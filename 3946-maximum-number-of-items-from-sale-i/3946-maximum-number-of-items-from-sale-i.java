import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int maxFactor = 0;
        for (int[] item : items) {
            maxFactor = Math.max(maxFactor, item[0]);
        }

        // 1. Count occurrences of each factor
        int[] freq = new int[maxFactor + 1];
        for (int[] item : items) {
            freq[item[0]]++;
        }

        // 2. Precompute how many items have a factor divisible by f
        int[] divisibleCount = new int[maxFactor + 1];
        for (int f = 1; f <= maxFactor; f++) {
            if (freq[f] == 0) continue;
            for (int multiple = f; multiple <= maxFactor; multiple += f) {
                divisibleCount[f] += freq[multiple];
            }
        }

        // 3. Knapsack DP over budget
        int[] dp = new int[budget + 1];

        for (int[] item : items) {
            int factor = item[0];
            int price = item[1];
            if (price > budget) continue;

            // First copy gives 1 purchased item + all other divisible items
            int firstCopyGain = divisibleCount[factor]; // includes item itself

            // Track best value for budget b where AT LEAST ONE copy of current item is bought
            int[] withItem = new int[budget + 1];
            Arrays.fill(withItem, -1);

            // Step A: Buy the 1st copy (0/1 transition from previous dp)
            for (int b = price; b <= budget; b++) {
                withItem[b] = dp[b - price] + firstCopyGain;
            }

            // Step B: Buy additional copies (unbounded transition, +1 item each)
            for (int b = price; b <= budget; b++) {
                if (withItem[b - price] != -1) {
                    withItem[b] = Math.max(withItem[b], withItem[b - price] + 1);
                }
            }

            // Step C: Merge back into main dp
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], withItem[b]);
            }
        }

        int ans = 0;
        for (int val : dp) {
            ans = Math.max(ans, val);
        }
        return ans;
    }
}