import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arrays = new int[n+1];
        int[][] dp = new int[3][n+1];

        Arrays.fill(arrays, 0);
        for(int i=0; i<3; i++){
            Arrays.fill(dp[i], 0);
        }

        for(int i=0; i<n; i++){
            int value = sc.nextInt();

            arrays[i] = value;
        }

        dp[1][0] = arrays[0];
        for(int i=1; i<n; i++){
            int one = Math.max(dp[0][i-1], dp[1][i-1]);
            dp[0][i] = Math.max(one, dp[2][i-1]);
            dp[1][i] = dp[0][i-1] + arrays[i];
            dp[2][i] = dp[1][i-1] + arrays[i];
        }

            int one = Math.max(dp[0][n-1], dp[1][n-1]);
            int result = Math.max(one, dp[2][n-1]);
            System.out.println(result);        
        
        
    }
}