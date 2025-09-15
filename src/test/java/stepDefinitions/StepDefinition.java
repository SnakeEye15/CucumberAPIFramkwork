package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {

    RequestSpecification res;
    ResponseSpecification resspec;
    public Response response;
    TestDataBuild data= new TestDataBuild();
    public static String place_id;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String Address)  throws IOException {
        // Write code here that turns the phrase above into concrete actions
        res=given().spec(requestSpecification()).body(data.addPlace(name,language,Address));
    }
    @When("user call {string} with {string} Http request")
    public void user_call_with_http_request(String APIRequest, String method) {
        // Write code here that turns the phrase above into concrete actions
        APIResources resourceAPI=APIResources.valueOf(APIRequest);
        System.out.println(resourceAPI.getResource());
        resspec= new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
        if (method.equalsIgnoreCase("POST"))
            response = res.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response = res.when().get(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("DELETE"))
            response= res.when().delete(resourceAPI.getResource());
        /***
        if(APIRequest.equalsIgnoreCase("AddPlaceAPI")){
            place_id = getJsonPath(response,"place_id");
            System.out.println("Captured place_id = " + place_id);
        }***/
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(), int1.intValue());

        // Capture place_id only for AddPlaceAPI calls
        if(response.asString().contains("place_id")) {
            place_id = getJsonPath(response, "place_id");
        }
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String KeyValue, String ExpectedValue) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(getJsonPath(response,KeyValue),ExpectedValue);
    }


    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String APIRequest) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        // extract place_id from response
        place_id = response.jsonPath().getString("place_id");
        System.out.println("Generated Place ID: " + place_id);

        // now call GetPlace API to verify
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_call_with_http_request(APIRequest, "GET");
        String actualName = response.jsonPath().getString("name");
        Assert.assertEquals(expectedName, actualName);

    }
    /***
    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        // Write code here that turns the phrase above into concrete actions

        res=given().spec(requestSpecification()).body(data.DeletePlacePayload(place_id));
        System.out.println("Deleting place with ID: " + place_id);

    }***/

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        Assert.assertNotNull("place_id should not be null before deleting", place_id);
        res = given().spec(requestSpecification()).body(data.DeletePlacePayload(place_id));
        System.out.println("Deleting place with ID: " + place_id);
    }



}
