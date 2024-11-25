class Solution {
    public int longestPalindrome(String s) {
        // 주어진 문자열 s는 대문자 소문자가 포함되어있다.
        // 그걸 가지고 가장 긴 펠린드롬의 길이를 반환하라

        // 펠린드롬은 짝수 펠린드롬이 있고, 홀수 펠린드롬이 있다.
        // 연속적일 필요가 없다. 펠린드롬을 가장 길게 만들려면 가능한한 모든 데이터가 짝수여야 한다.
        // 그리고 짝수 펠린드롬인 경우에는 모든 알파벳이 짝수개 나온경우고, 홀수 펠린드롬인 경우에는 하나의 홀수개를 제외하고서는
        // 모두가 짝수개인 알파벳인 경우다.
        // 여기서 중요한 것은 하나를 제외하면 모두가 짝수개여야 한다는 것
        // 특정 홀수를 짝수개만큼 쓸 수 있으므로, -1개만큼만 써서 짝수개만 사용할 수 있다.
        // 그렇게 모든 짝수를 사용하고 홀수도 짝수로만들어서 사용하다가, 사실 마지막 홀수개 알파벳은 홀수개 그대로 사용해도 펠린드롬에 문제가 없다.


        HashMap<Character, Integer> map = new HashMap<>();
        char[] chs = s.toCharArray();
        int answer = 0;
        for(char ch: chs) map.put(ch, map.getOrDefault(ch, 0) + 1);
        boolean isOdd = false;
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            int cnt = entry.getValue();
            if ((cnt & 1) != 1) {
                answer += (cnt);
            } else {
                isOdd = true;
                answer += (cnt - 1);
            }
        }
        return isOdd ? answer + 1 : answer;
    }
}