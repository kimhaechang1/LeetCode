class Solution {
    public int characterReplacement(String s, int k) {
        // 문자열 s와 정수 k가 주어진다.
        // 문자열내에 어떤 문자를 선택할수 있고, 그것달 다른 대문자 영문자로 바꿀 수 있다.
        // 이 과정을 최대 k번 수행할 수 있다.

        // 동일한 문자를 포함하는 가장 긴 부분문자열의 길이를 반환하라.

        // 연속적이어야 하고 그 길이를 찾아야 하니까

        // 문자열 내에 서로다른 문자가 몇개있는지 검사해야 할듯
        int start = 0;
        int end = 0;
        int[] cnt = new int[26];
        char[] chs = s.toCharArray();
        int answer = 0;
        int maxCnt = 0;
        // 26개의 알파벳을 매순간 순회하며 관리 할 필요가 없음
        // 왜냐하면 연속된 구간내에 알파벳의 CNT가 변경되는 순간은 start포인터나 end 포인터가 움직일 때 뿐임
        while(end < s.length()) {
            cnt[chs[end] - 'A']++;

            maxCnt = Math.max(maxCnt, cnt[chs[end] - 'A']);
            if (end - start + 1 - maxCnt > k) {
                cnt[chs[start] - 'A']--;
                start++;
                end++;
            } else {
                answer = Math.max(answer, end - start + 1);
                end++;
            }
        }
        return answer;
    }
}