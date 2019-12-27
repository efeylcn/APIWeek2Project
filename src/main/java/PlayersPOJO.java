import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayersPOJO {

    @Test
    public void getPlayers() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("api.football-data.org")
                .setPath("v2/competitions/2000/scorers");


        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("Accept", "Application/json");
        httpGet.addHeader("X-Auth-Token", "238b34774e1848ab9f3ea0c7386b1a43");

        HttpResponse response = httpClient.execute(httpGet);
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

        Scorers scorers = objectMapper.readValue(response.getEntity().getContent(), Scorers.class);


        List<Map<String, Object>> scorersListOMaps = new ArrayList<>();

        List<String> listOfScorers = new ArrayList<>();

        for (int i = 0; i < scorers.getScorers().size(); i++) {

          //  int score = (int) scorers.getScorers().get(i).get("numberOfGoals");&& scorers.getScorers().get(i).get("numberOfGoals").equals(4)


                listOfScorers.add(scorersListOMaps.get(i).get("scorers").toString());

            }


        System.out.println(listOfScorers);

    }}


