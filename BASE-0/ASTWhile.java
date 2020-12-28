public class ASTWhile implements ASTNode {

    private static final String BYTECODE = "iadd";
    ASTNode lhs, rhs;

    public ASTWhile(ASTNode condition, ASTNode body) {
        lhs = condition;
        rhs = body;
    }

    public void compile(CodeBlock c, Environment e) {

    }
    public IValue eval(Environment env) {

        IValue finalexp = null;
        Boolean condition = ((VBool) lhs.eval(env)).getval();
        while (condition) {
            finalexp = rhs.eval(env);
            condition = ((VBool) lhs.eval(env)).getval();
        }

        return finalexp;
    }
}
