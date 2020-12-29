public class VRef implements IValue {

    IValue v;

    VRef(IValue v) {
        this.v = v;
    }

    IValue get() {
        return v;
    }

    void set(IValue nv) {
        v = nv;
    }

    @Override
    public IValue eval(Environment e) {
        return null;
    }
}
