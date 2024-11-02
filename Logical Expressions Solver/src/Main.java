import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a logical expression: ");
        String expr = sc.nextLine();
        Validation validation = new Validation();
        InfixToPostfix converter = new InfixToPostfix();
        if (!validation.isValid(expr)) {
            System.out.println("The expression is invalid.");
        } else {
            System.out.println("The expression is valid.");
            String postfix = converter.convert(expr);
            System.out.println("Postfix expression: " + postfix);
        }
        sc.close();
    }
}
