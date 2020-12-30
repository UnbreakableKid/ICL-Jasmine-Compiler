import Exceptions.TypeError;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ASTNot implements ASTNode{

    private ASTNode v;

    public ASTNot(ASTNode v){
        this.v = v;
    }

    @Override
    public IValue eval(Environment<IValue> env) {
        IValue val = v.eval(env);
        if (val instanceof VBool) {
            return new VBool(!((VBool) val).getVal());
        }
        throw new TypeError(String.format("TypeError: [%s] is not valid to ~ operator", val.getClass().getName()));
    }

    @Override
    public void compile(CodeBlock c, Environment e) {
        throw new NotImplementedException();
    }
}
