import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a logical expression: ");
        String expr = scanner.nextLine();
        LogicalExpression expression = new LogicalExpression(expr);
        if (!Validation.isValid(expression.getRepresentation())) {
            System.out.println("Wrong expression");
        } else {
            Set<Character> variables = extractVariables(expression.getRepresentation());
            Map<Character, Boolean> valueOfVariables = new HashMap<>();
            for (char var : variables) {
                while (true) {
                    System.out.print("Enter value for " + var + " (true/false): ");
                    if (scanner.hasNextBoolean()) {
                        valueOfVariables.put(var, scanner.nextBoolean());
                        break;
                    } else {
                        System.out.println("Invalid input.");
                        scanner.next(); 
                    }
                }
            }
            expression.setVariableValues(valueOfVariables);
            LogicalExpressionSolver solver = new Evaluator();
            boolean result = solver.evaluateExpression(expression);
            System.out.println("The result of the expression is: " + result);
        }
        scanner.close();
    }
    private static Set<Character> extractVariables(String expression) {
        Set<Character> variables = new HashSet<>();
        for (char ch : expression.toCharArray()) {
            if (Character.isLetter(ch) && ch != 'v') {
                variables.add(ch);
            }
        }
        return variables;
    }
}
