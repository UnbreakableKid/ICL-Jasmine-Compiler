public class ASTMinus implements ASTNode {

    ASTNode lhs, rhs;

    public int eval() {
        int v1 = lhs.eval();
        int v2 = rhs.eval();
        return v1 - v2;
    }

    public ASTMinus(ASTNode l, ASTNode r) {
        lhs = l;
        rhs = r;
    }
}

