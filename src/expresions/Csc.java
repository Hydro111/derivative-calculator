package expresions;
public class Csc extends Function {

	public Csc(Expression operand) {
		super(operand);
	}

	@Override
	public double getValue(double varVal) {
		return 1 / Math.sin(operand.getValue(varVal));
	}

	@Override
	public String toDisplayString() {
		return String.format(" csc(%s) ", operand.toDisplayString());
	}

	@Override
	public Expression derivative() {
		return new Multiplication(new Constant(-1), new Multiplication(new Multiplication(new Csc(operand), new Cot(operand)), operand.derivative()));
	}
	
	@Override
	public Expression reduced() {
		return new Csc(operand.reduced());
	}

}
