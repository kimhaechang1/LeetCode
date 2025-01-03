class Solution {
    public int hammingWeight(int n) {
        int cnt = 0;
        int bit = 1;
        for(int i = 0;i < 31;i ++) {
            if ((n & bit) == bit) cnt++;
            bit <<= 1;
        }
        return cnt;
    }
}