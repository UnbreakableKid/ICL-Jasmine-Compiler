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

        int v1;
        e.beginScope();

        for (Map.Entry<String, ASTNode> var : vars.entrySet()) {
            v1 = var.getValue().eval(e);
            e.assoc(var.getKey(), v1);
        }
        int val = body.eval(e);
        e.endScope();
        return val;
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

        e.beginScope();
        String frame = c.genFrame();
        c.emit("new " + frame);
        c.emit("dup");
        c.initializeFrame(frame);

        int current_depth = e.depth() - 2;

        if (current_depth == 0) {
            c.emit("aload_0");
            c.emit("putfield frame_0/sl Ljava/lang/Object;");
        } else {
            c.emit("aload_3");
            c.emit("putfield frame_" + (current_depth) + "/sl Lframe_" + (current_depth-1) + ";");
        }

        c.emit("dup");

        c.emit("astore_3");
        c.emit("dup");

        int variableCount = 0;

        for (Map.Entry<String, ASTNode> var : vars.entrySet()) {
            var.getValue().compile(c, e);
            String pos = "v" + variableCount;
            e.assoc(var.getKey(), new Coordinates(pos, current_depth));
            c.emit("putfield frame_" + current_depth + "/" + pos + " I");
            c.emit("dup");
            variableCount++;

        }

        c.remove();
        c.emit("pop");
        body.compile(c, e);

        e.endScope();

    }
}
