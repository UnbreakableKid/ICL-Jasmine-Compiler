import util.Coordinates;

import java.io.*;
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

    static final String DEFAULT_FOLDER = "Jasmine/";


    @Override
    public void compile(CodeBlock c, Environment e) {

        Environment new_e = e.beginScope();
        String frame = c.genFrame();
        c.emit("new " + frame);
        c.emit("dup");
        c.initializeFrame(frame);

        String file_j = String.format("%s.j",frame);

        try{

            BufferedWriter out = new BufferedWriter(new FileWriter(new File(DEFAULT_FOLDER + file_j)));

            out.write(".class public " + frame +"\n");
            out.write(".super java/lang/Object\n");


        int current_depth = new_e.depth();

        if (current_depth == 1) {
            c.emit("aload_0");
            c.emit("putfield frame_0/sl Ljava/lang/Object;");
            out.write(".field public sl Ljava/lang/Object;\n");
        } else {
            c.emit("aload_3");
            c.emit("putfield frame_" + (current_depth-1) + "/sl Lframe_" + (current_depth-2) + ";");
            out.write(".field public sl Lframe_" + (current_depth-2)+";\n");
        }

        c.emit("dup");

        c.emit("astore_3");

        int variableCount = 0;

        for (Map.Entry<String, ASTNode> var : vars.entrySet()) {
            c.emit("dup");
            var.getValue().compile(c, new_e);
            String pos = "v" + variableCount;
            new_e.assoc(var.getKey(), new Coordinates(pos, current_depth));
            c.emit("putfield frame_" + (current_depth-1) + "/" + pos + " I");
            out.write("\t.field public " + pos+ " I\n");
            variableCount++;
        }

        out.write(".method public <init>()V\n");
        out.write("aload_0\n");
        out.write("invokenonvirtual java/lang/Object/<init>()V\n");
        out.write("return\n");
        out.write(".end method\n");
            out.flush();
            out.close();
        c.emit("pop");
        body.compile(c, new_e);
        c.emit("aload_3");
        String sl = (current_depth-1 <= 0)? "java/lang/Object":"frame_"+(current_depth-2);
        c.emit("getfield frame_" + (current_depth-1) + "/sl L" + sl + ";");
        c.emit("astore_3");

        e = new_e.endScope();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
