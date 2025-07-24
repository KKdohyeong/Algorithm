import java.util.*;

class Main {

    // ─── 전역 변수 ───────────────────────────────────────
    public static int k, N, k_value, M, K;
    public static long[] trees;                // ★ int → long

    // k 구하기
    public static void calculate_k() {
        k = 0;
        int value = 1;
        while (value < N) {
            value <<= 1;
            k++;
        }
        k_value = value;
    }

    // 구간 합
    public static long calculate_hap(int start, int finish) {
        long sum = 0;                          // ★ int → long
        while (start <= finish) {

            if ((start & 1) == 1) {            // start % 2 == 1
                sum += trees[start];
            }
            if ((finish & 1) == 0) {           // finish % 2 == 0
                sum += trees[finish];
            }

            start = (start + 1) >> 1;
            finish = (finish - 1) >> 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        calculate_k();

        trees = new long[4 * N];               // ★ long[]
        for (int i = 0; i < N; i++) {
            trees[i + k_value] = sc.nextLong(); // ★ nextLong()
        }

        for (int i = N + k_value - 1; i > 1; i--) {
            trees[i >> 1] += trees[i];
        }

        for (int i = 0; i < M + K; i++) {
            int type = sc.nextInt();

            if (type == 1) {                   // 값 변경
                int idx = sc.nextInt();
                long val = sc.nextLong();      // ★ nextLong()
                trees[idx + k_value - 1] = val;

                // 부모 노드 갱신 (O(log N))
                int p = (idx + k_value - 1) >> 1;
                while (p > 0) {
                    trees[p] = trees[p << 1] + trees[(p << 1) + 1];
                    p >>= 1;
                }

            } else {                           // 구간 합
                int l = sc.nextInt();
                int r = sc.nextInt();
                System.out.println(calculate_hap(l + k_value - 1, r + k_value - 1));
            }
        }
    }
}
