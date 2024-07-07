class Solution {
    static class Data implements Comparable<Data>{
        int idx;
        int value;
        public Data(int idx, int value){
            this.idx = idx;
            this.value = value;
        }
        public int compareTo(Data o){
            return this.value - o.value;
        }
        public String toString(){
            return "[" + this.value+", "+this.idx+"]";
        }

    }
    public int search(int[] nums, int target) {
        // 중복된 원소가 없고, 오름차순 정렬되어있는 nums 배열
        // 원래 오름차순인데 k번째를 기준으로 로테이션 되어있다.
        // target 숫자가 로테이션 후 배열에 존재하는지 검사해달라는 뜻
        // 정렬된 원소에서 특정 원소찾기? 이분탐색 ㄱ
        Data[] drr = new Data[nums.length];
        for(int i = 0;i<drr.length;i++){
            drr[i] = new Data(i, nums[i]);
        }
        Arrays.sort(drr);
        int s = 0;
        int e = drr.length-1;
        while(s <= e){
            int mid = (s+e)/2;
            if(drr[mid].value < target){
                s = mid + 1;
            }else if(drr[mid].value > target){
                e = mid - 1;
            }else{
                return drr[mid].idx;
            }
        }
        return -1;

    }
}