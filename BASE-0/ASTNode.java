import java.io.IOException;

public interface ASTNode {

    //int eval(Environment e);
    IValue eval(Environment<IValue> e);

    void compile(CodeBlock c, Environment e);

}
