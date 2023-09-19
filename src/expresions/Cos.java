package expresions;

public class Cos extends Function {

	public Cos(Expression operand) {
		super(operand);
	}

	@Override
	public double getValue(double varVal) {
		return Math.cos(operand.getValue(varVal));
	}

	@Override
	public String toDisplayString() {
		return String.format(" cos(%s) ", operand.toDisplayString());
	}

	@Override
	public Expression derivative() {
		return new Multiplication(new Multiplication(new Constant(-1), new Sin(operand)), operand.derivative());
	}
	
	@Override
	public Expression reduced() {
		if (!operand.hasVariable() && operand.getValue(0) == 0) {
			return new Constant(1);
		}
		return new Cos(operand.reduced());
	}

}
