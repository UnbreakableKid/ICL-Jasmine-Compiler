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

        int current_depth = e.depth() - 2;

        int difference = current_depth - idDepth;

        /*
         * imagina que está na depth 2 e o id foi definido na 0. fazer load da
         * frame_2/sl -> frame_1/sl -> frame_0/ valor
         * 
         * 
         * frame para cada def
         */

        if(difference == 0){
            c.emit("aload_3");
            c.emit("getfield frame_" + idDepth + "/" + x.getPosition() + " I");

        }
        else {
            c.emit("aload_3");

            for (int i = difference; i > 0; i--) {

                c.emit("getfield frame_" + difference + "/sl Lframe_" + (i - 1) +";");


            }
            c.emit("getfield frame_" + idDepth + "/" + x.getPosition() + " I");
        }

        // getfield frame_0/v1 I



    }
}
