package expresions;

public interface Expression {
	public double getValue(double varVal);
	public String toDisplayString();
	public boolean hasVariable();
	public Expression derivative();
	
	public default Expression reduced() {
		return this;
	}
}
