import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] dp = new int[n][3];
        int[][] dp_min = new int[n][3];
        
        int[][] array = new int[n][3];

        for(int i=0; i<n; i++){
            for(int j=0; j<3; j++){
                array[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<1; i++){
            for(int j=0; j<3; j++){
                dp[i][j] = array[i][j];
                dp_min[i][j] = array[i][j];
            }
        }
        
        //max구하자
        long answer = 0;
        for(int i=1; i<n; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]) + array[i][0];
            int temp = Math.max(dp[i-1][0], dp[i-1][1]);

            dp[i][1] = Math.max(temp, dp[i-1][2]) + array[i][1];

            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]) + array[i][2];
            if(i==n-1){
            answer = Math.max(dp[i][0], answer);
            answer = Math.max(dp[i][1], answer);
            answer = Math.max(dp[i][2], answer);   
            }
        }
        if(n==1){
            answer = Math.max(dp[0][0], answer);
            answer = Math.max(dp[0][1], answer);
            answer = Math.max(dp[0][2], answer);
        }
        System.out.printf("%d ", answer);

    
        long result = Integer.MAX_VALUE;
        for(int i=1; i<n; i++){
            dp_min[i][0] = Math.min(dp_min[i-1][0], dp_min[i-1][1]) + array[i][0];
            int temp = Math.min(dp_min[i-1][0], dp_min[i-1][1]);

            dp_min[i][1] = Math.min(temp, dp_min[i-1][2]) + array[i][1];

            dp_min[i][2] = Math.min(dp_min[i-1][1], dp_min[i-1][2]) + array[i][2];
            //System.out.printf("(%d,0) 값 : %d  ||  (%d, 1) 값 : %d  ||  (%d, 2) 값 : %d \n", i, dp[i][0], i, dp[i][1], i, dp[i][2]);
            if(i==n-1){
             
            result = Math.min(dp_min[i][0], result);
            result = Math.min(dp_min[i][1], result);
            result = Math.min(dp_min[i][2], result);   
            }
        }
        if(n==1){
            
            result = Math.min(dp_min[0][0], result);
            result = Math.min(dp_min[0][1], result);
            result = Math.min(dp_min[0][2], result);   
        }

        
        System.out.print(result);
        
    }
}