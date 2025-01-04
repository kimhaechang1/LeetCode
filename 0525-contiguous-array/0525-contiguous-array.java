class Solution {
    public int findMaxLength(int[] nums) {
        // 0과 1의 개수가 같은 가장 긴 부분수열 구하기
        
        // 특정 구간을 찾는것이기 때문에 여러 접근이 가능하다.
        // 0을 -1, 1을 +1로 계산했다고 가정해보자. 그러면 합이 0인구간은 0과 1의 개수가 같은 구간일수밖에 없다.
        // 여기서 0번 인덱스부터 차례대로 부분수열을 구한다고 하면 0인 구간이 0과 1의 개수가 같은 구간이지만
        // 다른 번째 인덱스부터 부분수열을 구하면 합의 결과가 이전 인덱스에서 존재했어야 한다.
        // 왜냐하면 0과1의 개수가 같아서 0을 더한거기 때문

        HashMap<Integer, Integer> map = new HashMap<>();
        // sum, index;
        int sum = 0;
        int ans = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += (nums[i] == 0 ? -1 : nums[i]);
            if (sum == 0) {
                ans = i + 1;
            } else if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return ans;
    }
}