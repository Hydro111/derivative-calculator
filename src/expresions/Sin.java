package expresions;

public class Sin extends Function {

	public Sin(Expression operand) {
		super(operand);
	}

	@Override
	public double getValue(double varVal) {
		return Math.sin(operand.getValue(varVal));
	}

	@Override
	public String toDisplayString() {
		return String.format(" sin(%s) ", operand.toDisplayString());
	}

	@Override
	public Expression derivative() {
		return new Multiplication(new Cos(operand), operand.derivative());
	}
	
	@Override
	public Expression reduced() {
		if (!operand.hasVariable() && operand.getValue(0) == 0) {
			return new Constant(0);
		}
		return new Sin(operand.reduced());
	}

}
