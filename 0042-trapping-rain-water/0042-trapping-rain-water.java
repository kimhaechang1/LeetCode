class Solution {
    public int trap(int[] height){ 
        return sol2(height);
    }
    public int sol1(int[] height) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0;i<height.length;i++) {
            if (height[i] == 0) continue;

            // 만약 스택이 비었다면? 일단 넣어야지
            // 스택이 비어있지 않다면? 스택의 peek보다 큰지 작은지 계산해야함
            // 스택이 비어있지 않은데, 스택의 peek보다 작다면 일단 넣어야함
            // 스택이 비어있지 않은데, 스택의 peek보다 크다면 자신보다 큰 값이 나올때 까지 빼내면서 
            // 더 작은 높이를 기준으로 삼고, 폭을 구할 수 있으니 넓이를 구하여 ans에 누적
            // 이 때, stack의 peek 보다도 자기 바로앞의 원소가 뭐였는지에 따라 높이를 다르게 책정해야 하므로 prev변수 채용
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