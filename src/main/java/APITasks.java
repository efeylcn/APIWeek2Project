import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nurkulov 12/26/19
 */
public class APITasks {

    /*
     * GET all soccer team names listed in given resource
     * Deserialization type: Pojo
     */
    public static List<String> getAllTeams() throws URISyntaxException, IOException {
        return null;
    }

    /*
     * GET names of all goalkeepers from England team
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getAllGoalkeepers() throws URISyntaxException, IOException {


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
        List<String> attackersList = new ArrayList<>();

        for (int i = 0; i < players.getSquad().size(); i++) {

            allPlayers.add(players.getSquad().get(i));
            // System.out.println(allPlayers.get(i).toString());


            if (allPlayers.get(i).get("position") != null && allPlayers.get(i).get("position").equals("Goalkeeper")) {

                goalkeepersList.add(allPlayers.get(i).get("name").toString());


            }

        }


        return goalkeepersList;
    }

    /*
     * GET names of all defenders from England team
     * note: England team id is 66
     * Deserialization type: TypeReference
     */
    public static List<String> getDefenders() throws URISyntaxException, IOException {

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

        List<String> defendersList = new ArrayList<>();

        for (int i = 0; i < players.getSquad().size(); i++) {

            allPlayers.add(players.getSquad().get(i));

            if (allPlayers.get(i).get("position") != null && allPlayers.get(i).get("position").equals("Defender")) {

                defendersList.add(allPlayers.get(i).get("name").toString());

            }

        }

        return defendersList;
    }

    /*
     * GET names of all midfielders from England team
     * note: England team id is 66
     * Deserialization type: Pojo
     */
    public static List<String> getMidfielders() throws IOException, URISyntaxException {

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

        List<String> midfieldersList = new ArrayList<>();

        for (int i = 0; i < players.getSquad().size(); i++) {

            allPlayers.add(players.getSquad().get(i));

            if (allPlayers.get(i).get("position") != null && allPlayers.get(i).get("position").equals("Midfielder")) {

                midfieldersList.add(allPlayers.get(i).get("name").toString());
            }
        }
        return midfieldersList;
    }

    /*
     * GET names of all midfielders from England team whose country is Brazil
     * note: England team id is 66
     * Deserialization type: Pojo
     */
    public static List<String> getMidfielderFromBrazil() throws URISyntaxException, IOException {

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

        List<String> midfieldersSPNList = new ArrayList<>();

        for (int i = 0; i < players.getSquad().size(); i++) {

            allPlayers.add(players.getSquad().get(i));

            if (allPlayers.get(i).get("position") != null && allPlayers.get(i).get("position")
                    .equals("Midfielder")&&allPlayers.get(i).get("nationality").equals("Brazil")) {

                midfieldersSPNList.add(allPlayers.get(i).get("name").toString());
            }
        }
        return midfieldersSPNList;
    }

    /*
     * GET names of all attackers from England team whose origin country is England
     * note: England team id is 66
     * Deserialization type: Pojo
     */

    public static List<String> getAttackerFromEngland() throws URISyntaxException, IOException {


        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("api.football-data.org")
                .setPath("v2/teams/77");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("Accept", "Application/json");
        httpGet.addHeader("X-Auth-Token", "238b34774e1848ab9f3ea0c7386b1a43");

        HttpResponse response = httpClient.execute(httpGet);
        ObjectMapper objectMapper = new ObjectMapper();

        Players players = objectMapper.readValue(response.getEntity().getContent(), Players.class);

        List<Map<String, Object>> allPlayers = new ArrayList<>();

        List<String> attackersENGList = new ArrayList<>();

        for (int i = 0; i < players.getSquad().size(); i++) {

            allPlayers.add(players.getSquad().get(i));

            if (allPlayers.get(i).get("position") != null && allPlayers.get(i).get("position")
                    .equals("Attacker")&&allPlayers.get(i).get("nationality").equals("England")) {

                attackersENGList.add(allPlayers.get(i).get("name").toString());
            }
        }
        return attackersENGList;
    }

    /*
     * GET name of Spain team coach
     * note: Spain team id is 77
     * Deserialization type: Pojo
     */
    public static List<String> getSpainCoach() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("api.football-data.org")
                .setPath("v2/teams/77");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("Accept", "Application/json");
        httpGet.addHeader("X-Auth-Token", "238b34774e1848ab9f3ea0c7386b1a43");

        HttpResponse response = httpClient.execute(httpGet);
        ObjectMapper objectMapper = new ObjectMapper();

        Players players = objectMapper.readValue(response.getEntity().getContent(), Players.class);

        List<Map<String, Object>> allPlayers = new ArrayList<>();

        List<String> spainCoach = new ArrayList<>();

        for (int i = 0; i < players.getSquad().size(); i++) {

            allPlayers.add(players.getSquad().get(i));

            if (allPlayers.get(i).get("role") != null && allPlayers.get(i).get("role")
                    .equals("COACH")&&allPlayers.get(i).get("nationality").equals("Spain")) {

                spainCoach.add(allPlayers.get(i).get("name").toString());
            }
        }
        return spainCoach;
    }



    public static List<String> getAllCompetitions() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("api.football-data.org")
                .setPath("v2/competitions");


        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("Accept", "Application/json");
        httpGet.addHeader("X-Auth-Token", "238b34774e1848ab9f3ea0c7386b1a43");

        HttpResponse response = httpClient.execute(httpGet);
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

        Competitions competitions = objectMapper.readValue(response.getEntity().getContent(), Competitions.class);


        List<Map<String, Object>> allCompetitionsListOfMaps = new ArrayList<>();
        List<String> listOfCompetitions = new ArrayList<>();

        for (int i = 0; i < competitions.getCompetitions().size(); i++) {


            if (competitions.getCompetitions().get(i)!=null) {

                allCompetitionsListOfMaps.add(competitions.getCompetitions().get(i));

                listOfCompetitions.add(allCompetitionsListOfMaps.get(i).get("name").toString());
            }



        }return listOfCompetitions;
    }

    /*
     * GET names of second highest scorrer from competitions of 2000 season
     * note: endpoint for competitions: `competitions/<year>/
     * note: endpoint for scorers: `competitions/<year>/scorers`
     * Deserialization type: Pojo and TypeReference
     */
    public static List<String> getSecondHighestScorer() throws URISyntaxException, IOException {
        return null;
    }
}
