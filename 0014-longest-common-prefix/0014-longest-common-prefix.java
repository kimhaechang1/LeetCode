class Solution {
    static String answer;
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        if (strs.length == 1) return strs[0];
        char[] firsts = strs[0].toCharArray();
        answer = "";
        outter: for(int i = 1;i < firsts.length; i++) {
            String sub = strs[0].substring(0, i);
            for(int j = 1;j < strs.length; j++) {
                if (!strs[j].startsWith(sub)) {
                    break outter;
                }
            }
            answer = sub;
        }
        return answer;
    }
}