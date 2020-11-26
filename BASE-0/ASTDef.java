import util.Coordinates;

import java.io.*;
import java.util.Map;

class ASTDef implements ASTNode {

    static final String DEFAULT_FOLDER = "Jasmine/";
    Map<String, ASTNode> vars;
    ASTNode body;

    public ASTDef(Map<String, ASTNode> vars, ASTNode r) {
        this.vars = vars;
        this.body = r;
    }

    public int eval(Environment env) {

        int val;
        Environment new_e = env.beginScope();

        for (Map.Entry<String, ASTNode> var : vars.entrySet()) {
            val = var.getValue().eval(new_e);
            new_e.assoc(var.getKey(), val);
        }
        val = body.eval(new_e);
        env = new_e.endScope();
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
}
