import java.util.*;

public class Weekly207 {
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
}
