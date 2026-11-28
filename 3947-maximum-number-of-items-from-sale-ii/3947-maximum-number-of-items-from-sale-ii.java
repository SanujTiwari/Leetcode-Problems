import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        // 1. Count factor frequencies and find the globally cheapest item
        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // 2. Compute how many distinct items each factor f divides (excluding itself)
        int[] multCount = new int[n + 1];
        for (int f = 1; f <= n; f++) {
            if (freq[f] == 0) continue;
            for (int m = f; m <= n; m += f) {
                multCount[f] += freq[m];
            }
            multCount[f]--; // Exclude the item itself (i != j)
        }

        // 3. Pair each item's price with its maximum free-item gain and sort by price
        int[][] itemInfo = new int[n][2];
        for (int i = 0; i < n; i++) {
            itemInfo[i][0] = items[i][1];              // price
            itemInfo[i][1] = multCount[items[i][0]];   // gain
        }
        Arrays.sort(itemInfo, Comparator.comparingInt(a -> a[0]));

        // 4. Greedily buy "value = 2" boosted copies while cheaper than 2 * minPrice
        int totalItems = 0;
        for (int[] info : itemInfo) {
            int price = info[0];
            int gain = info[1];

            if (price >= 2L * minPrice) {
                break;
            }
            if (gain == 0) {
                continue;
            }

            int canBuy = Math.min(gain, budget / price);
            totalItems += canBuy * 2;
            budget -= canBuy * price;
        }

        // 5. Spend any remaining budget on the cheapest single items
        totalItems += budget / minPrice;

        return totalItems;
    }
}