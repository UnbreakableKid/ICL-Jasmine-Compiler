import java.util.HashMap;
import java.util.Map;

public class Environment<T> {

    private Environment<T> ancestor;
    private Map<String, T> env;
    private int depth;

    public Environment() {
        ancestor = null;
        env = new HashMap<>();
        depth = 0;
    }

    public Environment(Environment<T> actual2ancestor) {
        ancestor = actual2ancestor;
        env = new HashMap<>();
        depth = actual2ancestor.depth() + 1;
    }

    public Environment<T> beginScope() {
        return new Environment<T>(this);
    }

    public Environment<T> endScope() {
        return ancestor;
    }

    public void assoc(String id, T val) {
        if(env.containsKey(id))
            throw new RuntimeException("IDDeclaredTwice");
        env.put(id, val);
    }

    public T find(String id) {
        T val = env.get(id);
        if(val == null)
            if(ancestor == null)
                throw new RuntimeException("Undeclared Variable");
            else
                val = ancestor.find(id);
        return val;

    }

    public int depth() {
        return depth;
    }
}
