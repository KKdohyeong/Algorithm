import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int how_many = sc.nextInt();
        for(int l = 0; l < how_many; l++){
            int size = sc.nextInt();

            // (1) values 입력 먼저
            int[] values = new int[size];
            for(int i = 0; i < size; i++){
                values[i] = sc.nextInt();
            }

            // (2) prefix_sum 계산
            //     prefix_sum[i] = values[0] + ... + values[i]
            int[] prefix_sum = new int[size];
            prefix_sum[0] = values[0];
            for(int i=1; i<size; i++){
                prefix_sum[i] = prefix_sum[i-1] + values[i];
            }

            // (3) dp 테이블 초기화
            int[][] dp = new int[size][size];

            // (4) 구간 DP 로직
            //     i: 구간 끝, j: 구간 시작
            for(int i=1; i<size; i++){
                for(int j=i-1; j>=0; j--){
                    dp[j][i] = Integer.MAX_VALUE;

                    // (4-1) [j..i]를 [j..k], [k+1..i]로 나누어 합치는 비용
                    for(int k=j; k < i; k++){
                        dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k+1][i]);
                    }

                    // (4-2) 구간 [j..i]의 파일 크기 합 추가
                    if(j == 0) {
                        // [0..i] 합 = prefix_sum[i]
                        dp[j][i] += prefix_sum[i];
                    } else {
                        // [j..i] 합 = prefix_sum[i] - prefix_sum[j-1]
                        dp[j][i] += prefix_sum[i] - prefix_sum[j-1];
                    }
                }
            }

            // (5) 결과 출력
            System.out.println(dp[0][size-1]);
        }
    }
}
