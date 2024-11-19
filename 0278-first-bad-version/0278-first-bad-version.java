/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        long s = 1;
        long e = n;
        int ans = n;
        // 1, 3 mid: 2 isBadVersion = true -> 1
        while(s <= e) {
            long mid = (s + e) / 2;
            if (isBadVersion((int)mid)) {
                ans = Math.min(ans, (int)mid);
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }
}