package expresions;

public class AbsoluteValue extends Function {

	public AbsoluteValue(Expression operand) {
		super(operand);
	}

	@Override
	public double getValue(double varVal) {
		return Math.abs(operand.getValue(varVal));
	}

	@Override
	public String toDisplayString() {
		return String.format("|%1$s|", operand.toDisplayString());
	}

	@Override
	public Expression derivative() {
		return new Division(new Multiplication(new AbsoluteValue(operand), operand.derivative()), operand);
	}

}
