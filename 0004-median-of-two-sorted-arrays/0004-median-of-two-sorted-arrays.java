class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
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