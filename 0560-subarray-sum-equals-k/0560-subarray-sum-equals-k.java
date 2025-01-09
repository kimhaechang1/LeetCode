class Solution {
    public int subarraySum(int[] nums, int k) {
        // 합이 k와 같은 부분배열의 총 수를 반환하라.

        // 부분배열은 빈부분이 없는 이어져있는 요소들로 구성되어있다.

        // 투포인터나 슬라이딩 윈도우, 누적합 등을 사용할 수 있을듯
        // HashMap도 가능

        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int ans = 0;
        for(int num: nums) {
            sum += num;
            if (sum == k) {
                ans++;
            }
            // 작아진 원인이, 음수의 합 때문에 작아졌다면? 그부분을 떼어낼 수 있는 판단이 필요함.
            // k > sum && map.contains로 검사하면 k = 21이고 sum = 20인데 1이 나왔던적이 있는경우 문제가 발생함, 1이 더 필요한건데 1을 땔수도 있게됨
            // 그런데, k = 21이고 현재 인덱스 원소까지의 합이 20인데, 합이 -1이 나왔던 적이 있다면, 그부분을 빼버리면 21로 상승하겠지?
            if (k > sum &&  map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            } else if (k <= sum && map.containsKey(sum - k)){
                ans += map.get(sum - k);
            }
            
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}