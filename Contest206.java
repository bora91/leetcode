public class Contest206 {
    public static void main(String[] args) {
        System.out.println(new Contest206().numSpecial(new int[][]{{0,0},{0,0},{1,0}}));
    }

    public int numSpecial(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[] col = new int[m];
        int[] row = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1)
                    count++;
            }
            row[i] = count;
        }
        for (int j = 0; j < m; j++) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (mat[i][j] == 1)
                    count++;
            }
            col[j] = count;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    if (row[i] == 1 && col[j] == 1)
                        res++;
                }
            }
        }

        return res;
    }
}
