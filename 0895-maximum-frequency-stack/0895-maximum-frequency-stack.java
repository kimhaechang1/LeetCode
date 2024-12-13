class FreqStack {
    HashMap<Integer, Integer> cntMap; // elem -> freq
    TreeMap<Integer, Stack<Integer>> freqSt; // freq -> stack
    public FreqStack() {
        
        // 스택에 push 하고, 가장 빈도수가 높은 요소를 pop한다.
        // 만약 빈도수가 똑같다면, 더 출구에 가까운 요소를 pop 한다.
        // 쉽게 생각해서, 더 나중에 들어온 요소를 pop한단 얘기?

        // 생각해보니까, freq시별로 Stack이 존재한다면, 언제나 Stack.pop() 원소는 문제에서 말하는 freqStack.pop()이 된다.
        // 그러고나서 원소의 개수의 변동을 lazy하게 업데이트 시켜놓을 자료구조를 만들어놓고
        // 나중에 pop() 했을때 해당원소의 freq와 실제 cntMap에 lazy 업데이트된 freq와 동일하지 않다면, 과거 데이터이므로 삭제시킨다.
        cntMap = new HashMap<>();
        freqSt = new TreeMap<>();
    }
    
    public void push(int val) {
        cntMap.put(val, cntMap.getOrDefault(val, 0) + 1);
        Stack<Integer> st = freqSt.getOrDefault(cntMap.get(val), new Stack<>());
        st.push(val);
        freqSt.put(cntMap.get(val), st);
    }
    
    public int pop() {
        
        Map.Entry<Integer, Stack<Integer>> last = freqSt.lastEntry();
        int selectFreq = last.getKey();
        Stack<Integer> st = last.getValue();
        int popped = -1;
        while(!st.isEmpty()) {
            int val = st.pop();
            if (cntMap.get(val) == selectFreq) {
                // 이전기록이 반드시 남아있을 것, 추가 저장할 필요는 없음
                cntMap.put(val, cntMap.getOrDefault(val, 0) - 1);
                popped = val;
                break;
            }
        }
        if (st.size() == 0) {
            freqSt.pollLastEntry();
        }
        return popped;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */