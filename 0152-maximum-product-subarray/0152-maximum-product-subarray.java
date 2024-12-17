class Solution {
    public int maxProduct(int[] nums) {
        // 가장 큰 곱의 결과를 가지는 부분수열을 찾아라
        // 그 값을 리턴하라

        // 일반적으로 합이 가장큰 부분수열을 찾는것과는 달리, 값이 내려가는경우는 음수를 곱했을 때 뿐이다.
        // 그러면 음수가 홀수개 만큼 존재하지 않도록 하는것이 중요하다.

        // 현재 수열에 포함시키느냐, 포함시키지 않고 해당 수부터 새로운 수열로 이어지느냐
        
        // 다르게 생각해보면 곱을 다루고 있기 때문에 부호에 따라
        // 음수중의 최솟값, 양수중의 최대값이 있을 수 있다.
        // 현재 곱해야할 수가 음수라면, 이전까지 최솟값과 곱했을 때 해당 최솟값이 음수였다면 양수가 될 수 도 있다.
         
         // i 번째 원소까지의 최솟값과, i번째 원소까지의 최대값을 각각 관리해야 한다.

        int minValue = nums[0];
        int maxValue = nums[0];
        int ans = nums[0];
        
        for(int i = 1; i < nums.length; i++) {
            // i번째 원소까지의 최소는, i번째 원소 단독이거나 i번째 원소를 이전까지 최대와 곱하던가 이전까지 최대와 곱하던가 중 최솟값이다.
            int prevMax = maxValue;
            int prevMin = minValue;
            minValue = Math.min(nums[i], Math.min(nums[i] * prevMax, nums[i] * prevMin));
            maxValue = Math.max(nums[i], Math.max(nums[i] * prevMax, nums[i] * prevMin));

            ans = Math.max(ans, Math.max(minValue, maxValue));
        }
        
        return ans;
    }
    
}