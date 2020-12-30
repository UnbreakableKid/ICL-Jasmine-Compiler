public class VInt implements IValue {

    int v;

    VInt(int v0) {
        v = v0;
    }

    int getVal() {
        return v;
    }

    @Override
    public String stringify(){
        return String.valueOf(v);
    }

}
