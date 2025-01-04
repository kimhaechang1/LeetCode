class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return solution2(nums1, nums2);
    }
    public double solution2(int[] nums1, int[] nums2) { 

        // O(log min(n, m))
        if (nums1.length < nums2.length) {
            return sol(nums1, nums2);
        } else { 
            return sol(nums2, nums1);
        }
    }
    public double sol(int[] small, int[] large) {
        
        // 정렬된 두 배열을 이용해보자.
        // 정렬이라면 무엇보다도 이분탐색을 사용하고 싶을것
        // 그러면 결정조건을 찾아보자.

        // 두 배열을 합친 상황이라면 n + m 길이의 중간 지점이 있을것이다.
        // 예를들어 3 + 4 라면 3번 인덱스가 중간지점일 것이고 총 개수는 4개 필요하다.
        // 그러면 합친배열에서는 n + m / 2 + 1개의 원소가 각 배열에서 부터 필요하다.

        // 그럼 각 배열에서 꺼내온 개수의 합이 위의 (n + m) / 2 + 1개여야 하는데,
        // 예를들어 3, 4인 상황에서는 4개가 필요한데 그 원소들은 각 배열에서 무조건 앞에서 뽑아야 한다.
        // 왜냐하면 그래야 정렬된 구조를 유지할 수 있어서 중간 인덱스가 중앙값이 된다.
        // 위에서 2개 아래에서 2개를 뽑는다고 가정해보자.
        
        // 그리고 최대 개수는 작은 배열 기준으로 잡는게 오버플로가 안나서 좋다.
        // 그렇게 2개를 뽑기위해 선별하면 경계선이 있을것이고, 경계선 내부의 값과 외부의 값을 가지고 서로 비교해야한다.
        // 가능한 순간은 작은 배열의 경계선 내부의 값은 큰 배열의 경계선 밖의 값보다 작아야하고, 큰 배열의 경계선 내부의 값 또한 작은 배열의 경계선 밖의 값보다 작아야 한다.
        // 만약 전자를 어긴거라면, 뽑은 작은 2개의 값에서 최대값이 기준치를 넘은것으로 작은배열의 사이즈를 줄여야 하고
        // 후자를 어긴거라면 큰배열의 사이즈를 줄여야 한다.
        int n = small.length;
        int m = large.length;
        int totalMiddleCnt = (n + m) / 2 + (((n + m) & 1) == 1 ? 1 : 0);

        
        int s = 0;
        int e = small.length;
        double ans = 0;
        while(s <= e) {

            int smid = (s + e) / 2;
            int lmid = (totalMiddleCnt - smid);
            
            int sl = Integer.MIN_VALUE;
            int sr = Integer.MAX_VALUE;
            int ll = Integer.MIN_VALUE;
            int lr = Integer.MAX_VALUE;
            if (smid - 1 > -1) {
                sl = Math.max(sl, small[smid - 1]);
            }
            if (smid < n) {
                sr = Math.min(sr, small[smid]);
            }
            if (lmid - 1 > -1) {
                ll = Math.max(ll, large[lmid - 1]);
            }
            if (lmid < m) {
                lr = Math.min(lr, large[lmid]);
            }

            if (sl <= lr && ll <= sr) {
                if (((n + m) & 1) == 1) {
                    ans = (double) Math.max(ll, sl);
                } else {
                    ans = (double) (Math.max(ll, sl) + Math.min(lr, sr)) / 2;
                }
                break;
            }

            if (sl > lr) {
                e = smid - 1;
            } else if (ll > sr) {
                s = smid + 1;
            }
        }

        
        return ans;
    }

    public double solution1(int[] nums1, int[] nums2) {
        // O(n + m)
        int[] arr = new int[nums1.length + nums2.length];
        int l = 0;
        int r = 0;
        int ptr = 0;
        while(l < nums1.length && r < nums2.length) {
            if (nums1[l] < nums2[r]) {
                arr[ptr++] = nums1[l++];
            } else {
                arr[ptr++] = nums2[r++];
            }
        }
        while(l < nums1.length) {
            arr[ptr++] = nums1[l++];
        }

        while(r < nums2.length) {
            arr[ptr++] = nums2[r++];
        }

        if ((arr.length & 1) == 1) {
            return arr[arr.length / 2];
        } else {
            return (double)(arr[arr.length / 2] + arr[arr.length / 2 - 1]) / 2;
        }
    }
}