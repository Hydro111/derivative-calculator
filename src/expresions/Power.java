package expresions;

public class Power extends Operator {

	public Power(Expression operand1, Expression operand2) {
		super(operand1, operand2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getValue(double varVal) {
		return Math.pow(operand1.getValue(varVal), operand2.getValue(varVal));
	}

	@Override
	public String toDisplayString() {
		return String.format("(%1$s ^ %2$s)", operand1.toDisplayString(), operand2.toDisplayString());
	}

	@Override
	public Expression derivative() {
		boolean hasVariable1 = operand1.hasVariable();
		boolean hasVariable2 = operand2.hasVariable();
		
		// Haha, good luck
		if ( hasVariable1 &&  hasVariable2) {
			
		}
		
		// It's a constant
		if (!hasVariable1 && !hasVariable2) {
			return new Constant(this.getValue(0));
		}
		
		// We don't know how to do this... yet
		if (!hasVariable1 &&  hasVariable2) {
			
		}
		
		// Power rule! (plus chain rule)
		if ( hasVariable1 && !hasVariable2) {
			return new Multiplication(new Multiplication(
					new Constant(operand2.getValue(0)), 
					new Power(operand1, new Constant(operand2.getValue(0) - 1))), 
					operand1.derivative() 
			);
		}
		
		
		return null;
	}
	
	@Override
	public Expression reduced() {
		if (!operand1.hasVariable() && operand1.getValue(0) == 0) {
			return new Constant(0);
		}
		
		if (!operand1.hasVariable() && operand1.getValue(0) == 1) {
			return new Constant(1);
		}
		
		if (!operand2.hasVariable() && operand2.getValue(0) == 0) {
			return new Constant(1);
		}
		
		if (!operand2.hasVariable() && operand2.getValue(0) == 1) {
			return operand1.reduced();
		}
		
		return new Power(operand1.reduced(), operand2.reduced());
	}

}
