public class ASTId implements ASTNode {

    String id;

    public ASTId(String i) {
        id = i;
    }

    public int eval(Environment e) {

        return e.find(id);
    }

    @Override
    public void compile(CodeBlock c) {
        // TODO Auto-generated method stub

    }
}

