import expresions.Expression;

public class Main {

	public static void main(String[] args) {
		Expression testExpression = ExpressionTools.parseToExpresion("cos(sin(x))").derivative().reduced();
		
		System.out.println(testExpression.toDisplayString());
	}

}
