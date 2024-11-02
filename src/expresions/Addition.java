package expresions;

import java.util.LinkedList;
import java.util.List;

public class Addition extends OperatorCommutative {
	
	public Addition(Expression... expressions) {
		super(expressions);
	}

	@Override
	public double getValue(double varVal) {
		double out = 0.0;
		for (Expression operand : operands) {
			out += operand.getValue(varVal);
		}
		return out;
	}

	@Override
	public String toDisplayString() {
		String outString = "(";
		for (Expression operand : operands) {
			outString = outString.concat(operand.toDisplayString().concat(" + "));
		}
		return outString.substring(0,outString.length()-3).concat(")");
	}

	@Override
	public Expression derivative() {
		Expression[] derivatives = new Expression[operands.length];
		for (int i = 0; i < derivatives.length; i++) {
			derivatives[i] = operands[i].derivative();
		}
		return new Addition(derivatives);
	}
	
	@Override
	public Expression reduced() {
		List<Expression> expressions = new LinkedList<Expression>();
		
		for (Expression operand : operands) {
			if (operand.hasVariable() || operand.getValue(0) != 0) {
				expressions.add(operand.reduced());
			}
		}
		
		return new Addition(expressions.toArray(new Expression[expressions.size()]));
	}
}
