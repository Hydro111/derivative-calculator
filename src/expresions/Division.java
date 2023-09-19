package expresions;
public class Division extends Operator {

	public Division(Expression operand1, Expression operand2) {
		super(operand1, operand2);
	}

	@Override
	public double getValue(double varVal) {
		return operand1.getValue(varVal) / operand2.getValue(varVal);
	}

	@Override
	public String toDisplayString() {
		return String.format("(%1$s / %2$s)", operand1.toDisplayString(), operand2.toDisplayString());
	}

	@Override
	public Expression derivative() {
		boolean hasVariable1 = operand1.hasVariable();
		boolean hasVariable2 = operand2.hasVariable();
		
		// Quotient rule
		if ( hasVariable1 &&  hasVariable2) {
			return new Division(
				new Subtraction(
					new Multiplication(operand1.derivative(), operand2),
					new Multiplication(operand1, operand2.derivative())
				),
				new Power(
					operand2,
					new Constant(2)
				)
			);
		}
		
		// It's a constant
		if (!hasVariable1 && !hasVariable2) {
			return new Constant(this.getValue(0));
		}
		
		// This is the same as multiplying by a constant
		if (!hasVariable1 &&  hasVariable2) {
			return new Division(operand1.derivative(), new Constant(operand2.getValue(0)));
		}
		
		// Quotient rule (special case)
		if ( hasVariable1 && !hasVariable2) {
			return new Division(
				new Multiplication(new Constant(-operand1.getValue(0)), operand2.derivative()),
				new Power(operand2, new Constant(2))
			);
		}
		
		return null;
	}
	
	@Override
	public Expression reduced() {
		if (!operand2.hasVariable() && operand2.getValue(0) == 1) {
			return operand1.reduced();
		}
		
		return new Division(operand1.reduced(), operand2.reduced());
	}

}
