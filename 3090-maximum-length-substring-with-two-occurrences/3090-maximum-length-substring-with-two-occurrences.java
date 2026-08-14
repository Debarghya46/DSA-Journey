class Solution {
    public int maximumLengthSubstring(String s) {
        int[] frequency = new int[26];

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);
            frequency[current - 'a']++;

            // If current character occurs more than twice,
            // shrink the window from the left.
            while (frequency[current - 'a'] > 2) {
                char leftChar = s.charAt(left);
                frequency[leftChar - 'a']--;
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}