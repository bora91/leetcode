import java.util.Arrays;
class Solution35 {
    public static void main(String[] args) {
        System.out.println(new Solution35().maxSumRangeQuery(new int[] { 1, 2, 3, 4, 5 }, new int[][]{{1,3},{0,1}}));
    }

    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int sum = arr[i];
            res += sum;
            for (int j = i + 1; j < n; j++) {
                sum += arr[j];
                int dis = j - i;
                if (dis % 2 == 0)
                    res += sum;
            }
        }

        return res;
    }

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int mod = 1_000_000_007;
        int n = nums.length;
        int[] frq = new int[n];
        Arrays.sort(nums);
        for (int[] req : requests) {
            int s = req[0];
            int e = req[1];
            frq[s]++;
            if (e + 1 < n)
                frq[e + 1]--;
        }
        for (int i = 1; i < n; i++)
            frq[i] += frq[i - 1];

        Arrays.sort(frq);
        long sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum += (frq[i] * nums[i]);
            sum %= mod;
        }
        return (int) (sum % mod);
    }
}