public class ASTId implements ASTNode {

    String id;

    public ASTId(String i) {
        id = i;
    }

    public int eval(Environment e) {

        // return e.find(id);
        return 1;
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        // TODO Auto-generated method stub

    }
}
