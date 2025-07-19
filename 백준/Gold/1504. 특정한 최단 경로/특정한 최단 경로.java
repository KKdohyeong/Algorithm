import java.util.*;

class Node implements Comparable<Node> {
    int end;
    int weight;

    Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

public class Main {
    static int N, E;
    static ArrayList<ArrayList<Node>> adj;   // 인접 리스트
    static int[] dist;                       // 최단 거리
    static boolean[] visited;                // 방문 여부
    static final int INF = 200_000_000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        E = sc.nextInt();

        adj = new ArrayList<>();
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());

        // 양방향 간선 입력
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adj.get(u).add(new Node(v, w));
            adj.get(v).add(new Node(u, w));
        }

        // 반드시 거쳐야 하는 정점 v1, v2
        int v1 = sc.nextInt();
        int v2 = sc.nextInt();
        sc.close();

        /* 1 → v1 → v2 → N */
        long path1 = 0;
        path1 += dijkstra(1,  v1);
        path1 += dijkstra(v1, v2);
        path1 += dijkstra(v2, N);

        /* 1 → v2 → v1 → N */
        long path2 = 0;
        path2 += dijkstra(1,  v2);
        path2 += dijkstra(v2, v1);
        path2 += dijkstra(v1, N);

        int answer = (path1 >= INF && path2 >= INF) ? -1
                                                   : (int)Math.min(path1, path2);

        System.out.println(answer);
    }

    /** 다익스트라 */
    private static int dijkstra(int start, int end) {
        Arrays.fill(dist, INF);
        Arrays.fill(visited, false);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.end;

            if (visited[u]) continue;
            visited[u] = true;

            for (Node next : adj.get(u)) {
                int v = next.end;
                int w = next.weight;

                if (!visited[v] && dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }
        return dist[end];
    }
}
