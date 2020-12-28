public class VInt implements IValue {

    int v;

    VInt(int v0) {
        v = v0;
    }

    int getval() {
        return v;
    }

    @Override
    public IValue eval(Environment e) {
        return null;
    }
}
