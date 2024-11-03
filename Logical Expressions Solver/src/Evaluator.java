import java.util.*;

public class Evaluator implements LogicalExpressionSolver {
    @Override
    public boolean evaluateExpression(Expression expression) {
        LogicalExpression logicalExpr = (LogicalExpression) expression;
        String postfix = InfixToPostfix.convert(logicalExpr.getRepresentation());
        Map<Character, Boolean> values = logicalExpr.getVariableValues();
        return evaluate(postfix, values);
    }
    public static boolean evaluate(String postfix, Map<Character,Boolean> variableValues) {
        Stack<Boolean> stack = new Stack<>();

        for (char ch : postfix.toCharArray()) {
            if (Character.isLetter(ch) && ch != 'v') {
                Boolean value = variableValues.get(ch);
                stack.push(value);
            } else {
                if (ch == '~') {
                    boolean operand = stack.pop();
                    stack.push(!operand);
                } else {
                    boolean operand2 = stack.pop();
                    boolean operand1 = stack.pop();
                    switch (ch) {
                        case '^':
                            stack.push(operand1 && operand2);
                            break;
                        case 'v':
                            stack.push(operand1 || operand2);
                            break;
                        case '>':
                            stack.push(!operand1 || operand2);
                            break;
                    }
                }
            }
        }
        return stack.pop();
    }
}

