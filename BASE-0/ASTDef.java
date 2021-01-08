import Exceptions.TypeError;
import util.Coordinates;

import java.io.*;
import java.util.Map;

class ASTDef implements ASTNode {

    static final String DEFAULT_FOLDER = "Jasmine/";
    Map<String, ASTNode> vars;
    Map<String, IType> types;
    ASTNode body;

    public ASTDef(Map<String, ASTNode> vars, Map<String, IType> types, ASTNode r) {
        this.vars = vars;
        this.types = types;
        this.body = r;
    }

    public IValue eval(Environment<IValue> env) {

        IValue val;
        Environment<IValue> new_e = env.beginScope();

        for (Map.Entry<String, ASTNode> var : vars.entrySet()) {
            val = var.getValue().eval(new_e);
            new_e.assoc(var.getKey(), val);
        }
        val = body.eval(new_e);
        new_e.endScope();
        return val;
    }

    @Override
    public void compile(CodeBlock c, Environment env) {

        Environment new_e = env.beginScope();
        int current_depth = new_e.depth();
        FrameCode fc = new FrameCode(current_depth);

        try{
            fc.genInitFrame(c);

            int variableCount = 0;

            for (Map.Entry<String, ASTNode> var : vars.entrySet()) {
                c.emit("dup");
                var.getValue().compile(c, new_e);
                String pos = "v" + variableCount++;
                fc.genMiddleFrame(c, pos);
                new_e.assoc(var.getKey(), new Coordinates(pos, current_depth));
            }

            fc.genEndFrame_1(c);
            body.compile(c, new_e);
            fc.genEndFrame_2(c);

            env = new_e.endScope();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Override
    public IType typeCheck(Environment<IType> env) {

        IType val;
        Environment<IType> new_e = env.beginScope();

        for (Map.Entry<String, ASTNode> var : vars.entrySet()) {
            val = var.getValue().typeCheck(env);
            IType type = types.get(var.getKey());

            IType aux = type;

            while (aux instanceof TRef){
                aux = ((TRef) val).getRefType();
            }
            if(aux.getType().compareTo(type.getType()) != 0 )
                throw new TypeError(String.format("Illegal type in [def]: [%s] not match [%s]",aux.getType(),type.getType()));

            new_e.assoc(var.getKey(), val);
        }
        val = body.typeCheck(new_e);
        new_e.endScope();

        return val;
    }
}
