import java.util.Stack;

public class Validation {
    public static boolean isValid(String expression) {
        Stack<Character> stack = new Stack<>();
        boolean lastWasOperator = false;
        boolean lastWasOperand = false;
        int count = 0;
        for (char ch : expression.toCharArray()) {
            if (ch == ' ') {
                continue;
            }else if (ch == '(') {
                stack.push(ch);
                lastWasOperator = false;
                lastWasOperand = false;
                count = 0;
            } else if (ch == ')') {
                if (stack.isEmpty() || lastWasOperator || count < 2) return false;
                stack.pop();
                lastWasOperand = true;
                lastWasOperator = false;
                count = 1;
            } else if (isOperator(ch)) {
                if (lastWasOperator && ch != '~') return false;
                lastWasOperator = true;
                lastWasOperand = false;
            } else if (Character.isLetter(ch)) {
                if (lastWasOperand) return false;
                lastWasOperand = true;
                lastWasOperator = false;
                count++;
            } else {
                return false;
            }
        }
        return stack.isEmpty() && !lastWasOperator && count <= 2;
    }

    private static boolean isOperator(char ch) {
        return ch == '~' || ch == '^' || ch == 'v' || ch == '>';
    }
    public static void main(String[] args) {
        String[] testExpressions = {
                "P ^ Q v R",          // Valid
                "(P ^ Q) v R",        // Valid
                "P ^ Q v R ^",        // Invalid: ends with an operator
                "(P ^ Q v R",         // Invalid: unmatched parenthesis
                "P ^ (Q v R)",        // Valid
                "P ^ Q R",            // Invalid: no operator between Q and R
                "P ^ (Q v R) ^ S",    // Valid
                "(P v Q) ^ (R > S)",  // Valid
                "P ^ (Q > (R ^ S))",  // Valid
                "P ^ Q v",            // Invalid: ends with an operator
                "((P ^ Q))",          // Valid
                "P ^ Q) v R",         // Invalid: unmatched parenthesis
                "(P ^ (Q v R)) v",    // Invalid: ends with an operator
                "P ^ Q v R ^ S",      // Valid
                "P v (Q ^ R)",        // Valid
                "(P ^ Q) v (R ^ S)",  // Valid
                "(P ^ Q) R",          // Invalid: no operator between Q and R
        };

        for (String expression : testExpressions) {
            System.out.println("Expression: \"" + expression + "\" is valid: " + Validation.isValid(expression));
        }
    }
}
