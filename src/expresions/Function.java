package expresions;
public abstract class Function implements Expression {

	protected Expression operand;
	
	public Function(Expression operand) {
		this.operand = operand;
	}
	
	@Override
	public boolean hasVariable() {
		return operand.hasVariable();
	}

}
