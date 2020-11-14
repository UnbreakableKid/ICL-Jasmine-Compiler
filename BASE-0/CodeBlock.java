import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CodeBlock {
    StringBuffer code;
    int pos;
    private int LOCAL_LIMIT = 2;
    private int STACK_LIMIT = 256;

    public CodeBlock() {
        code = new StringBuffer();
        pos = 0;
    }

    /**
     * Function to add the bytecodes
     *
     * @param bytecode
     */
    void emit(String bytecode) {
        code.append(String.format("\t%s\n", bytecode));
    }

    void remove(String bytecode){
        int ibc2remove = code.lastIndexOf(bytecode);
        code.delete(ibc2remove,-1);
    }

    void dump(String filename, int... local_stack) throws IOException {
        int local, stack;
        switch(local_stack.length){
            case 2:
                local = local_stack[0];
                stack = local_stack[1];
                break;
            default:
                local = LOCAL_LIMIT;
                stack = STACK_LIMIT;
                break;
        }
        String file_j = String.format("%s.j",filename);
        BufferedWriter out = new BufferedWriter(new FileWriter(file_j));
        StringBuffer init = generateInit(filename, local, stack);

        StringBuffer end = generateEnd();
        out.write(init.toString());
        out.write(code.toString());
        out.write(end.toString());
        out.flush();
        out.close();
    }

    private StringBuffer generateEnd() {
        StringBuffer ending = new StringBuffer();
        ending.append("\n\tinvokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n");
        ending.append("\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n");
        ending.append("\treturn\n");
        ending.append(".end method\n");
        return ending;
    }

    private StringBuffer generateInit(String filename, int local, int stack) {
        StringBuffer initial = new StringBuffer();
        initial.append(String.format(".class public %s\n",filename));
        initial.append(".super java/lang/Object\n\n");
        initial.append(".method public <init>()V\n");
        initial.append("\taload_0\n");
        initial.append("\tinvokenonvirtual java/lang/Object/<init>()V\n");
        initial.append("\treturn\n");
        initial.append(".end method\n");
        initial.append(".method public static main([Ljava/lang/String;)V\n");
        initial.append(String.format("\t.limit locals %d\n", local));
        initial.append(String.format("\t.limit stack %d\n\n", stack));
        initial.append("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
        return initial;
    }

    /**
     * @return labels for the thingies
     */
    String genFrame() {
        String result = ("frame_" + pos);
        pos++;
        return result;
    }

    void initializeFrame(String frame) {

        emit("invokespecial " + frame + "/<init>()V");
        emit("dup");
    }

}