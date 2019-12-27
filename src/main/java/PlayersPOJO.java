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
import java.util.Map;

public class PlayersPOJO {


    public static void getPlayers(int option) throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("api.football-data.org")
                .setPath("v2/teams/66");


        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("Accept", "Application/json");
        httpGet.addHeader("X-Auth-Token", "238b34774e1848ab9f3ea0c7386b1a43");

        HttpResponse response = httpClient.execute(httpGet);
        ObjectMapper objectMapper = new ObjectMapper();


        Players players = objectMapper.readValue(response.getEntity().getContent(), Players.class);

        List<Map<String, Object>> allPlayers = new ArrayList<>();

        List<Map<String, Object>> goalkeepers = new ArrayList<>();
        List<Map<String, Object>> defenders = new ArrayList<>();
        List<Map<String, Object>> attackers = new ArrayList<>();
        List<Map<String, Object>> midfielders = new ArrayList<>();

        List<String> goalkeepersList = new ArrayList<>();
        List<String> attackersList= new ArrayList<>();

        for (int i = 0; i < players.getSquad().size();i++){

         //   allPlayers.add(players.getSquad().get(i));

            if(players.getSquad().get(i).get("position").equals("Goalkeeper"))
                goalkeepers.add(players.getSquad().get(i));

            if(players.getSquad().get(i).get("position").equals("Attacker"))
                attackers.add(players.getSquad().get(i));

            if(players.getSquad().get(i).get("position").equals("Defender"))
                defenders.add(players.getSquad().get(i));

            if(players.getSquad().get(i).get("position").equals("Midfielder"))
                midfielders.add(players.getSquad().get(i));

        }

      //  for(int i = 0; i < goalkeepers.size(); i++)
           // goalkeepersList.add(goalkeepers.get(i).get("name"));

//        switch (option){
//            case 0:
//               // return goalkeepersList;
//            case 1:
//            case 2:
//            default:;
//
//        }
//        System.out.println(allPlayers.get(0).get("position"));
//        System.out.println(allPlayers.get(0).get("name"));
//

    }

}