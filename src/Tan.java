import expresions.Constant;
import expresions.Cos;
import expresions.Expression;
import expresions.Function;
import expresions.Multiplication;
import expresions.Power;
import expresions.Sin;

public class Tan extends Function {

	public Tan(Expression operand) {
		super(operand);
	}
	
	@Override
	public double getValue(double varVal) {
		return Math.tan(operand.getValue(varVal));
	}

	@Override
	public String toDisplayString() {
		return String.format(" tan(%s) ", operand.toDisplayString());
	}

	@Override
	public Expression derivative() {
		return new Multiplication(new Power(new Sec(operand), new Constant(2)), operand.derivative());
	}
	
	@Override
	public Expression reduced() {
		if (!operand.hasVariable() && operand.getValue(0) == 0) {
			return new Constant(0);
		}
		return new Tan(operand.reduced());
	}

}
