public class ASTNum implements ASTNode {

    int val;

    public ASTNum(int n) {
        val = n;
    }

    @Override
    public int eval(Environment e) {
        return val;
    }
}

