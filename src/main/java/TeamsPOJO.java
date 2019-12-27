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



    }
}