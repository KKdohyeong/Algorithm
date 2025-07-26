import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static void set_all(){
        all_visited = 0;
        int plus = 1;
        for(int i=0; i<10; i++){
            all_visited += (plus);
            plus *= 2;
        }
    }
    
    public static int all_visited;
    public static int N;
    public static long[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        dp = new long[N+2][10][1048];
        set_all();

        for(int i=0; i<=N+1; i++){
            for(int j=0; j<10; j++){
                for(int k=0; k<1048; k++){
                    dp[i][j][k] = 0;
                }
            }
        }
        for(int i=1; i<=1; i++){
            for(int j=1; j<10; j++){
                if(i==1){
                    int visit = 1 << j;
                    dp[i][j][visit] = 1;
                }
            }
            
        }
        //n의 자리수까지 만들어가보자.
        // i는 지금 몇자리의 수라는 의미이고 j는 내가 지금 어느 숫자를 마지막에 넣었다는 말이다 그리고 k는 현재 visit된것들을 의미한다.
        for(int i=1; i<N; i++){
            for(int j=0; j<10; j++){
                //이제 i가 1이상부터 여기 하는 것
                for(int k=0;k <1024; k++){
                    if(dp[i][j][k]>0){
                        //System.out.printf("%d번째 자연수까지 읽었을 때 %d에 있을 경우 %d만큼 방문됐고 %d값이다\n", i, j, k, dp[i][j][k]);
                    }
                    //내가 현재 있는 값이 j이다.
                    if(j==0){
                        int next_visit = k | 2; 

                        dp[i+1][1][next_visit] += dp[i][j][k];
                        dp[i+1][1][next_visit] = dp[i+1][1][next_visit] % 1000000000;
                    }
                    else if(j==9){
                        int next_visit = k | 1<<8;
                        dp[i+1][8][next_visit] += dp[i][j][k];
                        dp[i+1][8][next_visit] = dp[i+1][8][next_visit] % 1000000000;
                    }
                    else{
                        //이건 하나 빼자
                        int next_visit_one = k | 1<<(j-1);
                        int next_visit_two = k | 1<<(j+1);

                        //하나줄어드는경우
                        dp[i+1][j-1][next_visit_one] += dp[i][j][k];
                        dp[i+1][j+1][next_visit_two] += dp[i][j][k];
                        dp[i+1][j-1][next_visit_one] = dp[i+1][j-1][next_visit_one] % 1000000000;
                        dp[i+1][j+1][next_visit_two] = dp[i+1][j+1][next_visit_two] % 1000000000;
                    }
                }
            }
            
        }
        int result = 0;
        for(int i=0; i<10; i++){
            result += dp[N][i][all_visited];
            result = result % 1000000000;
        }
        System.out.println(result);
        
    }
}