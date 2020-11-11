import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Environment<T> {

    ArrayList<Map<String, T>> env;

    public Environment() {
        env = new ArrayList<>();
    }

    public void beginScope() {
        Map<String, T> newEnv = new HashMap<>();
        env.add(newEnv);
    }

    public void endScope() {
        env.remove(env.size() - 1);
    }

    public void assoc(String id, T val) {

        env.get(env.size() - 1).put(id, val);

    }

    public T find(String id) {

        for (int i = env.size() - 1; i > 0; i--) {

            if (env.get(i).get(id) != null) {
                return env.get(i).get(id);
            }
        }
        return env.get(env.size() - 1).get(id);

    }

    public int depth() {
        return env.size();
    }
}
