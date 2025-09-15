package resources;

import Pojos.AddLocation;
import Pojos.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddLocation addPlace(String name, String lanugage, String Address){
        AddLocation ad= new AddLocation();
        ad.setAccuracy(50);
        ad.setName(name);
        ad.setPhone_number("(+91) 983 893 3937");
        ad.setAddress(Address);
        ad.setWebsite("http://google.com");
        ad.setLanguage(lanugage);

        List<String> types=new ArrayList<String>();
        types.add("shoe park");
        types.add("shop");

        ad.setTypes(types);

        Location l= new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);

        ad.setLocation(l);

        return ad;
    }

    public String DeletePlacePayload(String placeId){

        return "{\n    \"place_id\":\"" + placeId + "\"\n}";
    }


}
