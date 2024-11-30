class Solution {
    static String answer;
    static int maxLen;
    public String longestPalindrome(String s) {
        answer = s.substring(0,1);
        maxLen = 1;
        // 가장 긴 펠린드롬 부분수열을 찾아라
        // 펠린드롬이 될려면 전체가 홀수인 펠린드롬과 짝수인 펠린드롬이 있다.
        // 그리고 양쪽에 같은 변위상에 알파벳이 동일해야 한다.
        // 전체가 짝수일려면 가운데도 짝수고, 전체가 홀수일려면 가운데가 홀수고 나머지는 짝수여야 한다.
        char[] frags = s.toCharArray();
        for(int i = 1;i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                go(i - 1, i, s, frags);
            }
            go(i - 1, i + 1, s, frags);
        }
        return answer;
    }
    static void go(int s, int e, String str, char[] frags) {
        while(s > -1 && e < frags.length && frags[s] == frags[e]) {
            int len = e - s + 1;
            if (maxLen < len) {
                maxLen = len;
                answer = str.substring(s, e + 1);
            }
            e++;
            s--;
        }
    }
}