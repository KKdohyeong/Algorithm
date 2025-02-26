
import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;  // 오름차순(비용이 작은 순)
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static int n, d, c;               // n: 컴퓨터 개수, d: 의존성 개수, c: 해킹당한 컴퓨터 번호
    static List<List<Edge>> graph;    // 인접 리스트
    static int[] dist;                // 각 노드까지의 최소 감염 시간

    public static void main(String[] args) throws IOException {
        // 빠른 입출력을 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            // 그래프 초기화
            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) {
                graph.add(new ArrayList<>());
            }

            // dist 배열도 초기화
            dist = new int[n+1];
            Arrays.fill(dist, INF);

            // d개의 의존성 읽어서 저장
            // a b s : "a가 b를 의존" → "b가 감염되면 s초 뒤 a도 감염"
            // 즉, 그래프 간선은 b -> a, 비용 s
            for(int i=0; i<d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                // b -> a, 비용 = s
                graph.get(b).add(new Edge(a, s));
            }

            // 다익스트라 수행: 시작 노드는 c
            dijkstra(c);

            // 결과 계산
            int count = 0;
            int maxTime = 0;
            for(int i=1; i<=n; i++) {
                if(dist[i] != INF) {
                    count++;
                    maxTime = Math.max(maxTime, dist[i]);
                }
            }

            sb.append(count).append(" ").append(maxTime).append("\n");
        }
        // 출력
        System.out.print(sb);
    }

    // 다익스트라 알고리즘 (우선순위 큐 사용)
    static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curNode = cur.to;
            int curCost = cur.cost;

            // 큐에서 꺼낸 정보가 이미 dist보다 크면 스킵
            if(curCost > dist[curNode]) continue;

            // 인접 노드들 확인
            for(Edge nxt : graph.get(curNode)) {
                int nextNode = nxt.to;
                int nextCost = curCost + nxt.cost;

                // 더 짧은 시간으로 갱신 가능하면 갱신 & 큐에 추가
                if(dist[nextNode] > nextCost) {
                    dist[nextNode] = nextCost;
                    pq.offer(new Edge(nextNode, nextCost));
                }
            }
        }
    }
}
