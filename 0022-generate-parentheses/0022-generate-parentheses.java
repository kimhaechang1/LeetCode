class Solution {
    static boolean[] isOpen;
    static List<String> answer;
    public List<String> generateParenthesis(int n) {
        isOpen = new boolean[n * 2];
        answer = new ArrayList<>();
        dfs(0, 0, n);
        return answer;
    }
    static void dfs(int depth, int cnt, int n) {
        if (depth >= 2 * n) {
            if (cnt == n) {
                String maked = make();
                if (!"".equals(maked)) {
                    answer.add(maked);
                }
            }
            return;
        }

        isOpen[depth] = true;
        dfs(depth + 1, cnt + 1, n);
        isOpen[depth] = false;
        dfs(depth + 1, cnt, n);
    }
    static String make() {
        String rt = "";
        StringBuilder sb = new StringBuilder();
        char[] ch = new char[isOpen.length];
        for(int i = 0; i < isOpen.length; i++) {
            char b = isOpen[i] ? '(' : ')';
            sb.append(b);
            ch[i] = b;
        }
        return check(ch) ? sb.toString() : rt;
    }
    static boolean check(char[] brackets) {
        Stack<Character> stack = new Stack<>();
        for(char bracket: brackets) {
            if (stack.isEmpty()) {
                stack.push(bracket);
            } else {
                if (bracket == '(') {
                    stack.push(bracket);
                } else {
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }  
}