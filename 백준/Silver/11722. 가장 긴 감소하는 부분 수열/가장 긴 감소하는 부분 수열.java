import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] values = new int[size];
        int[] dp = new int[size];

        for(int i=0; i<size; i++){
            values[i] = sc.nextInt();
            dp[i] = 0;
        }


        dp[0]= 1;
        for(int i=1; i<size; i++){
            int max = 1;
            for(int j=0; j<i; j++){
               if(values[j] > values[i] && dp[j] >= max){
                   max = dp[j]+1;
               }
            }

            dp[i] = max;
            //System.out.printf("dp[%d]의 값은 %d\n", i, dp[i]);
        }

        int max = 0;
        for(int i=0; i<size; i++){
            if(dp[i] > max){
                max = dp[i];
            }
        }


        System.out.printf("%d\n", max);


    }


}