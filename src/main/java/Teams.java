import java.util.List;
import java.util.Map;

public class Teams {

    private int count;
    private Map<String,Object> filters;
    private List<Map<String, Object>> teams;

    public void Teams(){}


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, Object> filters) {
        this.filters = filters;
    }

    public List<Map<String, Object>> getTeams() {
        return teams;
    }

    public void setTeams(List<Map<String, Object>> teams) {
        this.teams = teams;
    }
}
