import java.util.ArrayList;
import java.util.HashMap;

public class Environment {

    ArrayList<HashMap<String, Integer>> env;

    public Environment(){
        env = new ArrayList<>();
    }

    public void beginScope() {
        HashMap<String, Integer> newEnv = new HashMap<>();
        env.add(newEnv);
    }

    public void endScope() {
        env.remove(env.size()-1);
    }

    public void assoc(String id, int val) {

        env.get(env.size()-1).put(id,val);

    }

    public int find(String id) {
        for (int i = env.size()-1; i > 0 ; i--) {

            if (env.get(i).get(id) != null){
                return env.get(i).get(id);
            }
        }
        return env.get(env.size()-1).get(id);

    }
}
