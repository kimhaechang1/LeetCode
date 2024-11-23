class Solution {
    public int trap(int[] height) {
        int ans = 0;
        // 정확하게는 얼마나 내림차순으로 유지되는가가 중요할듯
        // Stack으로 풀 수 있을듯

        /**
                 1        
        1        1 
        1    1   1
        1 1  1 1 1
        1 1  1 1 1 
        */
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<height.length;i++) {
            if (height[i] == 0) continue;

            // 만약 스택이 비었다면? 일단 넣어야지
            // 스택이 비어있지 않다면? 스택의 peek보다 큰지 작은지 계산해야함
            // 스택이 비어있지 않은데, 스택의 peek보다 작다면 일단 넣어야함
            // 스택이 비어있지 않은데, 스택의 peek보다 크다면 자신보다 큰 값이 나올때 까지 빼내면서 
            int prev = 0;
            while(!stack.isEmpty()) {
                int hIdx = stack.peek();
                ans += (i - hIdx - 1) * (Math.min(height[hIdx], height[i]) - (prev));
                prev = height[hIdx];
                if (height[hIdx] > height[i]) break;
                stack.pop();
            }
            stack.push(i);
        }
        
        return ans;
    }
}