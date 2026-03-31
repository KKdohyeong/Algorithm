import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".

/*
1   2   3   4   5   6   7   8   9   10일
3   1   3   2   1   3   3   2   1   1
40  10  20  20  30  15  50  30  10  10

0   0   10  40  40  70  70  70  85  120
    
현재까지의 max값은 기억하면 될듯
1일 5일 7일


*/
class Main {

    public static int[] dp;
    
    public static int[] values;
    public static int[] days;
    public static int temp_max = 0;
    public static int answer = 0;
    public static int n;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        values = new int[n];
        days = new int[n];
        dp = new int[n+1];
        Arrays.fill(dp, 0);
        for(int i=0; i<n; i++){
            int day = sc.nextInt();
            int value = sc.nextInt();

            values[i] = value;
            days[i] = day;
        }
        
        for(int i=0; i<n; i++){
            int here_max = Math.max(temp_max, dp[i]);
            
            dp[i] = here_max;

            //System.out.printf("%d번째에서 %d값이 here_max\n", i, here_max);
            answer = Math.max(answer, here_max);

            temp_max = Math.max(temp_max, dp[i]);

            if(i + days[i] > n){
                continue;
            }

            dp[i+ days[i] ] = Math.max(here_max + values[i], dp[i + days[i]]);

            
            
        }
        answer = Math.max(answer, dp[n]);
        System.out.println(answer);
        
    }
}