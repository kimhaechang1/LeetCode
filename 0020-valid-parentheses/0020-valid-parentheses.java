class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char[] bks = s.toCharArray();
        for(char bk: bks) {
            if (stack.isEmpty()) {
                stack.push(bk);
            } else {
                char bkp = stack.peek();
                if ( (bkp == '(' && bk == ')') || (bkp == '[' && bk == ']') || (bkp == '{' && bk == '}')) {
                    stack.pop();
                } else if (bk == '(' || bk == '{' || bk == '[') {
                    stack.push(bk);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}