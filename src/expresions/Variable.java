package expresions;

public class Variable implements Expression {
	
	public Variable() {}

	@Override
	public double getValue(double varVal) {
		return varVal;
	}

	@Override
	public String toDisplayString() {
		return "x";
	}
	
	@Override
	public boolean hasVariable() {
		return true;
	}

	@Override
	public Expression derivative() {
		return new Constant(1);
	}
	
}
