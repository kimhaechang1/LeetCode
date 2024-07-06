class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0;i<m;i++){
            if(matrix[i][0] > target) return false;
            if(matrix[i][n-1] < target) continue;
            if(search(matrix[i], target)){
                return true;
            }
        }

        return false;
    }
    public boolean search(int [] arr, int find){
        int s= 0;
        int e= arr.length;
        while(s <= e){
            int mid = (s+e)/2;
            if(arr[mid] < find){
                s = mid + 1;
            }else if(arr[mid] > find){
                e = mid - 1;
            }else{
                return true;
            }
        }
        return false;
    }
}