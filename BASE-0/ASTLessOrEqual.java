import Exceptions.TypeError;

public class ASTLessOrEqual implements ASTNode {

	private static final String BYTECODE = "if_icmple";

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

	public void compile(CodeBlock c, Environment e) {

		e1.compile(c, e);

		e2.compile(c, e);

		c.emitNoEnter(BYTECODE);

		int x = c.genLabels(1);

		c.emit("TRUE" + x);
		c.emit("sipush 0");
		c.emit("goto exit" + x);

		c.emit("TRUE" +(x) + ":");
		c.emit("sipush 1");

		c.emit("exit" + x+ ":");


	}

	@Override
	public IType typeCheck(Environment<IType> env) {
		IType left = e1.typeCheck(env);
		IType right = e2.typeCheck(env);

		if(left instanceof TInt && right instanceof TInt)
			return new TInt();

		throw new TypeError("<=: argument is not an integer");
	}

}
