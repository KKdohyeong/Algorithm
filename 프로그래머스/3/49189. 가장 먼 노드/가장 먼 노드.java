import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        // 인접 리스트 초기화 (1~n 사용)
        List<Integer>[] my_graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            my_graph[i] = new ArrayList<>();
        }

        // 무방향 그래프: a<->b
        for (int[] e : edge) {
            int a = e[0];
            int b = e[1];
            my_graph[a].add(b);
            my_graph[b].add(a);
        }

        // --- Dijkstra 준비 ---
        // dist[x] = 1번 노드로부터 x번 노드까지의 최단 거리
        // (가중치가 없으니, "간선 개수"가 곧 거리)
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;  // 시작 노드(1번)는 거리 0

        // 우선순위 큐(최소 힙): (현재까지 거리, 노드)
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        pq.offer(new int[]{0, 1}); // 1번 노드, 거리 0

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentDist = current[0];
            int currentNode = current[1];

            if (dist[currentNode] < currentDist) {
                continue;
            }

            // 인접 노드 확인
            for (int next : my_graph[currentNode]) {
                int nextDist = currentDist + 1;
                if (dist[next] > nextDist) {
                    dist[next] = nextDist;
                    pq.offer(new int[]{nextDist, next});
                }
            }
        }

        int maxDist = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] != Integer.MAX_VALUE && dist[i] > maxDist) {
                maxDist = dist[i];
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == maxDist) {
                answer++;
            }
        }

        return answer;
    }
}
