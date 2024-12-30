class Solution {
    static Random random;
    static int[] S;
    public Solution(int[] w) {
        random = new Random();
        S = new int[w.length];
        S[0] = w[0];
        for(int i = 1; i < S.length; i++) {
            S[i] = S[i - 1] + w[i];
        }
            
        // 예를들어 1 ~ 100까지의 확률 숫자분포 중에 40%라는건 결국 60 ~ 100은 확률상 40%에 해당된다.
        // 1, 2, 4, 3 -> 10%, 20%, 40%, 30%
        // 1까지 10%, 1,2 30%, 1, 2, 4 -> 70%, 1, 2, 4, 3 -> 100%

    }
    
    public int pickIndex() {
        if (S.length == 1) return 0;
        int find = random.nextInt(101);
        int s = 0;
        int e = S.length - 1;
        int ans = e;
        while(s <= e) {
            int mid = (s + e) / 2;
            if (getProba(S[mid]) > find) {
                e = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                s = mid + 1;
            }
        }
        return ans;
    }
    public int getProba(int value) {
        int total = S[S.length - 1];
        double back = ((double)value / total);
        return (int)(back * 100);
    }
}
/**
0부터 인덱싱되어 있는 양의 정수 w 배열이 주어진다. w[i]는 i번째 인덱스의 가중치를 표현한다.
무작위로 선택하고, 그것을 반환해야 한다. 
i번째 인덱스가 선택될 가능성은 w[i] / w의 합 이다.

 */


/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */