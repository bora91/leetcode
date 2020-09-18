import java.util.*;

class Solution920 {
    int mod = 1_000_000_007;

    public static void main(String[] args) {
        System.out.println(new Solution920().numMusicPlaylists(3, 3, 1));
    }

    public int numMusicPlaylists(int N, int L, int K) {
        long[][] memo = new long[L + 1][N + 1];
        for (long[] arr : memo) {
            Arrays.fill(arr, -1);
        }

        return (int) helper(N, L, K, 0, 0, memo);
    }

    long helper(int n, int l, int k, int num, int len, long[][] memo) {
        if (len > l || num > n)
            return 0;
        if (len == l) {
            if (num == n)
                return 1;
            return 0;
        }
        if (memo[len][num] != -1)
            return memo[len][num];

        long res = (helper(n, l, k, num, len + 1, memo) * Math.max(0, num - k)) % mod;
        res += (helper(n, l, k, num + 1, len + 1, memo) * (n - num)) % mod;
        res = res % mod;

        return memo[len][num] = res;
    }
}