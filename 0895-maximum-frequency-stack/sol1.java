class FreqStack {
    HashMap<Integer, Integer> cntMap; // elem -> freq
    List<Stack<Integer>> freqSt; // freq -> stack
    public FreqStack() {

        // 28ms
        // 스택에 push 하고, 가장 빈도수가 높은 요소를 pop한다.
        // 만약 빈도수가 똑같다면, 더 출구에 가까운 요소를 pop 한다.
        // 쉽게 생각해서, 더 나중에 들어온 요소를 pop한단 얘기?

        // 생각해보니까, freq시별로 Stack이 존재한다면, 언제나 Stack.pop() 원소는 문제에서 말하는 freqStack.pop()이 된다.
        // 그러고나서 원소의 개수의 변동을 lazy하게 업데이트 시켜놓을 자료구조를 만들어놓고
        // 나중에 pop() 했을때 해당원소의 freq와 실제 cntMap에 lazy 업데이트된 freq와 동일하지 않다면, 과거 데이터이므로 삭제시킨다.
        // 그러면 자연스럽게 이전 개수의 stack 당연하게 남아있으므로, 숫자 업데이트를 고려하지 않아도 된다.

        // 여기서 내가 생각하지 못한 부분은,  무조건 한번에 하나씩 원소가 추가되는것이 보장되어있기 때문에어짜피 순서대로 증가하는것이 freq이고,
        // 그러면 freq는 굳이 로그연산도 필요없이 마지막 기준으로 처리하면 된다.
        cntMap = new HashMap<>();
        freqSt = new ArrayList<>();
        freqSt.add(new Stack<>());
        // 계산 편하게 하랄고 0개째는 미리 전처리
    }
    
    public void push(int val) {
        
        cntMap.put(val, cntMap.getOrDefault(val, 0) + 1);
        if (cntMap.get(val) > freqSt.size() - 1) freqSt.add(new Stack<>());
        freqSt.get(cntMap.get(val)).add(val);
    }
    
    public int pop() {
        
        Stack<Integer> st = freqSt.get(freqSt.size() - 1); // 가장 큰 빈도수
        int popped = st.pop();
        if (st.isEmpty()) freqSt.remove(freqSt.size() - 1);
        cntMap.put(popped, cntMap.getOrDefault(popped, 0) - 1);
        return popped;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
