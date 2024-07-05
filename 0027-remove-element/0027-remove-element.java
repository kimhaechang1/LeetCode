import java.util.*;

class Solution {
    public int removeElement(int[] nums, int val) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i: nums){
            if(i == val) continue;
            list.add(i);
        }
        for(int i = 0;i<list.size();i++){
            nums[i] = list.get(i);
        }
        return list.size();
    }
}