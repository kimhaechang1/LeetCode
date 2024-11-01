class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 가장 반복되지 않는 긴 문자열
        // 두포인터 접근
        // int s = 0, int e = 1;
        // ans = Math.max(ans, (e  - s  +1));
        int start = 0;
        int e = 0;
        int ans = 0;
        HashSet<Character> set = new HashSet<>();
        while(start <= e && e < s.length()) {
            if (set.contains(s.charAt(e))) {
                // e번째 보다 작은 s ~ e - 1동안 포함된 s커서를 옮겨야 한다.
                while(start <= e && s.charAt(start) != s.charAt(e)){
                    set.remove(s.charAt(start));
                    start++;
                }
                start++;
                set.add(s.charAt(e));
                e++;
            } else {
                // 포함된적이 없는 원소라면 넣고 갱신해준다.
                set.add(s.charAt(e));
                ans = Math.max(ans, e - start + 1);
                e++;
            }
        }
        
        return ans;
    }
}