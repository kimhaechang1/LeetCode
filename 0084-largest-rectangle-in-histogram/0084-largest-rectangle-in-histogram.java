class Solution {
    public int largestRectangleArea(int[] heights) {
        // 넓이가 1인 히스토그램 바의 높이를 나타내는 heights 정수 배열이 주어졌을 때
        // 히스토그램에서 가장 큰 사각형의 넓이를 반환하라
        
        // 언제 최대가 될까?
        // 히스토그램의 넓이는 언제나 동일하다.
        // 하지만 높이의 경우에는 해당 연속적인 히스토그램들 사이에서 가장 낮은 높이가 된다.
        // 즉, 직사각형의 넓이를 가장 크게 만들려면, 현재 스택내에 있는 오름차순을 지속적으로 유지해야 한다.
        // 오름차순을 두었을 때 중요한 관찰이라면, 맨끝에서 봤을 때 스택에서 꺼낼때마다 항상 최소높이가 된다는 것
        // 즉, 1 2 3 4 5 6 10 이런식이라면, 처음엔 최솟값 10이었다가 꺼내면 꺼낼수록 내려가고, 그만큼 넓이는 증가한다.

        // 오름차순의 특징이라고 하면 할 수있다.
        
        // 어짜피 스택에은 인덱스가 들어가 있을것이다. 오름차순으로 진행하면 장점으론 peek()값이 무조건 현재보다 작다는것
        // 즉 스택에서 원소를 꺼낸것들이 모두 minHeight가 될 수 있다.
        // 오름차순으로 쭉 진행하다가, 현재 들어갈 값이 peek()보다 작다면 stack의 값을 꺼내면서 어짜피 스택의 값은 오름차순이기에 꺼낼때마다 새로운 minHeight가 된다.
        int answer = 0;
        int n = heights.length;
        int[] lareas = new int[n];
        int[] rareas = new int[n];
        // i 번째 원소를 기준 높이로 사용했을 때의 넓이 최대값
        for(int i = 0; i < n; i++) {
            lareas[i] = heights[i];
            rareas[i] = heights[i];
        }
        // 왼쪽에서 생각했을 때, 오른쪽 특정 지점을 기준으로 항상 변위 * 해당 높이가 최소높이 이려면 오름차순을 유지해야 한다.
        // 즉, 스택 내부의 값을 꺼낼때 해당 값까지의 연속되는 수열중에서 스택의 값이 가장 작아야한다. < 오름차순이라 가능하다.
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int idx = stack.pop();
                int width = (i) - idx;
                int minHeight = heights[idx];
                lareas[idx] = Math.max(lareas[idx], width * minHeight);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            int width = (n) - idx;
            int minHeight = heights[idx];
            lareas[idx] = Math.max(lareas[idx], width * minHeight);
        }
        // 반대로 오른쪽에서도 동일하게 생각해주자.
        stack = new Stack<>();
        
        for(int i = n - 1; i > -1; i--) {
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int idx = stack.pop();
                int width = Math.abs(i - idx);
                int minHeight = heights[idx];
                rareas[idx] = Math.max(rareas[idx], width * minHeight);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()) {
            int idx = stack.pop();
            int width = idx + 1;
            int minHeight = heights[idx];
            rareas[idx] = Math.max(rareas[idx], width * minHeight);
        }
        for(int i = 0; i < n ;i++) {
            answer = Math.max(rareas[i] + lareas[i] - heights[i], answer);
        }

        return answer;
    }
}