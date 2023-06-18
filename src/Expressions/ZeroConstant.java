package Expressions;

// Singleton
public class ZeroConstant extends Constant {

    protected static ZeroConstant instance = new ZeroConstant();

    private ZeroConstant() {
        super(0);
    }

    @Override
    public Expression multiply(Expression other) {
        return instance;
    }

    @Override
    protected Expression multiplyHelper(Expression other) {
        return instance;
    }

    @Override
    public Expression add(Expression other) {
        return other;
    }

    @Override
    protected Expression addHelper(Expression other) {
        return other;
    }
}
