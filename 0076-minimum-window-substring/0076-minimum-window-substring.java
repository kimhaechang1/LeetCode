class Solution {
    public String minWindow(String s, String t) {
        // s, t, 연속적인 문자열들 중 t 문자열내에 문자들이 포함되어있는 부분 문자열중 가장 작은 부분문자열
        
        HashMap<Character, Integer> origin = new HashMap<>();
        for(char ch: t.toCharArray()) origin.put(ch, origin.getOrDefault(ch, 0) + 1);
        HashMap<Character, Integer> window = new HashMap<>();
        char[] frags = s.toCharArray();
        int start = 0;
        int end = start;
        int answerLen = s.length();
        int targetLen = t.length();
        String answer = "";
        while(end < s.length()) {
            int len = end - start + 1;
            window.put(frags[end], window.getOrDefault(frags[end], 0) + 1);
            if (len >= targetLen) {
                while(start <= end && check(origin, window)) {
                    if (answerLen >= end - start + 1) {
                        answerLen = end - start + 1;
                        answer = s.substring(start, end + 1);
                    }
                    window.put(frags[start], window.getOrDefault(frags[start], 0) - 1);
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