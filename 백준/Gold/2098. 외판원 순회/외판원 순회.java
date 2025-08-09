import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] W;
    static int[][] memo;
    static int FULL;
    static final int INF = 1_000_000_000;

    // tsp(mask, u): mask를 방문했고 현재 u에 있을 때,
    // 남은 도시 모두 방문하고 0으로 돌아가는 최소 비용
    static int tsp(int mask, int u) {
        if (mask == FULL) return W[u][0] == 0 ? INF : W[u][0];
        if (memo[mask][u] != -1) return memo[mask][u];

        int best = INF;
        for (int v = 0; v < N; v++) {
            if ((mask & (1 << v)) != 0) continue;
            if (W[u][v] == 0) continue;
            int cand = W[u][v] + tsp(mask | (1 << v), v);
            if (cand < best) best = cand;
        }
        return memo[mask][u] = best;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) W[i][j] = Integer.parseInt(st.nextToken());
        }
        FULL = (1 << N) - 1;
        memo = new int[1 << N][N];
        for (int i = 0; i < (1 << N); i++) Arrays.fill(memo[i], -1);

        int ans = tsp(1, 0); // 0에서 출발
        System.out.println(ans >= INF ? -1 : ans);
    }
}
