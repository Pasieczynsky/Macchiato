package Expressions;

// Singleton
public class OneConstant extends Constant {

    protected static OneConstant instance = new OneConstant();

    private OneConstant() {
        super(1);
    }

    @Override
    public Instruction multiply(Instruction other) {
        return other;
    }

    @Override
    protected Instruction multiplyHelper(Instruction other) {
        return other;
    }

}
