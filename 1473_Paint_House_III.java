class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().minCost(new int[] { 0, 0, 0, 0, 0 },
                new int[][] { { 1, 10 }, { 10, 1 }, { 10, 1 }, { 1, 10 }, { 5, 1 } }, 5, 2, 3));
    }

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        Integer[][][] memo = new Integer[m][n + 1][target + 1];
        int ans = helper(0, 0, 0, n, m, target, cost, houses, memo);

        return ans == Integer.MAX_VALUE / 2 ? -1 : ans;
    }

    int helper(int hIndex, int currColor, int currTarget, int n, int m, int target, int[][] cost, int[] houses,
            Integer[][][] memo) {
        if (currTarget > target)
            return Integer.MAX_VALUE / 2;
        if (hIndex == m) {
            if (currTarget == target)
                return 0;
            else
                return Integer.MAX_VALUE / 2;
        }
        if (memo[hIndex][currColor][currTarget] != null)
            return memo[hIndex][currColor][currTarget];
        int min = Integer.MAX_VALUE / 2;
        if (houses[hIndex] == 0) {
            for (int index = 0; index < n; index++) {
                if (index + 1 == currColor) {
                    min = Math.min(min, cost[hIndex][index]
                            + helper(hIndex + 1, index + 1, currTarget, n, m, target, cost, houses, memo));
                } else {
                    min = Math.min(min, cost[hIndex][index]
                            + helper(hIndex + 1, index + 1, currTarget + 1, n, m, target, cost, houses, memo));
                }
            }
        } else {
            if (currColor == houses[hIndex]) {
                min = Math.min(min, helper(hIndex + 1, houses[hIndex], currTarget, n, m, target, cost, houses, memo));
            } else {
                min = Math.min(min,
                        helper(hIndex + 1, houses[hIndex], currTarget + 1, n, m, target, cost, houses, memo));
            }
        }

        return memo[hIndex][currColor][currTarget] = min;
    }
}