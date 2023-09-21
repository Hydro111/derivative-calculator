import java.util.Scanner;

import expresions.Expression;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Expression to differentiate (exit with \'exit\'): ");
			String input = scanner.nextLine();
			
			if (input.toLowerCase().trim().equals("exit")) { break; }
			
			Expression testExpression = ExpressionTools.parseToExpresion(input).derivative().reduced();
			
			System.out.println(testExpression.toDisplayString());
		}
		
	}

}
