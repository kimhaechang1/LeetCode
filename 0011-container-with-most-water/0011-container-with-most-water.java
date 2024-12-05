class Solution {
    public int maxArea(int[] height) {
        int ans = 0;
        
        // 현재의 기둥을 기준으로 최대높이로 지정하고 최대넓이를 구할려면
        // 해당 기둥을 기준으로 끝에서부터 가장 높은 길이를 찾아야한다.

        // 양쪽 끝에 포인터를 두었다고 생각하고
        // 더 높이가 작은곳을 안쪽으로 옮긴다.
        // 왜냐하면 더 작은쪽을 기준으로 높이가 결정되고, 해당 높이를 기준으로 최대 폭이기 때문이다.
        
        // 투포인터로 구간처리 할 수 있었다.
        // 스택으로 풀려면 어쨋든 명확하게 결정적인 스택에서 영원히 빼내는 조건이 필요한데, 그게 아니면 안된다.
        int n = height.length;
        int s = 0;
        int e = n - 1;
        while(s <= e) {
            ans = Math.max( Math.min(height[s], height[e]) * (e - s), ans);
            if (height[s] < height[e]) {
                s++;
            } else if (height[s] > height[e]) { 
                e--;
            } else {
                s++;
                e--;
            }
        }
        
        return ans;
    }
}