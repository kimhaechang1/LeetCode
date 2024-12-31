class Solution {
    public int findMaxLength(int[] nums) {
        int n = nums.length;
        // 기본적으로 합이 0이 되는 순간은 무조건 i + 1 이 정답이 될 수 밖에 없다.
        // 왜냐하면 앞에서부터 누적했기 때문에 0번부터 누적했을 때 0이 나온단거기 때문이다.
        
        // 하지만 이 문제의 경우 0번부터가 range의 정답이 아닐수도 있다.
        // [0,1,0,0,1,0,1,0,0]
        //   -2 -3
        // 무슨말을 하는거냐면 필요없는 누적이 있다 하거늘 해당 번째까지의 누적을 뺀 구간에 있어서 합이 같다면 0과 1의 개수가 같은 구간이 된다.
        // 다시말해 예를들어 i = 2번째까지는 위의 규칙에 의해 -2였는데, i = 7번째까지 계속 누적했더니 -2가 나온거야
        // 그러면 그 사이 구간은 합이 0 즉, 1과 0의 개수가 같은 구간인거지
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int sum = 0;
        for(int i = 0;i < nums.length; i++) {
            sum += (nums[i] == 0 ? -1 : 1);
            if (sum == 0) {
                // 0이 나온건 시작점부터의 누적합이 최대길이가 되는 경우
                ans = Math.max(ans, i + 1);
            } else if (map.containsKey(sum)) {
                // 이전 누적합 중 현재의 sum이 나온적이 있는 경우, 그 사이 구간은 0과 1의 개수가 같다.
                ans = Math.max(ans, (i - map.get(sum)));
            } else {
                map.put(sum, i);
            }
        }
        return ans;
    }
}