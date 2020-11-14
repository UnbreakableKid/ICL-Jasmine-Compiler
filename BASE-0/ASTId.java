import util.Coordinates;

public class ASTId implements ASTNode {

    String id;

    public ASTId(String i) {
        id = i;
    }

    public int eval(Environment e) {

        return (int) e.find(id);
    }

    @Override
    public void compile(CodeBlock c, Environment e) {

        Coordinates x = (Coordinates) e.find(id);

        int idDepth = x.getDepth();

        int current_depth = e.depth();

        int difference = current_depth - idDepth;

        /*
         * imagina que está na depth 2 e o id foi definido na 0. fazer load da
         * frame_2/sl -> frame_1/sl -> frame_0/ valor
         * 
         * 
         * frame para cada def
         */

        c.emit("aload_3");

            for (int i = difference; i > 0; i--) {

                c.emit("getfield frame_" + i + "/sl Lframe_" + (i - 1) + ";");

            }

        c.emit("getfield frame_" + (idDepth-1) + "/" + x.getPosition() + " I");
    }
}
