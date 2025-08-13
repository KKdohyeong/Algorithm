import java.io.*;
import java.util.*;

class Main {

    static class Node {
        int value, weight;
        Node(int value, int weight) { this.value = value; this.weight = weight; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Node[] items = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value  = Integer.parseInt(st.nextToken());
            items[i] = new Node(value, weight);
        }

        int[] dp = new int[K + 1]; 

        for (Node it : items) {
            for (int cap = K; cap >= it.weight; cap--) {
                dp[cap] = Math.max(dp[cap], dp[cap - it.weight] + it.value);
            }
        }

        System.out.println(dp[K]);
    }
}
