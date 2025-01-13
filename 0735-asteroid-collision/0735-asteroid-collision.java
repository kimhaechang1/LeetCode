class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        // 각 소행성들은 그것들의 상대적인 위치를 나타낸다.
        // 각각의 값의 부호는 양이면 우측, 음이면 좌측으로 간다는것이고, 값은 크기를 으미한다.
        // 같은 속도로 날라간다.

        // 소행성들의 상태를 출력하라 모든 충돌이후에
        // 만약 두 소행성이 만났다면 더 작은것이 터진다.
        // 둘 사이즈가 같다면, 둘다 터진다. 같은 방향으로 움직이는 두 소행성은 절대로 만나지 못한다.

        // 같은 row 즉 같은 가로줄에 있는 소행성들에 대한 정보를 배열로 주어진것

        Stack<Integer> stack = new Stack<>();
        for(int asteroid: asteroids) {
            stack.push(asteroid);
            while(stack.size() >= 2) {
                int right = stack.pop();
                int left = stack.pop();
                if ((right * left > 0) || (left < 0 && right > 0)) {
                    stack.push(left);
                    stack.push(right);
                    break;
                }
                // 자기 왼쪽이 오른쪽으로 이동방향을 가지면서 (양수)
                // 자기 자신은 왼쪽으로 이동방향을 가져야 충돌이 발생한다. (음수)
                int absRight = Math.abs(right);
                int absLeft = Math.abs(left);
                if (absRight == absLeft) {
                    break;
                }
                
                stack.push(absRight > absLeft ? right : left);
            }
        }
        int[] result = new int[stack.size()];
        int idx = result.length - 1;
        while(!stack.isEmpty()) {
            result[idx--] = stack.pop();
        }
        return result;
    }
}