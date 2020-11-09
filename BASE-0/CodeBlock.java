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

    /**
     * 
     * 
     * @return labels for the thingies
     */
    String genSymbol() {
        String result = ("new frame_" + pos);
        pos++;
        return result;
    }

}