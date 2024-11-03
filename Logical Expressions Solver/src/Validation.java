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
    }

