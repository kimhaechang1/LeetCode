class Solution {
    static int[] dp;
    static TreeMap<Integer, ArrayList<Integer>> timeMap;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // 결국 이전의 job이 갑자기 이후의 job과 결합되어 정답 후보가 될 수 있다.
        // 그래서 완전탐색을 하는게 좋다.
        // 목표는 결국 최대이익이다.

        // 시작시각을 기준으로 정렬되어 있을 때,
        // 현재 작업을 선택하는 경우와 선택하지 않은 경우로 나눠볼 수 있다.
        // 그렇다고 얻는 이율과 작업의 종류를 기준으로 knapsack을 하기에는 이율 범위가 너무 크다.

        // 시작시각을 기준으로 정렬해보면, 특정 작업을 선택해서 얻을 수 있는 최대이익은 정해져 있기에 메모해둘 수 있다.
        // 그리고 작업선택 기준은 현재 선택된 작업의 끝나는 시각과 가장 가까운 작업을 선택할 수 있다.

        // 정렬을 하지않았다면, 현재 작업을 선택하지 않고 다음작업으로 시작할 때의 선택이 무작위가 되어버려서 문제가 발생한다.
        
        timeMap = new TreeMap<>();
        
        int[][] info = new int[startTime.length][3];
        for(int i = 0;i < startTime.length;i++) {
            info[i][0] = startTime[i];
            info[i][1] = endTime[i];
            info[i][2] = profit[i];
        }

        Arrays.sort(info, (a, b)-> {
            return a[0] - b[0];
        });

        dp = new int[startTime.length];
        Arrays.fill(dp, -1); // 이익의 음수는 없으므로

        dfs(0, info);
        
        return dp[0];
    }
    static int dfs(int depth, int[][] info) {

        if (depth >= info.length) {
            // 만약 선택 할 idx가 더이상 선택이 불가하다면, 이득도 당연히 없다.
            return 0;
        }

        if (dp[depth] != -1) {
            return dp[depth];
        }
        
        dp[depth] = 0;
        // 현재 작업을 선택하고 이어붙인 경우
        int a = info[depth][2];
        int next = search(depth + 1, info.length - 1, info, info[depth][1]);
        if (next != -1) {
            a = Math.max(a, info[depth][2] + dfs(next, info));
        }
        // 현재 작업을 선택안한 경우
        int b = dfs(depth + 1, info);
        
        dp[depth] = Math.max(a, b);
        return dp[depth];
    }
    static int search(int s, int e, int[][] info,  int find) {
        int ans = -1;

        while(s <= e) {
            int mid = (s + e) / 2;
            
            if (info[mid][0] >= find) {
                ans = mid;
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }
}