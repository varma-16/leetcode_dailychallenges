import java.util.*;

class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        // making adj list and indegree:
        HashMap<Integer, ArrayList<Integer>> hmap = new HashMap<>();
        int[] indegree = new int[n];
        for (int i = 0; i < relations.length; i++) {
            int a = relations[i][1] - 1;
            int b = relations[i][0] - 1;
            ArrayList<Integer> arr = hmap.getOrDefault(b, new ArrayList<>());
            arr.add(a);
            hmap.put(b, arr);
            indegree[a]++;
        }
        int ans[] = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = time[i];
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                indegree[i]--;
            }
        }
        // bfs is used to find the number of min months are need to complete the task:
        while (!queue.isEmpty()) {
            int m = queue.size();
            for (int i = 0; i < m; i++) {
                int node = queue.poll();
                ArrayList<Integer> arr = hmap.getOrDefault(node, new ArrayList<>());
                for (int j : arr) {
                    ans[j] = Math.max(ans[j], time[j] + ans[node]);
                    indegree[j]--;
                    if (indegree[j] == 0) {
                        queue.add(j);
                    }
                }
            }
        }
        int maxi = 0;
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, ans[i]);

        }
        return maxi;
    }
}