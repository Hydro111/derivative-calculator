import expresions.Constant;
import expresions.Expression;
import expresions.Function;
import expresions.Multiplication;
import expresions.Power;

public class Cot extends Function {

	public Cot(Expression operand) {
		super(operand);
	}
	
	@Override
	public double getValue(double varVal) {
		return 1 / Math.tan(operand.getValue(varVal));
	}

	@Override
	public String toDisplayString() {
		return String.format(" cot(%s) ", operand.toDisplayString());
	}

	@Override
	public Expression derivative() {
		return new Multiplication(new Constant(-1), new Multiplication(new Power(new Csc(operand), new Constant(2)), operand.derivative()));
	}
	
	@Override
	public Expression reduced() {
		return new Cot(operand.reduced());
	}

}
