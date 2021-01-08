
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RefCode {
    String type;

    public RefCode(String type) {
        this.type = type;
    }
    public void dump() throws IOException {
        String file_j = String.format("ref_%s.j", type);
        BufferedWriter out = new BufferedWriter(new FileWriter(CodeBlock.DEFAULT_FOLDER + file_j));
        out.write(String.format(".class ref_%s\n", type));
        out.write(".super java/lang/Object\n");
        out.write(".field public v I\n");
        out.write(".method public <init>()V\n");
        out.write("\taload_0\n");
        out.write("\tinvokenonvirtual java/lang/Object/<init>()V\n");
        out.write("\treturn\n");
        out.write(".end method\n");
        out.flush();
        out.close();
    }
}