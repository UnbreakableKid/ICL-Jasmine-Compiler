public interface ASTNode {

    //int eval(Environment e);
    IValue eval(Environment e);

    void compile(CodeBlock c, Environment e);

}
