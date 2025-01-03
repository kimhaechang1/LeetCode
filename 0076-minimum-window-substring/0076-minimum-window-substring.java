class Solution {
    public String minWindow(String s, String t) {
        // s, t, 연속적인 문자열들 중 t 문자열내에 문자들이 포함되어있는 부분 문자열중 가장 작은 부분문자열
        // 두포인터로 푸는데, 첫 풀이는 시간복잡도가 O(T * S) 였다.
        // 이를 O(T) 수준으로 낮추는 핵심 로직은, 어짜피 필요한 문자는 정해져있고, 현재 윈도우의 길이가 T가 되는 순간이 아닌
        // t 문자열의 요구사항을 갖춘 부분 문자열이 되는순간을 찾아야 한다.
        HashMap<Character, Integer> origin = new HashMap<>();
        for(char ch: t.toCharArray()) origin.put(ch, origin.getOrDefault(ch, 0) + 1);
        HashMap<Character, Integer> window = new HashMap<>();
        char[] frags = s.toCharArray();
        int start = 0;
        int end = start;
        int answerLen = s.length();
        int targetLen = t.length();
        int cnt = 0; // 윈도우 내에 종류별 필요개수를 만족한 개수
        int maxCnt = origin.size(); // 그러한 종류의 최대치
        String answer = "";
        while(end < s.length()) {
            int len = end - start + 1;
            if (origin.containsKey(frags[end])) {
                int wc = window.getOrDefault(frags[end], 0);
                if (wc + 1 == origin.get(frags[end])) {
                    // 해당 종류의 요구치를 만족한 경우 요구치 만족개수를 증가시킨다.
                    cnt++;
                }
                window.put(frags[end], wc + 1);
            }
            if (cnt == maxCnt) {
                while(start <= end) {
                    if (answerLen >= end - start + 1) {
                        answerLen = end - start + 1;
                        answer = s.substring(start, end + 1);
                    }
                    if (origin.containsKey(frags[start])) {
                        int wc = window.get(frags[start]);
                        if (wc - 1 < origin.get(frags[start])) {
                            break;
                        }
                        window.put(frags[start], wc - 1);
                    }
                    start++;
                }
            }
            end++;
        }
        return answer;
    }
    static boolean check(HashMap<Character, Integer> o, HashMap<Character, Integer> w) {
        for(char key: o.keySet()) {
            if (w.getOrDefault(key, 0) < o.get(key)) return false;
        }
        return true;
    }
}
