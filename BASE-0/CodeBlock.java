import java.util.ArrayList;

public class CodeBlock {
    ArrayList<String> code;
    int pos;

    public CodeBlock(){
        code = new ArrayList<>();
        pos = 0;
    }
    void emit(String bytecode) {
        code.add(bytecode);
    }
}