public class VBool implements IValue {

    Boolean v;

    VBool(Boolean v0) {
        v = v0;
    }

    Boolean getVal() {
        return v;
    }

    @Override
    public IValue eval(Environment e) {
        return null;
    }
}
