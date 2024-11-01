import java.util.*;

class Solution {
    public int maxProfit(int[] prices) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(prices[0]);
        for(int i = 1;i<prices.length;i++) {
            if (stack.peek() > prices[i]) {
                stack.pop();
                stack.push(prices[i]);
            } else {
                ans = Math.max(prices[i] - stack.peek(), ans);
            }
        }
        return ans;
    }
}