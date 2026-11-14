class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minPositiveSum = Integer.MAX_VALUE;

        // Iterate through every possible starting index
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            
            // Expand the subarray ending at index j
            for (int j = i; j < n; j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;

                // Stop expanding if the length exceeds r
                if (length > r) {
                    break;
                }

                // Check if length is within [l, r] and sum is strictly positive
                if (length >= l && currentSum > 0) {
                    minPositiveSum = Math.min(minPositiveSum, currentSum);
                }
            }
        }

        return minPositiveSum == Integer.MAX_VALUE ? -1 : minPositiveSum;
    }
}