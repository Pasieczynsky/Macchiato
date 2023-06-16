package Expressions;

// Singleton
public class ZeroConstant extends Constant {

    protected static ZeroConstant instance = new ZeroConstant();

    private ZeroConstant() {
        super(0);
    }

    @Override
    public Instruction multiply(Instruction other) {
        return instance;
    }

    @Override
    protected Instruction multiplyHelper(Instruction other) {
        return instance;
    }

    @Override
    public Instruction add(Instruction other) {
        return other;
    }

    @Override
    protected Instruction addHelper(Instruction other) {
        return other;
    }
}
