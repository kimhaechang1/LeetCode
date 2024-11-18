class MyQueue {

    static Stack<Integer> back;
    static Stack<Integer> front;

    public MyQueue() {
        back = new Stack<>();
        front = new Stack<>();
    }
    
    public void push(int x) {
        back.push(x);
    }
    
    public int pop() {
        int sz = back.size();
        while(back.size() != 1) {
            front.push(back.pop());
        }
        int rt = back.peek();
        back.pop();
        while(!front.isEmpty()) {
            back.push(front.pop());
        }
        return rt;
    }
    
    public int peek() {
        int sz = back.size();
        while(back.size() != 1) {
            front.push(back.pop());
        }
        int rt = back.peek();
        while(!front.isEmpty()) {
            back.push(front.pop());
        }
        return rt;
    }
    
    public boolean empty() {
        return back.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */