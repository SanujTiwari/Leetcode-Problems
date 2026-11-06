class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        
        // Pair each query with its original index so we can restore the order later
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        
        // Sort queries by their time value
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[] ans = new int[m];
        HashMap<Integer, Integer> serverCount = new HashMap<>();
        
        int logIndex = 0; // Points to the start of valid logs for the window
        int left = 0;     // Left boundary of logs included in the window
        
        for (int i = 0; i < m; i++) {
            int qTime = sortedQueries[i][0];
            int originalIndex = sortedQueries[i][1];
            int windowStart = qTime - x;
            int windowEnd = qTime;
            
            // Add logs that fall within or before windowEnd
            while (logIndex < logs.length && logs[logIndex][1] <= windowEnd) {
                int serverId = logs[logIndex][0];
                serverCount.put(serverId, serverCount.getOrDefault(serverId, 0) + 1);
                logIndex++;
            }
            
            // Remove logs that fall out of the left boundary (windowStart)
            while (left < logIndex && logs[left][1] < windowStart) {
                int serverId = logs[left][0];
                serverCount.put(serverId, serverCount.get(serverId) - 1);
                if (serverCount.get(serverId) == 0) {
                    serverCount.remove(serverId);
                }
                left++;
            }
            
            // Total servers minus servers that received at least one request
            ans[originalIndex] = n - serverCount.size();
        }
        
        return ans;
    }
}