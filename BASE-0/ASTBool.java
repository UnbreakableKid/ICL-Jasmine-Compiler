import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ASTBool implements ASTNode{
    boolean bool;

    public ASTBool(boolean b) {
        bool = b;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        return new VBool(bool);
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

        if (bool)
            c.emit("sipush 1");
        else
            c.emit("sipush 0");

    }
}
