
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RefCode {
    String type;
    String vtype;

    public RefCode(String type, String vtype) {
        this.type = type;
        this.vtype = vtype;
    }
    public void dump() throws IOException {
        String file_j = String.format("ref_%s.j", type);
        BufferedWriter out = new BufferedWriter(new FileWriter(CodeBlock.DEFAULT_FOLDER + file_j));
        out.write(String.format(".class ref_%s\n", type));
        out.write(".super java/lang/Object\n");
        out.write(String.format(".field public v %s\n",vtype));
        out.write(".method public <init>()V\n");
        out.write("\taload_0\n");
        out.write("\tinvokenonvirtual java/lang/Object/<init>()V\n");
        out.write("\treturn\n");
        out.write(".end method\n");
        out.flush();
        out.close();
    }
}