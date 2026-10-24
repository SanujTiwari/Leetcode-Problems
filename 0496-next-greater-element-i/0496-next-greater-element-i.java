class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // Since constraints state elements are between 0 and 10^4, 
        // we can use a direct-address table (frequency/hash array) instead of HashMap.
        // Map stores: nextGreaterMap[element] = nextGreaterElement
        int[] nextGreaterMap = new int[10001];
        
        // Use a primitive array as a stack to avoid java.util.Stack
        int[] stack = new int[nums2.length];
        int top = -1;

        for (int num : nums2) {
            while (top >= 0 && num > stack[top]) {
                nextGreaterMap[stack[top--]] = num;
            }
            stack[++top] = num;
        }

        while (top >= 0) {
            nextGreaterMap[stack[top--]] = -1;
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap[nums1[i]];
        }

        return result;
    }
}