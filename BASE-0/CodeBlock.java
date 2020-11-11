import java.util.ArrayList;

public class CodeBlock {
    ArrayList<String> code;
    int pos;

    public CodeBlock() {
        code = new ArrayList<>();
        pos = 0;
    }

    /**
     * Function to add the bytecodes
     *
     * @param bytecode
     */
    void emit(String bytecode) {
        code.add(bytecode);
    }

    void remove(){
        code.remove(code.size()-1);
    }


    void dump() {
        for (String str : code) {
            System.out.println(str);
        }
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