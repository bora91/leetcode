import java.util.*;
public class Contest206 {
    public static void main(String[] args) {
        System.out.println(new Contest206().minCostConnectPoints(new int[][]{{0,0},{2,2},{3,10},{5,2},{7,0}}));
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

    int[] parent;
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[][]distance = new int[n][n];
        parent = new int[n];
        
        for(int i = 0; i < n; i++)
            parent[i] = i;
        
        PriorityQueue<int[]>pq = new PriorityQueue<>((a,b)->distance[a[0]][a[1]]-distance[b[0]][b[1]]);
        
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                distance[i][j] = Math.abs(points[i][0]-points[j][0]) + Math.abs(points[i][1]-points[j][1]);
                pq.offer(new int[]{i, j});
            }
        }
        
        int count = n;
        int res = 0;
        while(!pq.isEmpty() && count > 1){
            int[]p = pq.poll();
            if(union(p[0], p[1])){
                res += distance[p[0]][p[1]];
                count--;
            }
        }
        
        return res;
    }
    boolean union(int x, int y){
        int parentx = find(x);
        int parenty = find(y);
        if(parentx == parenty)
            return false;
        parent[parentx] = parenty;
        return true;
    }
    int find(int x){
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        
        return parent[x];
    }
}
