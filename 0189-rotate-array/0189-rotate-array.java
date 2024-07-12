import java.util.*;
class Solution {
    public void rotate(int[] nums, int k) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i= 0;i<nums.length;i++){
            queue.addLast(nums[i]);
        }
        while(k-- > 0){
            queue.addFirst(queue.pollLast());
        }
        for(int i= 0;i<nums.length;i++){
            nums[i] = queue.pollFirst();
        }
    }
}