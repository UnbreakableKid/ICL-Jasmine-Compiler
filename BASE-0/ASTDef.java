import util.Coordinates;

import java.util.Map;

class ASTDef implements ASTNode {
    Map<String, ASTNode> vars;
    ASTNode body;

    public ASTDef(Map<String, ASTNode> vars, ASTNode r) {
        this.vars = vars;
        this.body = r;
    }

    public int eval(Environment e) {

        int val;
        Environment new_e = e.beginScope();

        for (Map.Entry<String, ASTNode> var : vars.entrySet()) {
            val = var.getValue().eval(new_e);
            new_e.assoc(var.getKey(), val);
        }
        val = body.eval(new_e);
        e = new_e.endScope();
        return val;
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

        Environment new_e = e.beginScope();
        String frame = c.genFrame();
        c.emit("new " + frame);
        c.emit("dup");
        c.initializeFrame(frame);

        int current_depth = new_e.depth();

        if (current_depth == 1) {
            c.emit("aload_0");
            c.emit("putfield frame_0/sl Ljava/lang/Object;");
        } else {
            c.emit("aload_3");
            c.emit("putfield frame_" + (current_depth-1) + "/sl Lframe_" + (current_depth-2) + ";");
        }

        c.emit("dup");

        c.emit("astore_3");
        //c.emit("dup");

        int variableCount = 0;

        for (Map.Entry<String, ASTNode> var : vars.entrySet()) {
            c.emit("dup");
            var.getValue().compile(c, new_e);
            String pos = "v" + variableCount;
            new_e.assoc(var.getKey(), new Coordinates(pos, current_depth));
            c.emit("putfield frame_" + (current_depth-1) + "/" + pos + " I");
            variableCount++;
        }

        //c.remove("dup");
        c.emit("pop");
        body.compile(c, new_e);

        e = new_e.endScope();

    }
}
