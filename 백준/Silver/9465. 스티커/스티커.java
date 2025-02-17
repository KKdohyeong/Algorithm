import java.util.*;



public class Main
{


    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int count = sc.nextInt();


    for(int i=0; i<count; i++){
        int size = sc.nextInt();
        int[][] dp = new int[2][size];
        int[][] values = new int[2][size];
        //초기값설정
        for(int j=0; j<2; j++){
            for(int k=0; k<size; k++){
                values[j][k] = sc.nextInt();
            }
        }

        for(int j=0; j<size; j++){
            if(j==0){
                dp[0][j] = values[0][j];
                dp[1][j] = values[1][j];

            }
            else if(j==1){
                dp[0][1] = dp[1][0] + values[0][1];
                dp[1][1] = dp[0][0] + values[1][1];

            }
            else{
                int max_temp = Math.max(dp[0][j-2], dp[1][j-2]);
                dp[0][j] = Math.max(max_temp, dp[1][j-1]) + values[0][j];

                dp[1][j] = Math.max(max_temp, dp[0][j-1]) + values[1][j];
            }

        }
        for(int j=0; j<size; j++){
            //System.out.printf("%d ", dp[0][j]);
        }
        System.out.println(Math.max(dp[0][size-1], dp[1][size-1]));


    }


    }
}
