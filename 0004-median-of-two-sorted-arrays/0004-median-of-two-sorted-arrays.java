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
        
        // 정렬되어 있는 배열 및 경계선을 핵심 키워드로 생각해보자.
        // 전체 배열의 크기는 n + m 이다.
        // 그러면 전체 배열에서 중앙까지 필요한 원소의 개수가 있을것, 이를 totalMid라고 하자.
        // 그 중앙 개수를 채우는것은 반드시 small과 large의 특정 범위일 것이다.
        // 아웃오브 바운드를 생각해서 small을 기준으로 범위를 지정하자.
        // 그리고 small에서 merge 배열에 중앙까지 채울 개수를 lCnt, 반대를 rCnt라고 했을 때,
        // lCnt - 1이 경계선에 놓인값이고 lCnt는 경계선 밖의 값
        // rCnt도 마찬가지이다.
        // 각 배열별 경계선에서 경계선보다 높은 값보다 서로의 반대편 경계선값은 작아야 한다.
        // 무슨말이냐면 lCnt - 1이 l배열의 경계선, lCnt가 경계선 바로 밖의 값이라고 정의하고 r배열도 마찬가지로 정의하자.
        // lCnt - 1 <= rCnt 면서 lCnt >= rCnt - 1이어야 한단 뜻이다. 이러면 정답을 구할 수 있다.
        // 반대로 만약 lCnt - 1 > rCnt 가 되어버리면? 그 땐 l배열의 개수를 하나 줄이고 r배열의 개수를 하나 늘려야 한다. 
        // 전체 개수는 항상 정해져 있음을 까먹지 말자
        int n = small.length;
        int m = large.length;
        int totalLeftCnt = (n + m) / 2 + (((n + m) & 1) == 1 ? 1 : 0);
        int start = 0;
        int end = small.length;
        double answer = 0;
        while(start <= end) {
            int lCnt = (start + end) / 2;
            int rCnt = (totalLeftCnt - lCnt);
            int l1 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE;
            int l2 = Integer.MIN_VALUE;
            int r2 = Integer.MAX_VALUE;
            
            if (lCnt - 1 >= 0) {
                l1 = small[lCnt - 1];
            }
            
            if (lCnt < n) {
                r1 = small[lCnt];
            }
            
            if (rCnt - 1 >= 0) {
                l2 = large[rCnt - 1];
            }
        
            if (rCnt < m) {
                r2 = large[rCnt];
            }

            if (l1 <= r2 && r1 >= l2) {
                if (((n + m) & 1) == 1) {
                    answer = Math.max(l1, l2);
                } else {
                    answer = ((double)(Math.max(l1, l2) + Math.min(r1, r2)) / 2);
                }
                break;
            } else if (l1 > r2) {
                end = lCnt - 1;
            } else {
                start = lCnt + 1;
            }
        }   
        return answer;
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