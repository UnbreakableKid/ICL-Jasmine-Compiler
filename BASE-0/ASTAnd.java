public class ASTAnd implements ASTNode {
	
	ASTNode e1, e2;

	public ASTAnd(ASTNode e1, ASTNode e2) {
		this.e1 = e1;
		this.e2 = e2;
	}	

	@Override
	public IValue eval(Environment env)  {
		IValue v1 = e1.eval(env);
		IValue v2 = e2.eval(env);
		
		return new VBool(((VBool)v1).getval()&&((VBool)v2).getval());
	}

	@Override
	public void compile(CodeBlock c, Environment e) {

	}

}
