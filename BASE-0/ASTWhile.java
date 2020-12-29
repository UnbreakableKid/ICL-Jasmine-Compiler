public class ASTWhile implements ASTNode {

    private static final String BYTECODE = "iadd";
    ASTNode lhs, rhs;

    public ASTWhile(ASTNode condition, ASTNode body) {
        lhs = condition;
        rhs = body;
    }

    public void compile(CodeBlock c, Environment e) {

        int labels = c.genLabels(2);

        String start = "L" + labels;
        String end = "L" + labels+1;

        c.emit(start + ":");
        lhs.compile(c,e);
        c.emit("ifeq " + end);
        c.emit("pop");
        c.emit("goto " + start);
        c.emit(end + ":");
    }
    public IValue eval(Environment env) {

        IValue finalexp = null;
        Boolean condition = ((VBool) lhs.eval(env)).getVal();
        while (condition) {
            finalexp = rhs.eval(env);
            condition = ((VBool) lhs.eval(env)).getVal();
        }

        return finalexp;
    }
}
