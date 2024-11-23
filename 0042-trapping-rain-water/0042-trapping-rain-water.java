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

            // 내림차순 유지를 하려다가 보니 더 큰값이 들어오려 한다.
            // 그러면 현재 넣을려는 값 -> right, 중앙값 stack.peek() -> mid, 바로 왼쪽 비교대상 stack.pop().peek();
            // 어짜피 스택 내부는 내림차순을 유지하려고 할것이므로, 
            while(!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int mid = stack.pop();
                if (stack.isEmpty()) break;
                int left = stack.peek();
                int hh = Math.min(height[left] - height[mid], height[i] - height[mid]);
                ans += (i - left - 1) * (hh);
            }
            stack.push(i);
        }
        
        return ans;
    }
    public void sol2(int[] height) {

    }
}