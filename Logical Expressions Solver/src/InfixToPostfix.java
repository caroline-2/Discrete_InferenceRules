import java.util.*;

public class InfixToPostfix {
    private static int precedence(char c) {
        switch (c){
            case '~': return 4;
            case '^': return 3;
            case 'v': return 2;
            case '>': return 1;
            default: return -1;
        }
    }
    public static String convert(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if(c == ' '){
                continue;
            }else if (c == '(') {
                st.push(c);
            } else if (Character.isLetter(c) && c != 'v') {
                result.append(c);
            } else if (c == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    result.append(st.pop());
                }
                if (!st.isEmpty()) st.pop();
            } else {
                while (!st.isEmpty() && precedence(c) <= precedence(st.peek())) {
                    result.append(st.pop());
                }
                st.push(c);
            }
        }
        while (!st.isEmpty()) {
            result.append(st.pop());
        }
        return result.toString();
    }
}