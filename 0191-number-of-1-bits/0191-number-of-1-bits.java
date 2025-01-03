class Solution {
    public int hammingWeight(int n) {
        String binary = Integer.toBinaryString(n);
        int cnt= 0;
        for(int i = 0;i < binary.length();i ++) {
            if (binary.charAt(i) - '0' == 1) {
                cnt++;
            }
        }
        return cnt;
        //       1    1
        // 11 -> 5 -> 2 -> 0
    }
}