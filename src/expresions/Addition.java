package expresions;

public class Addition extends Operator {

	public Addition(Expression operand1, Expression operand2) {
		super(operand1, operand2);
	}

	@Override
	public double getValue(double varVal) {
		return operand1.getValue(varVal) + operand2.getValue(varVal);
	}

	@Override
	public String toDisplayString() {
		return String.format("(%1$s + %2$s)", operand1.toDisplayString(), operand2.toDisplayString());
	}

	@Override
	public Expression derivative() {
		return new Addition(operand1.derivative(), operand2.derivative());
	}
	
	@Override
	public Expression reduced() {
		if (!operand1.hasVariable() && operand1.getValue(0) == 0) {
			return operand2.reduced();
		}
		
		if (!operand2.hasVariable() && operand2.getValue(0) == 0) {
			return operand1.reduced();
		}
		
		return new Addition(operand1.reduced(), operand2.reduced());
	}
}