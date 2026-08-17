class Solution {

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        // Prefix sum
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        // dp[left][right]
        int[][] dp = new int[n][n];

        // length = size of current range
        for (int length = 2; length <= n; length++) {

            for (int left = 0; left + length <= n; left++) {

                int right = left + length - 1;

                // Try every possible split
                for (int split = left; split < right; split++) {

                    int leftSum =
                            prefix[split + 1] - prefix[left];

                    int rightSum =
                            prefix[right + 1] - prefix[split + 1];

                    if (leftSum < rightSum) {

                        dp[left][right] = Math.max(
                                dp[left][right],
                                leftSum + dp[left][split]
                        );

                    } else if (leftSum > rightSum) {

                        dp[left][right] = Math.max(
                                dp[left][right],
                                rightSum + dp[split + 1][right]
                        );

                    } else {

                        dp[left][right] = Math.max(
                                dp[left][right],
                                Math.max(
                                        leftSum + dp[left][split],
                                        rightSum + dp[split + 1][right]
                                )
                        );
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}