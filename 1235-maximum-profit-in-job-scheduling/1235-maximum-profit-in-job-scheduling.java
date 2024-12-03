class Solution {
    static int[] dp;
    static TreeMap<Integer, ArrayList<Integer>> timeMap;
    static class Job {
        int start;
        int end;
        int profit;

        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        
        Sol1 sol = new Sol1();
        
        return sol.solution(startTime, endTime, profit);
    }

    static class Sol1 {

         static int solution(int[] startTime, int[] endTime, int[] profit) {

            // 1800ms, try second
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

            for(int i = 0;i < startTime.length;i++) {
                ArrayList<Integer> idxList = timeMap.containsKey(info[i][0]) ? timeMap.get(info[i][0]) : new ArrayList<>();
                idxList.add(i);
                timeMap.put(info[i][0], idxList);
            }

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
            Map.Entry<Integer, ArrayList<Integer>> entry = timeMap.ceilingEntry(info[depth][1]);
            if (entry != null) {
                ArrayList<Integer> idxList = entry.getValue();
                for(int idx: idxList) {
                    a = Math.max(a, info[depth][2] + dfs(idx, info));
                }
            }
            // 현재 작업을 선택안한 경우
            int b = dfs(depth + 1, info);
            
            dp[depth] = Math.max(a, b);
            return dp[depth];
        }
        
       
    }

    static class Sol2 {

        static int solution(int[] startTime, int[] endTime, int[] profit) {
            // 97 ms, try first
            // 최고 이윤을 반환하라 , 어떠한 두개작업도 시간 범위가 겹치지 않는
            // 어떤 작업의 끝나는시간이 X이면 X시간에 또다른작업을 바로 시작할 수 있다.

            // 생각해보면 어쨋든 최대한 넣는것이 중요하고
            // 특정끝나는 시각까지의 최대값을 알아야 한다.
            
            Job[] jobs = new Job[startTime.length];

            for(int i = 0;i<jobs.length;i++) {
                jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
            }
            Arrays.sort(jobs, (a, b) -> {  
                return a.end - b.end;
            });

            TreeMap<Integer, Integer> treeMap = new TreeMap<>();

            for(int i = 0;i<jobs.length;i++) {
                Job select = jobs[i];
                Map.Entry<Integer, Integer> floorEntry = treeMap.floorEntry(select.start);
                int profitSum = (floorEntry == null ? 0 : floorEntry.getValue()) + jobs[i].profit;
                
                floorEntry = treeMap.floorEntry(select.end);
                int maxValue = (floorEntry == null ? 0 : floorEntry.getValue());
                
                // 자기 앞의 끝나있는 시각대의 값들 중에서 최대를 골라야 한다.
                // 현재 작업의 시작시간보다 적은 값들 중에서 최대를 골라야 한다.
                // 최적의 값을 꺼낸다 생각하면 이전까지의 최대값을 꺼내서 현재 볼 수 있는 이득과 비교한다.
                // 새롭게 시작하는것 보다 이전까지의 최적값을 선택하는게 좋을 수 있다. 
                // 왜냐하면 어짜피 해당 끝나는 시각까지 얻을 수 있는 최대값을 다른 시작시각에서 또 사용하기 때문이다.
                
                treeMap.put(select.end, Math.max(maxValue, profitSum));
            }
            
            // 마지막까지 끝나는 시각 기준 누적 최대값을 리턴한다.
            return treeMap.lastEntry().getValue();
        }
    }
    static class Sol3 {

        // best solution, 32ms

        // 결국 이전의 job이 갑자기 이후의 job과 결합되어 정답 후보가 될 수 있다.
        // 그래서 완전탐색을 하는게 좋다.
        // 여기서 재밌는점은 시작시각을 기준으로 어짜피 정렬되어 있기에, 바로 붙일 수 있는 작업을 이분탐색으로 찾을 때
        // 같은 시작시각을 가진 값들 모두를 생각할 필요가 없다.
        // 어짜피 순차적으로 다 하게 되어있다.
        // 목표는 결국 최대이익이다.

        // 시작시각을 기준으로 정렬되어 있을 때,
        // 현재 작업을 선택하는 경우와 선택하지 않은 경우로 나눠볼 수 있다.
        // 그렇다고 얻는 이율과 작업의 종류를 기준으로 knapsack을 하기에는 이율 범위가 너무 크다.

        // 시작시각을 기준으로 정렬해보면, 특정 작업을 선택해서 얻을 수 있는 최대이익은 정해져 있기에 메모해둘 수 있다.
        // 그리고 작업선택 기준은 현재 선택된 작업의 끝나는 시각과 가장 가까운 작업을 선택할 수 있다.

        // 정렬을 하지않았다면, 현재 작업을 선택하지 않고 다음작업으로 시작할 때의 선택이 무작위가 되어버려서 문제가 발생한다.

        static int solution(int[] startTime, int[] endTime, int[] profit) {
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

    
}