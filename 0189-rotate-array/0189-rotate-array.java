class Solution {
    public void rotate(int[] nums, int k) {
        // nums 배열이 있고 k번 오른쪽으로 돌려라, k는 양의 정수이다.
        int n = nums.length;
        k %= n;


        //       k  
        // 1 2 3 4 5 6 7
        // 5 6 7 1 2 3 4

        // 위를 보면 한가지 중요한 특징이 있다.
        // k번째를 기준으로 두 그룹으로 나누자면
        // 1번 그룹: 1 2 3 4
        // 2번 그룹: 5 6 7
        // 그리고 5 6 7 이 앞에가고 1 2 3 4가 뒤에 간다.
        // 이렇게만 보고있으면 옮겨야 한다는 사실이 불편하다. 묶음 단위를 안 옮길순 없을까?

        // 전체를 뒤집어서 생각해보면 재미있는 사실은 이제 앞에서 부터 k번째로 그룹을 나눌 수 있다는 점이다.
        //       k
        // 7 6 5 4 3 2 1
        // 0 ~ k 만큼 뒤집고, k ~ length - 1 까지 뒤집으면
        // 5 6 7 1 2 3 4 로 정답이 된다.

        // 뒤집는건 양쪽 끝에서부터 교환하면 된다.
        rotate(nums, 0, n - 1);
        rotate(nums, 0, k - 1);
        rotate(nums, k, n - 1);
    }
    static void rotate(int[] arr, int s, int e) {
        while(s < e) {
            int t = arr[s];
            arr[s] = arr[e];
            arr[e] = t;
            s++;
            e--;
        }
    }
}