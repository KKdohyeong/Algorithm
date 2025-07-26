import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    // 충분히 큰 값 (최대 1,000,000 × 16 = 16,000,000 < 10^9)
    static final int INF = 1_000_000_000;

    public static int[][] dp;
    public static int[][] array;
    public static int N;
    public static int final_bit;

    // n이 4라면 1111(2) → 1+2+4+8 = 15
    public static void set_final_bit(int n) {
        int start = 1;
        for (int i = 0; i < n; i++) {
            final_bit += start;
            start *= 2;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        set_final_bit(N);                  // final_bit 계산

        array = new int[N][N];
        dp    = new int[N][final_bit + 1];

        // 비용 행렬 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dp 초기화  (INF → -1)
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // 0번 도시를 방문한 상태(비트 0001)에서 출발
        System.out.println(dfs(0, 1));
    }

    // where : 현재 도시, visit : 방문 비트마스크
    public static int dfs(int where, int visit) {

        // 이미 계산된 상태라면 바로 반환  (-1 기준)
        if (dp[where][visit] != -1) {
            return dp[where][visit];
        }

        // 모든 도시를 방문했을 때
        if (visit == final_bit) {
            if (array[where][0] == 0) {
                dp[where][visit] = INF;            // 길이 없으면 INF
            } else {
                dp[where][visit] = array[where][0]; // 원점으로 복귀 비용
            }
            return dp[where][visit];
        }

        int best = INF;

        // 다음 도시 탐색
        for (int i = 0; i < N; i++) {
            int next = visit | (1 << i);           // i 도시 방문 표시
            if (next == visit) continue;           // 이미 방문함
            if (array[where][i] == 0) continue;    // 길 없음

            int candidate = dfs(i, next) + array[where][i];
            if (candidate < best) best = candidate;
        }

        dp[where][visit] = best;                   // 반드시 저장
        return best;
    }
}
