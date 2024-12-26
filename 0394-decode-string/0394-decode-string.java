class Solution {
    static StringBuilder total;
    static int index;
    public String decodeString(String s) {
        // 인코딩 규칙
        // k[인코딩된 문자열]이 가지는 의미는 브라켓 내에 문자열이 k번 반복된다는 것
        // k는 양의 정수임이 보장된다.

        // 디코딩된 문자열을 반환하라
        // k는 언제나 일반 문자열이 아닌 반복횟수로서의 의미만 가진다.
        total = new StringBuilder();
        char[] chs = s.toCharArray();
        index = 0;
        for(;index < chs.length;) {
            if (isDigit(chs[index])) {
                total.append(dfs(chs, s));
            } else if (isAlpha(chs[index])){
                total.append(chs[index]);
                index++;
            }
        }
        return total.toString();
        
    }
    static String dfs(char[] frags, String str) {
        
        int start = index;
        while(index < frags.length && isDigit(frags[index])) {
            index++;
        }
        StringBuilder make = new StringBuilder();
        int cnt = Integer.parseInt(str.substring(start, index));
        StringBuilder sub = new StringBuilder();
        for(; index < frags.length;) {
            if (index < frags.length && frags[index] == '[') {
                index++;
                continue;
            }
            if (index < frags.length && isAlpha(frags[index])) {
                sub.append(frags[index]);
                index++;
            } 
            if (index < frags.length && isDigit(frags[index])) {
                sub.append(dfs(frags, str));
            } 
            if (index < frags.length && frags[index] == ']') {
                index++;
                break;
            }
        }
        while(cnt-- > 0) {
            make.append(sub.toString());
        }
        return make.toString();
    }
    static boolean isDigit(char ch) {
        return ch - '0' > -1 && ch - '0' < 10;
    }
    static boolean isAlpha(char ch) {
        return ch - 'a' >= 0 && ch - 'a' <= 26;
    }
}