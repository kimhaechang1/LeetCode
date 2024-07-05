class Solution {
    public int removeDuplicates(int[] nums) {
        boolean [] arr = new boolean[201];
        for(int i: nums){
            if(!arr[i+100]) arr[i+100] = true;
        }
        ArrayList<Integer> list= new ArrayList<>();
        for(int i= 0;i<201;i++){
            if(arr[i]) list.add(i-100);
        }
        for(int i= 0;i<list.size();i++){
            nums[i] = list.get(i);
        }
        return list.size();
    }
}