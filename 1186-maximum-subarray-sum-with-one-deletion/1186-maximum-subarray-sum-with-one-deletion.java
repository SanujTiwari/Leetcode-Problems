class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        if (n == 1) return arr[0];

        int withoutDel = arr[0];
    
        int withDel = arr[0];
        
        int maxSum = arr[0];

        for (int i = 1; i < n; i++) {
            
            withDel = Math.max(withDel + arr[i], withoutDel);
            withoutDel = Math.max(withoutDel + arr[i], arr[i]);
            
            maxSum = Math.max(maxSum, Math.max(withoutDel, withDel));
        }

        return maxSum;
    }
}