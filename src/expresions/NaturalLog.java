package expresions;

public class NaturalLog extends Function {

	public NaturalLog(Expression operand) {
		super(operand);
	}

	@Override
	public double getValue(double varVal) {
		return Math.log(varVal);
	}

	@Override
	public String toDisplayString() {
		return String.format(" ln(%s) ", operand.toDisplayString());
	}

	@Override
	public Expression derivative() {
		return new Division(operand.derivative(), operand);
	}
	
	@Override
	public Expression reduced() {
		if (!operand.reduced().hasVariable() && operand.reduced().getValue(0) == 1) {
			return new Constant(0);
		}
		return new NaturalLog(operand.reduced());
	}

}
