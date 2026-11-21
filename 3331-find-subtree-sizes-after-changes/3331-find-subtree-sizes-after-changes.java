import java.util.*;

class Solution {
    private List<List<Integer>> adj;
    private int[] newParent;
    private int[] lastSeen;
    private int[] sizes;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.newParent = parent.clone();
        this.lastSeen = new int[26];
        Arrays.fill(this.lastSeen, -1);

        // 1. Build initial tree
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        // 2. Determine new parents via DFS + backtracking
        findParents(0);

        // 3. Build the modified tree
        for (int i = 0; i < n; i++) {
            adj.get(i).clear();
        }
        for (int i = 1; i < n; i++) {
            adj.get(newParent[i]).add(i);
        }

        // 4. Compute final subtree sizes
        sizes = new int[n];
        computeSizes(0);

        return sizes;
    }

    private void findParents(int u) {
        int charIdx = s.charAt(u) - 'a';
        int prevAncestor = lastSeen[charIdx];

        // If an ancestor with the same character exists, update parent
        if (prevAncestor != -1) {
            newParent[u] = prevAncestor;
        }

        // Set current node as the closest ancestor for this character
        lastSeen[charIdx] = u;

        for (int v : adj.get(u)) {
            findParents(v);
        }

        // Backtrack: restore previous state
        lastSeen[charIdx] = prevAncestor;
    }

    private int computeSizes(int u) {
        int size = 1;
        for (int v : adj.get(u)) {
            size += computeSizes(v);
        }
        sizes[u] = size;
        return size;
    }
}