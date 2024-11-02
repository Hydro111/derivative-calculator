package expresions;

public abstract class OperatorCommutative implements Expression {
	
	protected Expression[] operands;

	public OperatorCommutative(Expression... expressions) {
		this.operands = expressions;
	}
	
	@Override
	public boolean hasVariable() {
		for (Expression operand : operands) {
			if (operand.hasVariable()) {
				return true;
			}
		}
		return false;
	}
}
