import java.util.*;

public class Main {
    static int[][] DP = new int[10001][4];  

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        init();  
        getDP(); 

        int T = sc.nextInt();             
        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();            
            int result = DP[N][1] + DP[N][2] + DP[N][3];
            System.out.printf("%d%n", result);
        }

        sc.close();
    }


    static void getDP() {
        for (int i = 4; i < 10001; i++) {
            DP[i][1] = DP[i - 1][1];
            DP[i][2] = DP[i - 2][1] + DP[i - 2][2];
            DP[i][3] = DP[i - 3][1] + DP[i - 3][2] + DP[i - 3][3];
        }
    }

    // 
    static void init() {
        DP[1][1] = DP[2][1] = DP[2][2] = DP[3][1] = DP[3][2] = DP[3][3] = 1;
    }
}
