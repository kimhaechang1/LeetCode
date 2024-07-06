class Solution {
    public int findPeakElement(int[] nums) {
        int s= 0;
        int e= nums.length-1;
        int result = nums.length-1;
        // 갑자기 내려가는 구간을 찾으면 깎아 올라가고
        // 올라가는 구간이면 더 좁혀서 내려가는 구간을 찾는다.
        // if(e == 0) return 0;
        while(s < e){
            System.out.println("s: "+s+" e: "+e);
            int mid = (s+e) / 2;
            if(nums[mid] < nums[mid+1]){
                s = mid + 1;
            }else{
                e = mid;
                result = Math.min(result, mid);
            }
        }
        
        return result;
    }
}