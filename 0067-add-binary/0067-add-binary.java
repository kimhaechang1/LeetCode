class Solution {
    public String addBinary(String a, String b) {
        Stack<Character> aStack = new Stack<>();
        Stack<Character> bStack = new Stack<>();
        for(char c: a.toCharArray()) aStack.push(c);
        for(char c: b.toCharArray()) bStack.push(c);
        Stack<Character> result = new Stack<>();
        boolean isCarry = false;
        while(check(aStack, bStack)) {
            if (!aStack.isEmpty() && !bStack.isEmpty()) {
                //System.out.println("둘다 안빔: "+execute(aStack.peek(), bStack.peek(), isCarry));
                result.push(execute(aStack.peek(), bStack.peek(), isCarry));
                isCarry = isOccurCarry(aStack.peek(), bStack.peek(), isCarry);
                //System.out.println("isCarry: "+isCarry);
                aStack.pop();
                bStack.pop();
            } else if (aStack.isEmpty() && !bStack.isEmpty()) {
                if (isCarry && bStack.peek() == '1') {
                    result.push('0');
                    isCarry = true;
                } else if (isCarry && bStack.peek() == '0') {
                    result.push('1');
                    isCarry = false;
                } else if (!isCarry) {
                    result.push(bStack.peek());
                }
                bStack.pop();
            } else if (!aStack.isEmpty() && bStack.isEmpty()) {
                if (isCarry && aStack.peek() == '1') {
                    
                    result.push('0');
                    isCarry = true;
                } else if (isCarry && aStack.peek() == '0') {
                    
                    result.push('1');
                    isCarry = false;
                } else if (!isCarry) {
                    result.push(aStack.peek());
                }
                aStack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        if (isCarry) sb.append('1');
        while(!result.isEmpty()) {
            sb.append(result.peek());
            result.pop();
        }

        
        return sb.toString();
    }
    static boolean check(Stack<Character> s1, Stack<Character> s2) {
        return !s1.isEmpty() || !s2.isEmpty();
    }
    static boolean isOccurCarry(char c1, char c2, boolean isCarry) {
        if (isCarry) {
            if (c1 == '1' && c2 == '0') return true;
            if (c1 == '0' && c2 == '1') return true;
            if (c1 == '1' && c2 == '1') return true;
        } else {
            if (c1 == '1' && c2 == '1') return true;
        }
        return false;
    }
    static char execute(char c1, char c2, boolean isCarry) {
        if (isCarry) {
            if (c1 == '1' && c2 == '0') return '0';
            if (c1 == '0' && c2 == '1') return '0';
            if (c1 == '0' && c2 == '0') return '1';
            if (c1 == '1' && c2 == '1') return '1';
        } else {
            if (c1 == '1' && c2 == '0') return '1';
            if (c1 == '0' && c2 == '1') return '1';
            if (c1 == '0' && c2 == '0') return '0';
            if (c1 == '1' && c2 == '1') return '0';
        }
        return '1';
    }
 
}