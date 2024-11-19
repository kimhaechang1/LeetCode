import java.util.*;

class Solution {
    public int longestPalindrome(String s) {
        // 짝수형 펠린드롬과, 홀수형 펠린드롬이 있다.
        // 현재 선택한 문자를 기준으로 만들 수 있는 가장 긴 펠린드롬의 수를 계속 누적한다.
        HashMap<Character, Integer> cnt = new HashMap<>();
        int sum = 0;
        boolean isOdd = false;
        
        for(char ch: s.toCharArray()) cnt.put(ch, cnt.getOrDefault(ch, 0) + 1);
        // 홀수수의 빈도수를 가진것은 -1을 통해 짝수개수만 들고오면 된다.
        // 그리고 마지막 홀수개수의 문자는 홀수만으로도 펠린드롬유지에 문제가 없다.
        for(Map.Entry<Character, Integer> entry: cnt.entrySet()) {
            int c = entry.getValue();
            if ((c & 1) != 1) {
                sum += c;
            } else {
                sum += (c - 1);
                isOdd = true;
            }
        }
        return isOdd ? sum + 1 : sum;
    }
}