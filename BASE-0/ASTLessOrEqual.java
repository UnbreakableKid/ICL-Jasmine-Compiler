import Exceptions.TypeError;

public class ASTLessOrEqual implements ASTNode {

	ASTNode e1, e2;

	public ASTLessOrEqual(ASTNode e1, ASTNode e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public IValue eval(Environment<IValue> env)  {
		IValue v1 = e1.eval(env);
		IValue v2 = e2.eval(env);
		if (v1 instanceof VInt && v2 instanceof VInt)
			return new VBool(((VInt) v1).getVal() <= ((VInt) v2).getVal());
		throw new TypeError("TypeError: Illegal arguments with relational operators...");
	}

	@Override
	public void compile(CodeBlock c, Environment e) {

	}

}
