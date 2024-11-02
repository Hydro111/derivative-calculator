import java.util.Scanner;

import expresions.Expression;

public class Main {

	public static void main(String[] args) {
		//System.out.println(ExpressionTools.parseToExpresion("(cos(x*3.141592))^(0-2)").derivative().derivative().getValue(2));
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Expression to differentiate (exit with \'exit\'): ");
			String input = scanner.nextLine();
			
			if (input.toLowerCase().trim().equals("exit")) { break; }
			
			Expression testExpression = ExpressionTools.parseToExpresion(input).derivative().reduced();
			
			System.out.println(testExpression.toDisplayString());
			
			//System.out.println("5th deriv at 0");
			
			//System.out.println(ExpressionTools.parseToExpresion(input).derivative().reduced().derivative().reduced().derivative().reduced().derivative().reduced().derivative().reduced().getValue(0));
		}
		scanner.close();
		
	}

}
