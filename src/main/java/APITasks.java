import Pojo.MidfieldersPojo.MidFildersPojo;
import Pojo.TeamPojo.TeamsPojo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by nurkulov 12/26/19
 */
public class APITasks {

   static ObjectMapper objectMapper=new ObjectMapper();

    /*
     * GET all soccer team names listed in given resource
     * Deserialization type: Pojo
     */
    public static List<String> getAllTeams() throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("api.football-data.org")
                .setPath("v2/teams/");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("X-Auth-Token", "1c15c4d2d6264f9e9159f66b8d7ee109");


        HttpResponse response = httpClient.execute(httpGet);

        TeamsPojo teamsPojo = objectMapper.readValue(response.getEntity().getContent(), TeamsPojo.class);

        List<String> allTeams = new ArrayList<>();
        for(int i=0;i<teamsPojo.getTeams().size();i++){
            allTeams.add(teamsPojo.getTeams().get(i).getName());
        }

        return allTeams;
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
        httpGet.addHeader("X-Auth-Token", "1c15c4d2d6264f9e9159f66b8d7ee109");


        HttpResponse response = httpClient.execute(httpGet);

        Map<String,Object> map = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference <Map<String,Object>>(){
                });
        List<Map<String,Object>> listOfSquad = (List<Map<String, Object>>) map.get("squad");

        List<String> goalKeepersName=new ArrayList<>();

        for(int i=0;i<listOfSquad.size();i++){
            if(listOfSquad.get(i).get("position") != null) {
                if (listOfSquad.get(i).get("position").toString().equalsIgnoreCase("Goalkeeper")) {
                    goalKeepersName.add(listOfSquad.get(i).get("name").toString());
                }
            }
        }
        return goalKeepersName;
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
        httpGet.addHeader("X-Auth-Token", "1c15c4d2d6264f9e9159f66b8d7ee109");


        HttpResponse response = httpClient.execute(httpGet);

        Map<String,Object> map = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference <Map<String,Object>>(){
                });
        List<Map<String,Object>> listOfSquad = (List<Map<String, Object>>) map.get("squad");

        List<String> defendersName=new ArrayList<>();

        for(int i=0;i<listOfSquad.size();i++){
            if(listOfSquad.get(i).get("position") != null) {
                if (listOfSquad.get(i).get("position").toString().equalsIgnoreCase("Defender")) {
                    defendersName.add(listOfSquad.get(i).get("name").toString());
                }
            }
        }

        return defendersName;
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
        httpGet.addHeader("X-Auth-Token", "1c15c4d2d6264f9e9159f66b8d7ee109");


        HttpResponse response = httpClient.execute(httpGet);

        MidFildersPojo midFildersPojo = objectMapper.readValue(response.getEntity().getContent(), MidFildersPojo.class);

        List<String> midFieldersName=new ArrayList<>();

        for(int i=0;i<midFildersPojo.getSquad().size();i++){
            if(midFildersPojo.getSquad().get(i).getPosition() != null) {
                if (midFildersPojo.getSquad().get(i).getPosition().equalsIgnoreCase("Midfielder")) {
                    midFieldersName.add(midFildersPojo.getSquad().get(i).getName());
                }
            }
        }

        return midFieldersName;
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
        httpGet.addHeader("X-Auth-Token", "1c15c4d2d6264f9e9159f66b8d7ee109");


        HttpResponse response = httpClient.execute(httpGet);

        MidFildersPojo midFildersPojo = objectMapper.readValue(response.getEntity().getContent(), MidFildersPojo.class);

        List<String> midFieldersName=new ArrayList<>();

        for(int i=0;i<midFildersPojo.getSquad().size();i++){
            if(midFildersPojo.getSquad().get(i).getPosition() != null) {
                if (midFildersPojo.getSquad().get(i).getPosition().equalsIgnoreCase("Midfielder")
                        && midFildersPojo.getSquad().get(i).getCountryOfBirth().equals("Brazil")) {
                    midFieldersName.add(midFildersPojo.getSquad().get(i).getName());
                }
            }
        }

        return midFieldersName;
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
                .setPath("v2/teams/66");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("X-Auth-Token", "1c15c4d2d6264f9e9159f66b8d7ee109");


        HttpResponse response = httpClient.execute(httpGet);

        MidFildersPojo midFildersPojo = objectMapper.readValue(response.getEntity().getContent(), MidFildersPojo.class);

        List<String> midFieldersName=new ArrayList<>();

        for(int i=0;i<midFildersPojo.getSquad().size();i++){
            if(midFildersPojo.getSquad().get(i).getPosition() != null) {
                if (midFildersPojo.getSquad().get(i).getPosition().equalsIgnoreCase("Attacker")
                        && midFildersPojo.getSquad().get(i).getCountryOfBirth().equals("England")) {
                    midFieldersName.add(midFildersPojo.getSquad().get(i).getName());
                }
            }
        }

        return midFieldersName;
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
        httpGet.addHeader("X-Auth-Token", "1c15c4d2d6264f9e9159f66b8d7ee109");


        HttpResponse response = httpClient.execute(httpGet);

        MidFildersPojo midFildersPojo = objectMapper.readValue(response.getEntity().getContent(), MidFildersPojo.class);

        List<String> coachesName=new ArrayList<>();

        for(int i=0;i<midFildersPojo.getSquad().size();i++){
                if (midFildersPojo.getSquad().get(i).getRole().equals("COACH")){
                    coachesName.add(midFildersPojo.getSquad().get(i).getName());
            }
        }

        return coachesName;
    }

    public static List<String> getAllCompetitions() throws URISyntaxException, IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("http")
                .setHost("api.football-data.org")
                .setPath("v2/competitions");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.addHeader("X-Auth-Token", "1c15c4d2d6264f9e9159f66b8d7ee109");


        HttpResponse response = httpClient.execute(httpGet);
        Map<String,Object> map = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference <Map<String,Object>>(){
                });
        List<Map<String,Object>> listOfCompetitions = (List<Map<String, Object>>) map.get("competitions");

        List<String> competitions = new ArrayList<>();
        for(int i=0;i<listOfCompetitions.size();i++){
            competitions.add(listOfCompetitions.get(i).get("name").toString());
        }


        return competitions;

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
