class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return solution2(nums1, nums2);
    }
    public double solution2(int[] nums1, int[] nums2) { 
        if (nums1.length < nums2.length) {
            return sol(nums1, nums2);
        } else { 
            return sol(nums2, nums1);
        }
    }
    public double sol(int[] small, int[] large) {
        // 전체 배열의 크기는 n + m이다.
        // 여기서 중앙은 n + m / 2 일 것이다.

        // 전체 중앙의 인덱스는 (n + m) / 2 이다.
        // 여기서 중앙 인덱스를 기준으로 왼쪽의 원소들도 num1, nums2로 채워야 하고 반대 쪽도 마찬가지
        // 왼쪽만 보고 생각하면 된다.
        // out of bound가 안날려면 더 작은 배열을 기준으로 길이를 기준으로 잡아야 하고
        // 작은 배열의 기준점을 mid1이라고 했을 때, 큰 배열은 (n + m) / 2 - mid1 만큼을 꺼내야 한다.
        // 여기서 정답을 어떻게 판단하는가? 
        // mid1 - 1, mid1 + 1, mid2 - 1, mid2 + 1 각 배열 경계선에 인접한 값이다.
        // mid1 - 1의 경우 mid2 + 1보다도 작아야 한다, 반대로 mid1 + 1은 mid2 - 1 보다 커야 한다.
        // 자명한 결정조건이 있으므로 이진탐색이 가능하다.
        
        // 전체 중앙값이 (n + m) / 2 가 홀수인 경우와 짝수인 경우로 나눠서 생각해보면
        // 홀수인 경우 
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