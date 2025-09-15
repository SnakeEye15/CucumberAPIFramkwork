package resources;
import io.cucumber.java.Before;
import stepDefinitions.StepDefinition;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlaceAPI")
    public void beforeScenario() throws IOException {
        StepDefinition step= new StepDefinition();
        if(StepDefinition.place_id ==null) {
            step.add_place_payload_with("Arun", "Telugu", "Goa");
            step.user_call_with_http_request("AddPlaceAPI", "POST");
            step.verify_place_id_created_maps_to_using("Arun", "GetPlaceAPI");
        }

    }
}
