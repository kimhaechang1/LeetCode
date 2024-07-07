import java.util.*;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int ptr = nums.length;
        int result = 0;
        while(ptr-- > -1){
            if(nums.length - ptr == k){
                result = nums[ptr];
                break;
            }
        }
        return result;
    }
}