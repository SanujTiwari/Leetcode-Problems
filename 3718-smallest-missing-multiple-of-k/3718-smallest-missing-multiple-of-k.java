class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        
        // Add all elements of nums to a HashSet for O(1) lookups
        for (int num : nums) {
            set.add(num);
        }
        
        // Check multiples of k starting from 1 * k
        int multiple = k;
        while (set.contains(multiple)) {
            multiple += k;
        }
        
        // Return the first multiple not found in the set
        return multiple;
    }
}