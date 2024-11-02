import java.util.Stack;

public class Validation {
    public static boolean isValid(String expression) {
        Stack<Character> stack = new Stack<>();
        boolean lastWasOperator = false;
        boolean lastWasOperand = false;
        if (!expression.isEmpty() && isOperator(expression.charAt(0)) && expression.charAt(0) != '~') {
            return false;
        }
        for (char ch : expression.toCharArray()) {
            if (ch == ' ') continue;
            if (ch == '(') {
                stack.push(ch);
                lastWasOperator = false;
                lastWasOperand = false;
            } else if (ch == ')') {
                if (stack.isEmpty() || lastWasOperator) return false;
                stack.pop();
                lastWasOperand = true;
                lastWasOperator = false;
            } else if (isOperator(ch)) {
                if (lastWasOperator && ch != '~') return false;
                lastWasOperator = true;
                lastWasOperand = false;
            } else if (Character.isLetter(ch)) {
                if (lastWasOperand) return false;
                lastWasOperand = true;
                lastWasOperator = false;
            } else{
                return false;
            }
        }
        return stack.isEmpty() && !lastWasOperator;
    }
    private static boolean isOperator(char ch) {
        return ch == '~' || ch == '^' || ch == 'v' || ch == '>';
    }
}
