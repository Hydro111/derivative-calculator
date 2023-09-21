package expresions;
public class Sec extends Function {

	public Sec(Expression operand) {
		super(operand);
	}

	@Override
	public double getValue(double varVal) {
		return 1 / Math.cos(operand.getValue(varVal));
	}

	@Override
	public String toDisplayString() {
		return String.format(" sec(%s) ", operand.toDisplayString());
	}

	@Override
	public Expression derivative() {
		return new Multiplication(new Multiplication(new Sec(operand), new Tan(operand)), operand.derivative());
	}
	
	@Override
	public Expression reduced() {
		if (!operand.hasVariable() && operand.getValue(0) == 0) {
			return new Constant(1);
		}
		return new Sec(operand.reduced());
	}
}
