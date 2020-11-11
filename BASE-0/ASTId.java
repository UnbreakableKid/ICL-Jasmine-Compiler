import util.Coordinates;

public class ASTId implements ASTNode {

    String id;

    public ASTId(String i) {
        id = i;
    }

    public int eval(Environment e) {

        return (int) e.find(id);
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

        Coordinates x = (Coordinates) e.find(id);

        x.getDepth();

        for (int i = 0; i < x.getDepth(); i++) {

        }

    }
}
