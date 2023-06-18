package Expressions;

// Singleton
public class OneConstant extends Constant {

    protected static OneConstant instance = new OneConstant();

    private OneConstant() {
        super(1);
    }

    @Override
    public Expression multiply(Expression other) {
        return other;
    }

    @Override
    protected Expression multiplyHelper(Expression other) {
        return other;
    }

}
