public class Contest206 {
    public static void main(String[] args) {
        System.out.println(new Contest206().unhappyFriends(4, new int[][] { { 1, 3, 2 }, { 2, 3, 0 }, { 1, 3, 0 }, {0, 2, 1} }, new int[][]{{1, 3}, {0, 2}}));
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

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int res = 0;
        int[] map = buildHashMap(pairs, n);
        for (int[] pair : pairs) {
            if (isHappy(pair[0], pair[1], preferences, map) != -1) {
                res += 1;
            }
            if (isHappy(pair[1], pair[0], preferences, map) != -1) {
                res += 1;
            }
        }
        return res;
    }

    private int isHappy(int person, int curMate, int[][] preferences, int[] map) {

        for (int mate : preferences[person]) {
            if (mate == curMate)
                return -1;

            int matesCurMate = map[mate];
            for (int matesMate : preferences[mate]) {
                if (matesMate == person) {
                    return mate;
                } else if (matesMate == matesCurMate) {
                    break;
                }
            }
        }

        return -1;
    }

    private int[] buildHashMap(int[][] pairs, int n) {
        int[] map = new int[n];
        for (int[] pair : pairs) {
            map[pair[0]] = pair[1];
            map[pair[1]] = pair[0];
        }

        return map;
    }
}
