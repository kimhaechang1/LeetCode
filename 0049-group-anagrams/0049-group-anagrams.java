class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 어떤 순서든 상관없이, 같은 에너그램 형태를띄는 그룹을 묶어라
        // 소문자만 포함된다.

        // 문자열 개수는 최대 1만개
        // 문자열 길이의 최대는 100

        int[][] cnts = new int[strs.length][26];
        String[] cntStr = new String[strs.length];
        HashMap<String, List<String>> map = new HashMap<>();
        for(int i = 0;i < strs.length; i++) {
            for(char ch: strs[i].toCharArray()) {
                cnts[i][ch - 'a']++;
            }
        }
        for(int i = 0;i < strs.length;i ++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0;j<26;j++) {
                if (cnts[i][j] > 0) {
                    sb.append((char)('a' + j)).append(cnts[i][j]);
                }
            }
            String ana = sb.toString();
            List<String> list = map.get(ana);
            if (list == null) list = new ArrayList<>();
            list.add(strs[i]);
            map.put(ana, list);
        }

        List<List<String>> answer = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry: map.entrySet()) {
            answer.add(entry.getValue());
        }
        return answer;
    }
}