import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TeamsPOJO {


    public static List<String> getTeams() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("api.football-data.org")
                .setPath("v2/teams/");


        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("Accept", "Application/json");
        httpGet.addHeader("X-Auth-Token", "238b34774e1848ab9f3ea0c7386b1a43");

        HttpResponse response = httpClient.execute(httpGet);
        ObjectMapper objectMapper = new ObjectMapper();


        Teams allTeams = objectMapper.readValue(response.getEntity().getContent(), Teams.class);
        List teamNames = new ArrayList();
        for(int i=0; i<allTeams.getCount();i++){
            teamNames.add(allTeams.getTeams().get(i).get("name"));
        }
        return teamNames;

//
//        "id": 57,
//                "area": {
//            "id": 2072,
//                    "name": "England"
//        },
//        "name": "Arsenal FC",
//                "shortName": "Arsenal",
//                "tla": "ARS",
//                "crestUrl": "http://upload.wikimedia.org/wikipedia/en/5/53/Arsenal_FC.svg",
//                "address": "75 Drayton Park London N5 1BU",
//                "phone": "+44 (020) 76195003",
//                "website": "http://www.arsenal.com",
//                "email": "info@arsenal.co.uk",
//                "founded": 1886,
//                "clubColors": "Red / White",
//                "venue": "Emirates Stadium",
//                "lastUpdated": "2019-12-26T02:24:48Z"
//    },


    }
}