class Solution {
    public int countCommas(int n) {
        long ans = 0;
        long power = 1000;

        while (power <= n) {
            ans += n - power + 1;
            power *= 1000;
        }

        return (int) ans;
    }
}