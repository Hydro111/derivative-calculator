import expresions.*;

public class ExpressionTools {

	public static Expression parseToExpresion(String text) {
		String workingText;
		// Clean up input (remove extra whitespace, parentheses, etc.)
		// Remove whitespace
		workingText = text.trim();
		// Remove parentheses
		int parenthesesCount = 0;
		if (workingText.length() > 0 && workingText.charAt(0) == '(' && workingText.charAt(workingText.length() - 1) == ')') {
			
			boolean totallyEnclosed = true;
			for (int i = 0; i < workingText.length(); i++) {
				if (workingText.charAt(i) == '(') { parenthesesCount++; }
				
				if (parenthesesCount == 0) {
					totallyEnclosed = false;
					break;
				}
				
				if (workingText.charAt(i) == ')') { parenthesesCount--; }
				
			}
			
			if (totallyEnclosed) {
				return parseToExpresion(workingText.substring(1, workingText.length()-1 ));
			}
		}
		
		// We need to parse each operation in the *reverse* of order of operations. This is because we're
		// building the expression from root to leaf in the recursive object structure. We start at the root
		// with this recursive function, and the layers of recursion should match in the function and the
		// object tree.
		// Thus, we ignore anything in parentheses, it is last in our order. Only if the entire function is
		// contained in parentheses, and so there is no other option, do we peel back the parentheses.
		// We look for addition and subtraction, then multiplication and division, then powers, then special
		// functions, like trigonometric functions.
		
		// Addition / subtraction
		parenthesesCount = 0;
		for (int i = 0; i < workingText.length(); i++) {
			// Manage the parentheses - if we enter a layer of them, add one. If we exit one, subtract one.
			// this means parentheses count will always be the depth of parentheses we're in
			if (workingText.charAt(i) == '(') { parenthesesCount++; }
			if (workingText.charAt(i) == ')') { parenthesesCount--; }
			
			if (parenthesesCount == 0 && workingText.charAt(i) == '+') {
				return new Addition(parseToExpresion(workingText.substring(0, i)), parseToExpresion(workingText.substring(i+1)));
			}
			
			if (parenthesesCount == 0 && workingText.charAt(i) == '-') {
				return new Subtraction(parseToExpresion(workingText.substring(0, i)), parseToExpresion(workingText.substring(i+1)));
			}
		}
		
		// Multiplication / division
		parenthesesCount = 0;
		for (int i = 0; i < workingText.length(); i++) {
			// Manage the parentheses - if we enter a layer of them, add one. If we exit one, subtract one.
			// this means parentheses count will always be the depth of parentheses we're in
			if (workingText.charAt(i) == '(') { parenthesesCount++; }
			if (workingText.charAt(i) == ')') { parenthesesCount--; }
			
			if (parenthesesCount == 0 && workingText.charAt(i) == '*') {
				return new Multiplication(parseToExpresion(workingText.substring(0, i)), parseToExpresion(workingText.substring(i+1)));
			}
			
			if (parenthesesCount == 0 && workingText.charAt(i) == '/') {
				return new Division(parseToExpresion(workingText.substring(0, i)), parseToExpresion(workingText.substring(i+1)));
			}
		}
		
		// Powers
		parenthesesCount = 0;
		for (int i = 0; i < workingText.length(); i++) {
			// Manage the parentheses - if we enter a layer of them, add one. If we exit one, subtract one.
			// this means parentheses count will always be the depth of parentheses we're in
			if (workingText.charAt(i) == '(') { parenthesesCount++; }
			if (workingText.charAt(i) == ')') { parenthesesCount--; }
			
			if (parenthesesCount == 0 && workingText.charAt(i) == '^') {
				return new Power(parseToExpresion(workingText.substring(0, i)), parseToExpresion(workingText.substring(i+1)));
			}
		}
		
		// If this isn't any of the above, it must be a trigonometric function
		if (workingText.length() > 3 && workingText.substring(0, 3).equals("sin")) {
			return new Sin(parseToExpresion(workingText.substring(3)));
		}
		
		if (workingText.length() > 3 && workingText.substring(0, 3).equals("cos")) {
			return new Cos(parseToExpresion(workingText.substring(3)));
		}
		
		if ( workingText.equals("x")) {
			return new Variable();
		}
		
		return new Constant(Double.valueOf(workingText));
		
		//throw new ParseException("Parsing failed on text: \'" + text + "\'. Working text: \'" + workingText + "\'", 0);
	}

}
