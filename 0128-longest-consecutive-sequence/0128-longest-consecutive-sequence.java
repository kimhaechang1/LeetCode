class Solution {
    public int longestConsecutive(int[] nums) {
        // 가장 긴 연속적인 요소 시퀀스의 길이
        // 어떤 숫자를 기준으로 자기의 -1 의 요소 개수와
        // 자기의  + 1 요소의 개수의 합 + 1이 연속 요소의 개수다.

        // 현재위치만 기록하지 말고, 좀 더 생각해보자.
        // 위치만 기록하는것이 아니라 해당 숫자가 추가되면서 늘어난 연속구간의 양끝점을 업데이트 해준다고 생각하면
        // 예를들어 초기에는 자기점에 연속개수 1개만 기록되고, 양옆에 인접원소까지의 연속 요소의 수를 찾아봐도없다.
        // 그러다가 인접요소가 추가되면 서로가 2 2 개로 기록된다.
        // 그러다가 인접요소가 또 추가되면 그때는 양 끝만 기록하는데, 그것이 가능한이유는? 구간요소 총 개수는 항상 인접 요소에 있기 때문이다.
        
        int ans = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++ ) {
            if (map.containsKey(nums[i])) continue;
            int prev = map.getOrDefault(nums[i] - 1, 0);
            int next = map.getOrDefault(nums[i] + 1, 0);     
            ans = Math.max(ans, prev + next + 1);       
            map.put(nums[i], prev + next + 1);
            map.put(nums[i] - prev, prev + next + 1);
            map.put(nums[i] + next, prev + next + 1);
        }
        return ans;
    }
}