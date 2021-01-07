import Exceptions.TypeError;

public class ASTWhile implements ASTNode {

    private static final String BYTECODE = "iadd";
    ASTNode lhs, rhs;

    public ASTWhile(ASTNode condition, ASTNode body) {
        lhs = condition;
        rhs = body;
    }

    public IValue eval(Environment<IValue> env) {

        IValue condVal = lhs.eval(env);
        if(!(condVal instanceof VBool))
            throw new TypeError("TypeError: Illegal arguments with relational operators...");

        IValue finalexp = null;
        while (((VBool) condVal).getVal()) {
            finalexp = rhs.eval(env);
            condVal = lhs.eval(env);
        }
        return finalexp;
    }

    public void compile(CodeBlock c, Environment e) {

        int labels = c.genLabels(2);

        String start = "L" + labels;
        String end = "L" + labels+1;

        c.emit(start + ":");
        lhs.compile(c,e);
        c.emit("ifeq " + end);
        rhs.compile(c,e);
        c.emit("pop");
        c.emit("goto " + start);
        c.emit(end + ":");
    }

    @Override
    public IType typeCheck(Environment<IType> env) {
        IType condType = lhs.typeCheck(env);

        if(condType instanceof TBool){
            rhs.typeCheck(env);
            return new TBool();
        }
        throw new TypeError("while: condition is non boolean");
    }

}
