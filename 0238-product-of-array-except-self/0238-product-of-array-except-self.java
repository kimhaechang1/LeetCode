import java.util.*;

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] origin = new int[nums.length + 1];
        for(int i = 0;i<nums.length;i ++) {
            origin[i + 1] = nums[i];
        }
        int[] L = new int[nums.length + 1];
        int[] R = new int[nums.length + 1];
        L[0] = 1;
        R[origin.length - 1] = 1;
        
        for(int i = 1;i<origin.length;i ++) {
            L[i] = L[i - 1] * origin[i];
        }

        for(int i = origin.length - 2;i > -1;i--) {
            R[i] = R[i + 1] * origin[i + 1];
        }

        ArrayList<Integer> answer =new ArrayList<>();
        answer.add(R[1]);
        
        for(int i = 2;i<=origin.length-2;i++) {
            answer.add(L[i - 1] * R[i]);
        }
        answer.add(L[origin.length - 2]);
        int[] ans = new int[answer.size()];
        for(int i = 0;i<answer.size();i++) {
            ans[i] = answer.get(i);
        }
        return ans;
    }
}