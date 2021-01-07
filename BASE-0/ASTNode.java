import java.io.IOException;

public interface ASTNode {

    //int eval(Environment e);
    IValue eval(Environment<IValue> env);

    void compile(CodeBlock c, Environment env);

    IType typeCheck(Environment<IType> env);
}
