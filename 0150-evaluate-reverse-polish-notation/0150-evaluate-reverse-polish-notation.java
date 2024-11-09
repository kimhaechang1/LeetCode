import java.util.*;

class Solution {
    public int evalRPN(String[] tokens) {
        int ans = 0;
        Stack<String> stack = new Stack<>();
        for(int i = 0;i<tokens.length;i++) {
            String token = tokens[i];
            if (isOP(token)) {
                int operand1 = Integer.parseInt(stack.pop());
                int operand2 = Integer.parseInt(stack.pop());
                stack.push(doExpr(operand2, operand1, token));
            } else {
                stack.push(token);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public boolean isOP(String str) {
        return "+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str);
    }
    
    public String doExpr(int a, int b, String op) {
        if ("+".equals(op)) {
            return String.valueOf(a + b);
        } else if ("-".equals(op)) {
            return String.valueOf(a - b);
        } else if ("*".equals(op)) {
            return String.valueOf(a * b);
        } else {
            return String.valueOf(a / b);
        }
    }
}