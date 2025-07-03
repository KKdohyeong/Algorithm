import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();      // 좌석 수
        int M = sc.nextInt();      // VIP 수

        boolean[] vip = new boolean[N + 1];
        for (int i = 0; i < M; i++) vip[sc.nextInt()] = true;

        // 0~N 구간용 피보나치 미리 계산
        int[] f = new int[N + 1];
        f[0] = 1; f[1] = 1;
        for (int i = 2; i <= N; i++) f[i] = f[i - 1] + f[i - 2];

        int answer = 1, len = 0; 
        for (int seat = 1; seat <= N; seat++) {
            if (vip[seat]) {          
                answer *= f[len];
                len = 0;
            } else {
                len++;
            }
        }
        answer *= f[len];            
        System.out.println(answer);
    }
}
