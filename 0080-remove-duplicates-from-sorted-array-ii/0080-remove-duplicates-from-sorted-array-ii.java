class Solution {
    public int removeDuplicates(int[] nums) {
        int [] cnt = new int[20001];
        for(int i = 0;i<nums.length;i++){
            int idx = nums[i] + 10000;
            if(cnt[idx] + 1 > 2) continue;
            cnt[idx]++;
        }
        ArrayList<Integer> list =new ArrayList<>();
        for(int i = 0;i<20001;i++){
            while(cnt[i]-- > 0) list.add(i - 10000);
        }
        for(int i = 0;i<list.size();i++){
            nums[i] = list.get(i);
        }
        return list.size();
    }
}