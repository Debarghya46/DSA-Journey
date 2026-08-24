class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int sum1 = 0, sum2 = 0;
        int q1 = 0, q2 = 0;

        for (int i = 0; i < half; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                q1++;
            } else {
                sum1 += ch - '0';
            }
        }

        for (int i = half; i < n; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                q2++;
            } else {
                sum2 += ch - '0';
            }
        }

        // Odd number of '?' means Alice gets the advantage
        if ((q1 + q2) % 2 == 1) {
            return true;
        }

        // Bob wins only when the sums can be perfectly balanced
        return sum1 - sum2 != 9 * (q2 - q1) / 2;
    }
}