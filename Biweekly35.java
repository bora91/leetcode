class Solution35 {
    public static void main(String[] args) {
        System.out.println(new Solution35().sumOddLengthSubarrays(new int[]{1,2,3}));
    }
    public int sumOddLengthSubarrays(int[] arr) {
        int n = arr.length;
        int res = 0;
        for(int i = 0; i < n; i++){
            int sum = arr[i];
            res += sum;
            for(int j = i + 1; j < n; j++){
                sum += arr[j];
                int dis = j - i;
                if(dis % 2 == 0)
                    res += sum;
            }
        }
        
        return res;
    }
}