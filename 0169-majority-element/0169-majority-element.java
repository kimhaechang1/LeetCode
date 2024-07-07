import java.util.*;

class Solution {
    static int n;
    public int majorityElement(int[] nums) {
        n = nums.length;
        Arrays.sort(nums);
        int cnt = (int)Math.ceil((double)n/2);
        int c = 0;
        int prev = -1111111111;
        int pointer = 0;
        for(;pointer<n;pointer++){
            if(prev != nums[pointer]){
                c = 1;
                prev = nums[pointer];
            }else{
                c++;
            }
            if(c == cnt){
                break;
            }
        }
        return nums[pointer];
    }
}