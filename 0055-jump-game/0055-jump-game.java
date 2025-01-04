class Solution {
    public boolean canJump(int[] nums) {
        // 정수배열 num가 주어진다.
        // 너는 배열의 첫번째 인덱스에 위치해있다.
        // 각요소는 그 위치에서 너의 최대 점프 길이 표현하고 잇다.

        // 마지막 인덱스에 도달이 가능하다면 true를, 불가능하다면 false를 반환하라

        // 좀 쉽게 생각하면, 특정 범위까지 도달이 가능하다고 생각해보자.
        // 그러면 도움닫기를 최대한 했을 때, 이미 nums.length - 1 이상이 된다면 그건 도달이 가능한것

        int max = 0; // 지금까지 최대한 갈 수 있는 길이
        for(int i = 0; i < nums.length; i++) {
            if (max < i) return false; // 도움닫기를 최대한 해봣는데도 i번째에 닿지 못하는경우가 있을 수 있다.
            max = Math.max(max, i + nums[i]);
            if (max >= nums.length - 1) return true;
        }
        
        return false;
    }
}