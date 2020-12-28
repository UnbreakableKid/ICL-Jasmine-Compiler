public class VRef implements IValue {

    IValue v;

    VRef(IValue v0) {
        v = v0;
    }

    IValue get() {
        return v;
    }

    void set(IValue v0) {
        v = v0;

    }

    @Override
    public IValue eval(Environment e) {
        return null;
    }
}
