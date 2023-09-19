package expresions;
public abstract class Operator implements Expression {

	protected Expression operand1;
	protected Expression operand2;
	
	public Operator(Expression operand1, Expression operand2) {
		this.operand1 = operand1;
		this.operand2 = operand2;
	}
	
	@Override
	public boolean hasVariable() {
		return operand1.hasVariable() || operand2.hasVariable();
	}
}
