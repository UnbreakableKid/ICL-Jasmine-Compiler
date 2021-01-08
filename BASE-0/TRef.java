public class TRef implements IType{

    private static final String REF = "Lref_%s;";
    private IType tr;

    public TRef(IType t){
        tr = t;
    }

    public IType getRefType(){
        return tr;
    }

    @Override
    public String getType() {
        return String.format(REF,tr.getType());
    }
}
