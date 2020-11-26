import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FrameCode {

    private static final String FRAME = "frame_";
    private final String frame_fname;
    private final int nframe;

    BufferedWriter out;

    public FrameCode(int depth){
        nframe = depth-1;
        frame_fname = FRAME + nframe;
    }

// %[argument_index$][flags][width][.precision]conversion

    void genInitFrame(CodeBlock code) throws IOException {
        String file_j = String.format("%s.j", frame_fname);
        out = new BufferedWriter(new FileWriter(CodeBlock.DEFAULT_FOLDER + file_j));

        code.emit("new " + frame_fname);
        code.emit("dup");
        code.emit("invokespecial " + frame_fname + "/<init>()V");
        code.emit("dup");
        out.write(String.format(".class public %s\n",frame_fname));
        out.write(".super java/lang/Object\n\n");
        if (nframe == 0) {
            code.emit("aload_0");
            code.emit(String.format("putfield %s0/sl Ljava/lang/Object;",FRAME));
            out.write(".field public sl Ljava/lang/Object;\n");
        } else {
            code.emit("aload_3");
            code.emit(String.format("putfield %s%d/sl L%1$s%d;", FRAME, nframe, nframe-1));
            out.write(String.format(".field public sl L%s%d;\n", FRAME, nframe-1));
        }
        code.emit("dup");
        code.emit("astore_3");
    }

    void genMiddleFrame(CodeBlock code, String vpos) throws IOException {

        code.emit(String.format("putfield %s%d/%s I",FRAME,nframe,vpos));
        out.write("\t.field public "+ vpos +" I\n");
    }

    void genEndFrame_1(CodeBlock code) throws IOException {

        out.write(".method public <init>()V\n");
        out.write("\taload_0\n");
        out.write("\tinvokenonvirtual java/lang/Object/<init>()V\n");
        out.write("\treturn\n");
        out.write(".end method\n");
        out.flush();
        out.close();
        code.emit("pop");
    }
    void genEndFrame_2(CodeBlock code) {

        code.emit("aload_3");
        String sl = (nframe <= 0)? "java/lang/Object":FRAME+(nframe-1);
        code.emit(String.format("getfield %s%d/sl L%s;",FRAME,nframe,sl));
        code.emit("astore_3");
    }

}
