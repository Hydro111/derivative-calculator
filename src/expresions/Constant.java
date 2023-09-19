package expresions;
import java.lang.Double;

public class Constant implements Expression {

	protected double value;
	
	public Constant(double value) {
		this.value = value;
	}

	@Override
	public double getValue(double varVal) {
		return value;
	}

	@Override
	public String toDisplayString() {
		return Double.toString(value);
	}

	@Override
	public boolean hasVariable() {
		return false;
	}

	@Override
	public Expression derivative() {
		return new Constant(0);
	}

}
