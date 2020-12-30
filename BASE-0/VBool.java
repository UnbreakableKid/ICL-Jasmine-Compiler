public class VBool implements IValue {

    Boolean v;

    public VBool(Boolean v0) {
        v = v0;
    }

    Boolean getVal() {
        return v;
    }

    @Override
    public String stringify(){
        return String.valueOf(v.booleanValue());
    }
}
