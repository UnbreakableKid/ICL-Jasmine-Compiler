import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CodeBlock {
    StringBuffer code;
    private int LOCAL_LIMIT = 10;
    private int STACK_LIMIT = 256;
    private int numLabels = 0;
    //private Map<String, RefCode> refs;

    public CodeBlock() {
        code = new StringBuffer();
        //refs = new HashMap<>();
    }

    static final String DEFAULT_FOLDER = "Jasmine/";

    /**
     * Function to add the bytecodes
     *
     * @param bytecode
     */
    void emit(String bytecode) {
        code.append(String.format("\t%s\n", bytecode));
    }

    void emitNoEnter(String bytecode) {
        code.append(String.format("\t%s", bytecode));
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
        BufferedWriter out = new BufferedWriter(new FileWriter(DEFAULT_FOLDER + file_j));
        StringBuffer init = generateInit(filename, local, stack);

        StringBuffer end = generateEnd();
        out.write(init.toString());
        out.write(code.toString());
        out.write(end.toString());
        out.flush();
        out.close();
        //for (RefCode ref : refs.values())
          //  ref.dump();
    }

    private StringBuffer generateEnd() {
        StringBuffer ending = new StringBuffer();
        //ending.append("\n\tinvokestatic java/lang/String/valueOf(I)Ljava/lang/String;\n");
        //ending.append("\tinvokevirtual java/io/PrintStream/println(Ljava/lang/String;)V\n");
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
        initial.append("\taconst_null\n");
        initial.append("\tastore_3\n\n");
        //initial.append("getstatic java/lang/System/out Ljava/io/PrintStream;\n");
        return initial;
    }

    public int genLabels(int n){
        int curr = numLabels;
        numLabels += n;
        return curr;
    }

    public void generateRef(String type) {
        emit(String.format("new ref_%s", type));
        emit("dup");
        emit(String.format("invokespecial ref_%s/<init>()V", type));
        emit("dup");
        //if (!refs.containsKey(type))
          //  refs.put(type, new RefCode(type));
    }

    public void generateRefCheckcast(String type) {
        emit(String.format("checkcast ref_%s", type));
    }

    public void generateRefPut(String type, String vtype) {
        emit(String.format("putfield ref_%s/v %s", type, vtype));
    }

    public void generateRefGet(String type, String vtype) {
        emit(String.format("getfield ref_%s/v %s", type, vtype));
    }
}