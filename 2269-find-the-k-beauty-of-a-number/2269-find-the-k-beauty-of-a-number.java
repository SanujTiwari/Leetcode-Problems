class Solution {
    public int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        int count = 0;
        
        // Slide a window of size k across the string representation of num
        for (int i = 0; i <= s.length() - k; i++) {
            String sub = s.substring(i, i + k);
            int val = Integer.parseInt(sub);
            
            // Ensure val is not 0 to avoid ArithmeticException (division by zero)
            if (val != 0 && num % val == 0) {
                count++;
            }
        }
        
        return count;
    }
}