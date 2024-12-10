class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // 매일으 ㅣ온도를 표현하고 있는 temperatures 정수형 배열이 주어진다.
        // answer[i] 는 i번째 날짜가 따뜻해질려면 몇일을 기다려야하는지 적힌 배열이다.

        // 특정 날짜가 몇일 기다리는지 확정낼려면, 해당날짜보다 온도가 높아지는 날짜를 찾아야 한다.
        // 그리고 해당 날짜는 무조건 자기보다 뒤에서 정답이 구해지고, 자신의 높아지는 날짜가 결정나면 제외시켜도 된다.
        int n = temperatures.length;
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[n];
        for(int i = 0; i < n; i++) {
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int idx = stack.peek();
                answer[idx] = i - idx;
                stack.pop();
            }
            stack.push(i);
        }
        return answer;
    }
}