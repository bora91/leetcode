import java.util.*;

public class Weekly207 {
    public static void main(String[] args) {
        System.out.println(new Weekly207().isPrintable(new int[][]{{1,1,1,1},{1,1,3,3},{1,1,3,4},{5,5,1,4}}));
    }

    public String reorderSpaces(String text) {
        int words = 0;
        int space = 0;
        boolean flag = false;
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                space++;
                if (flag)
                    list.add(sb.toString());
                flag = false;
                sb = new StringBuilder();
            } else {
                sb.append(text.charAt(i));
                if (!flag) {
                    words++;
                    flag = true;
                }
            }
        }
        if (flag)
            list.add(sb.toString());
        if (words == 1) {
            String res = list.get(0);
            for (int i = 0; i < space; i++)
                res += " ";

            return res;
        }

        int avgspace = space / (words - 1);
        int extra = space % (words - 1);
        sb = new StringBuilder();
        for (int j = 0; j < list.size(); j++) {
            String str = list.get(j);
            for (int i = 0; i < str.length(); i++) {
                sb.append(str.charAt(i));
            }
            if (j != list.size() - 1) {
                for (int i = 0; i < avgspace; i++)
                    sb.append(' ');
            }
        }
        for (int i = 0; i < extra; i++)
            sb.append(' ');

        return sb.toString();
    }

    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        return dfs(set, 0, s);
    }

    int dfs(Set<String> set, int idx, String s) {
        if (idx >= s.length())
            return 0;
        int res = -1;
        for (int i = idx + 1; i <= s.length(); i++) {
            String sub = s.substring(idx, i);
            if (set.contains(sub))
                continue;
            set.add(sub);
            int next = dfs(set, i, s);
            if (next >= 0)
                res = Math.max(res, next + 1);
            set.remove(sub);
        }
        return res;
    }

    long p = -1;

    public int maxProductPath(int[][] grid) {
        int mod = 1_000_000_007;
        dfs(grid, 0, 0, grid[0][0]);
        return (int) (p % mod);
    }

    void dfs(int[][] grid, int i, int j, long curr) {
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            p = Math.max(p, curr);
            return;
        }
        if (grid[i][j] == 0) {
            p = Math.max(p, 0);
            return;
        }

        if (i + 1 < grid.length)
            dfs(grid, i + 1, j, curr * grid[i + 1][j]);
        if (j + 1 < grid[0].length)
            dfs(grid, i, j + 1, curr * grid[i][j + 1]);
    }

    int[][]postMin = new int[61][2];
    int[][]postMax = new int[61][2];
    public boolean isPrintable(int[][] targetGrid) {
        for(int[]row : postMin)
            Arrays.fill(row, 61);
        for(int[]row : postMax)
            Arrays.fill(row, 0);
        HashSet<Integer>colors = new HashSet<>();
        int n = targetGrid.length;
        int m = targetGrid[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int color = targetGrid[i][j];
                colors.add(color);
                postMin[color][0] = Math.min(postMin[color][0], i);
                postMin[color][1] = Math.min(postMin[color][1], j);
                postMax[color][0] = Math.max(postMax[color][0], i);
                postMax[color][1] = Math.max(postMax[color][1], j);
            }
        }
        
        
        while(colors.size() > 0){
            HashSet<Integer>temp = new HashSet<>();
            for(int color : colors){
                if(!isReact(targetGrid, color))
                    temp.add(color);
            }
            if(colors.size() == temp.size())
                return false;
            colors = temp;
        }
        
        return true;
    }
    boolean isReact(int[][]A, int c){
        for(int i = postMin[c][0]; i <= postMax[c][0]; i++){
            for(int j = postMin[c][1]; j <= postMax[c][1]; j++){
                if(A[i][j] > 0 && A[i][j] != c)
                    return false;
            }
        }
        for(int i = postMin[c][0]; i <= postMax[c][0]; i++){
            for(int j = postMin[c][1]; j <= postMax[c][1]; j++){
                A[i][j] = 0;
            }
        }
        
        return true;
    }
}
