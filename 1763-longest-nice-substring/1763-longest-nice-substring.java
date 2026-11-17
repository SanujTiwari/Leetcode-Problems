
class Solution {
    public String longestNiceSubstring(String s) {
        int maxLength = 0;
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            int lower = 0;
            int upper = 0;

            for (int j = i; j < s.length(); j++) {
                char ch = s.charAt(j);

                if (Character.isLowerCase(ch)) {
                    lower |= 1 << (ch - 'a');
                } else {
                    upper |= 1 << (ch - 'A');
                }

                if (lower == upper) {
                    int len = j - i + 1;

                    if (len > maxLength) {
                        maxLength = len;
                        result = s.substring(i, j + 1);
                    }
                }
            }
        }

        return result;
    }
}
