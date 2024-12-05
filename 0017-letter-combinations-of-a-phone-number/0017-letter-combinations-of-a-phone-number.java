class Solution {
    static List<String> answer;
    static boolean[] v;
    static char[] result;
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();
        answer = new ArrayList<>();
        char[][] all = {
            {},
            {},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
        };
        result = new char[digits.length()];
        // 2 ~ 9가 포함된 숫자로된 문자열을 줄 것이다.
        // 너는 모든 가능한 문자조합들을 순서 상관없이 반환하면 된다.
        

        dfs(all, 0, digits, digits.length());

        return answer;
    }
    static void dfs(char[][] all, int depth, String digits, int n) {
        if (depth == n) {
            StringBuilder sb = new StringBuilder();
            for(char ch: result) sb.append(ch);
            answer.add(sb.toString());
            return;
        }
        int idx = digits.charAt(depth) - '0';
        for(int i = 0;i < all[idx].length; i++) {
            result[depth] = all[idx][i];
            dfs(all, depth + 1, digits, n);
        }
    }
}