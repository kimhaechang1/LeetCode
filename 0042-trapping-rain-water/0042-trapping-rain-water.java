class Solution {
    public int trap(int[] height){ 
        return sol2(height);
    }
    public int sol1(int[] height) {
        // 정확하게는 얼마나 내림차순으로 유지되는가가 중요할듯
        // Stack으로 풀 수 있을듯

        /**
                 1        
        1        1 
        1    1   1
        1 1  1 1 1
        1 1  1 1 1 
        */
        int ans = 0;
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
    public int sol2(int[] height) {
        // 현재 인덱스에서 자기 머리위에 몇칸까지 물로 채워질 수 있는지를 파악하는 방법을 생각해보자.
        // 각각의 인덱스가 아니라 전체를 살펴보면 
        // 현재 위치까지 올 수 있는 좌측에서의 최대높이와 우측에서의 최대높이 중 더 작은값이 비교대상이란걸 알 수 있다.
        // 그렇게 구한 최대이자 최소를 구한값에서 현재 인덱스의 높이를 빼주면 현재 인덱스에서 머리위에 쌓을수 있는 물칸이 된다.
        int ans = 0;
        int[] L = new int[height.length + 1];
        int[] R = new int[height.length + 1];
        int[] H = new int[height.length + 1];
        for(int i = 1;i<height.length + 1;i++) {
            H[i] = height[i - 1];
        }
        L[1] = H[1];
        for(int i = 2;i<L.length;i++) {
            L[i] = Math.max(L[i - 1], H[i]);
        }
        R[R.length - 1] = H[H.length - 1];
        for(int i = R.length - 2;i > 0;i--) {
            R[i] = Math.max(R[i + 1], H[i]);
        }

        for(int i = 1;i<H.length;i++) {
            ans += (Math.min(L[i], R[i]) - H[i]);
        }
        return ans;
    }
}