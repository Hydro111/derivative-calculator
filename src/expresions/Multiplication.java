package expresions;
public class Multiplication extends Operator {

	public Multiplication(Expression operand1, Expression operand2) {
		super(operand1, operand2);
	}

	@Override
	public double getValue(double varVal) {
		return operand1.getValue(varVal) * operand2.getValue(varVal);
	}

	@Override
	public String toDisplayString() {
		return String.format("(%1$s * %2$s)", operand1.toDisplayString(), operand2.toDisplayString());
	}

	@Override
	public Expression derivative() {
		boolean hasVariable1 = operand1.hasVariable();
		boolean hasVariable2 = operand2.hasVariable();
		
		// Product rule
		if ( hasVariable1 &&  hasVariable2) {
			return new Addition(
					new Multiplication(operand1, operand2.derivative()),
					new Multiplication(operand2, operand1.derivative())
			);
		}
		
		// It's a constant
		if (!hasVariable1 && !hasVariable2) {
			return new Constant(this.getValue(0));
		}
		
		// Pull out a constant
		if (!hasVariable1 &&  hasVariable2) {
			return new Multiplication(new Constant(operand1.getValue(0)), operand2.derivative());
		}
		
		// Pull out a constant
		if ( hasVariable1 && !hasVariable2) {
			return new Multiplication(operand1.derivative(), new Constant(operand2.getValue(0)));
		}
		
		// If this line is running the universe is broken and we can all go home
		return null;
	}
	
	@Override
	public Expression reduced() {
		if (!operand1.hasVariable() && operand1.getValue(0) == 1) {
			return operand2.reduced();
		}
		
		if (!operand2.hasVariable() && operand2.getValue(0) == 1) {
			return operand1.reduced();
		}
		
		return new Multiplication(operand1.reduced(), operand2.reduced());
	}

}
