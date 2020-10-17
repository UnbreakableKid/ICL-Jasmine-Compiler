public class ASTId implements ASTNode {

    String id;

    public ASTId(String i) {
        id = i;
    }

    public int eval(Environment e) {

        return e.find(id);
    }
}

