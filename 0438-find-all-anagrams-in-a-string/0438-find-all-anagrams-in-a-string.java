class Solution {
    static List<Integer> answer;
    public List<Integer> findAnagrams(String s, String p) {
        answer = new ArrayList<>();
        // 연속된 구간이니까, 투포인터로 해볼 수 있을 듯
        // 길이도 정해져 있어서
        int[] cnt = new int[26];
        HashSet<Character> set = new HashSet<>();
        for(char pp: p.toCharArray()) {
            cnt[pp - 'a']++;
            set.add(pp);
        }
        int[] window = new int[26];

        int index = 0;
        int cc = 0;
        int maxCnt = set.size();
        
        for(; index < Math.min(p.length(), s.length()); index++) {
            if (window[s.charAt(index) - 'a'] + 1 == cnt[s.charAt(index) - 'a'] ) {
                cc++;
            }
            window[s.charAt(index) - 'a']++;
        }
        int start = 0;
        int end = index - 1;
        
        while(end < s.length()) {
            if (start == 10001) {
            }
            if (cc == maxCnt) {
                answer.add(start);
            }
            if (window[s.charAt(start) - 'a'] == cnt[s.charAt(start) - 'a']) {
                cc--;
            }
            window[s.charAt(start) - 'a']--;
            start++;
            end++;
            if (end == s.length()) break;
            if (window[s.charAt(end) - 'a'] + 1 == cnt[s.charAt(end) - 'a']) {
                cc++;
            }
            window[s.charAt(end) - 'a']++;
        }
        
        return answer;
    }
}