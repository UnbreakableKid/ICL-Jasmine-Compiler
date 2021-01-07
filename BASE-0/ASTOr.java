import Exceptions.TypeError;

public class ASTOr implements ASTNode {

	private static final String BYTECODE = "ior";

	ASTNode e1, e2;

	public ASTOr(ASTNode e1, ASTNode e2) {
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public IValue eval(Environment<IValue> env)  {
		IValue v1 = e1.eval(env);
		IValue v2 = e2.eval(env);
		if (v1 instanceof VBool && v2 instanceof VBool){
			return new VBool(((VBool)v1).getVal()||((VBool)v2).getVal());
		}
		throw new TypeError("TypeError: Illegal arguments with relational operators...");
	}

	@Override
	public void compile(CodeBlock c, Environment e) {
		e1.compile(c, e);
		e2.compile(c, e);
		c.emit(BYTECODE);
	}

	@Override
	public IType typeCheck(Environment<IType> env) {
		IType left = e1.typeCheck(env);
		IType right = e2.typeCheck(env);

		if(left instanceof TBool && right instanceof TBool)
			return new TBool();

		throw new TypeError("||: argument is non boolean");
	}

}
