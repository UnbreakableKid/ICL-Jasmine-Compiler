interface IValue {
    String toString();

    IValue eval(Environment e);

    // IValue constructors

    // VInt(int n);

    // VBool(String t);

    // VMCell(int value);
}