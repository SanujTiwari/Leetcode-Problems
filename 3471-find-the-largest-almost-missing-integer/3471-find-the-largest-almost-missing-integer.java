import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        if (k == 1) {
            HashMap<Integer, Integer> freq = new HashMap<>();

            for (int num : nums) {
                freq.put(num, freq.getOrDefault(num, 0) + 1);
            }

            int ans = -1;

            for (int num : nums) {
                if (freq.get(num) == 1) {
                    ans = Math.max(ans, num);
                }
            }

            return ans;
        }

        if (k == n) {
            int max = nums[0];

            for (int num : nums) {
                max = Math.max(max, num);
            }

            return max;
        }

        int ans = -1;

        int first = nums[0];
        int firstCount = 0;

        for (int num : nums) {
            if (num == first) {
                firstCount++;
            }
        }

        if (firstCount == 1) {
            ans = Math.max(ans, first);
        }

        int last = nums[n - 1];
        int lastCount = 0;

        for (int num : nums) {
            if (num == last) {
                lastCount++;
            }
        }

        if (lastCount == 1) {
            ans = Math.max(ans, last);
        }

        return ans;
    }
}