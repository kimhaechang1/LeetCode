class Solution {
    public void nextPermutation(int[] nums) {
        // 다음 순서의 순열을 구하여라

        // 순열의 중요한 특징은, 산봉우리 뒤는 왠만하면 오름차순이란 것

        int n = nums.length;
        int i = n - 1;

        // 뒤에서 부터 높은 산봉우리를 찾고,
        while(i > 0 && nums[i - 1] >= nums[i]) i--;
        if (i == 0) {
            Arrays.sort(nums);
            return;
        }
        // 산봉우리 뒤에서부터, 산봉우리 앞 원소보다 큰 원소를 찾는다.
        int j = n - 1;
        while(nums[i - 1] >= nums[j]) j--;

        int t = nums[j];
        nums[j] = nums[i - 1];
        nums[i - 1] = t;

        // 교환하고나면 산봉우리 뒤 내리막길로 되어있는 원소들을 오르막길로 업 해준다.
        int k = n - 1;
        while(i < k) {
            t = nums[i];
            nums[i] = nums[k];
            nums[k] = t;
            i++;
            k--;
        }
    }
    
}   