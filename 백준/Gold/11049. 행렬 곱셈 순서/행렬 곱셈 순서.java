
import java.io.*;
import java.util.*;
/*
지금 백트래킹을 할까 했는데 500개...
500개면 너무 많은 것 같아.

그러면 이제 다른 방식으로 생각해야하는데..

특징

(1) 순서대로 무조건 있고 아무렇게나 짝을 지어도 (순서는 동일) 곱이 가능하다
(2)


 */



public class Main {

    public static int[][] dp;
    public static int[][] values;
    public static int size;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        size=  sc.nextInt();
        dp = new int[size][size];
        values = new int[size][size];
        for(int i=0; i<size; i++){
            values[i][0] = sc.nextInt();
            values[i][1] = sc.nextInt();
            for(int j=0; j<size; j++){
                dp[i][j] = Integer.MAX_VALUE;
                if(i==j){
                    dp[i][j] = 0;
                }
            }
        }


        for(int i = size - 1; i >= 0; i--) {
            // j는 i+1부터 size - 1까지
            for(int j = i + 1; j < size; j++) {
                // k로 i부터 j-1까지 중간 지점 탐색
                for(int k = i; k < j; k++) {
                    int cost = dp[i][k]
                            + dp[k+1][j]
                            + values[i][0] * values[k][1] * values[j][1];

                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        System.out.println(dp[0][size-1]);


    }


}
