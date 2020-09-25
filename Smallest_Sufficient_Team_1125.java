import java.util.*;

public class Smallest_Sufficient_Team_1125 {
    public static void main(String[] args) {
    }

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int ns = req_skills.length;
        int np = people.size();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < req_skills.length; i++)
            map.put(req_skills[i], i);
        List<Integer>[] suff = new List[1 << ns];
        suff[0] = new ArrayList<>();
        for (int i = 0; i < np; i++) {
            int skill = 0;
            for (String s : people.get(i))
                skill |= (1 << map.get(s));
            for (int prev = 0; prev < suff.length; prev++) {
                if (suff[prev] == null)
                    continue;
                int comb = skill | prev;
                if (suff[comb] == null || suff[prev].size() + 1 < suff[comb].size()) {
                    suff[comb] = new ArrayList<>(suff[prev]);
                    suff[comb].add(i);
                }
            }
        }
        List<Integer> res = suff[(1 << ns) - 1];
        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++)
            arr[i] = res.get(i);

        return arr;
    }
}