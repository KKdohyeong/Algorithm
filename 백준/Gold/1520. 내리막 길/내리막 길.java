import java.util.*;

public class Main {
    public static int[][] values;
    public static int[][] visited;
    public static int[][] dp;
    public static int[] dx = new int[]{0, 1, 0, -1};
    public static int[] dy = new int[]{-1, 0, 1, 0};
    public static int x_size;
    public static int y_size;


    public static void tracking(int y, int x){

        int sum =0;
        for(int k=0; k<4; k++){
            int new_y = y+dy[k];
            int new_x = x+dx[k];

            if(new_x>=0 && new_y>=0 && new_x<x_size && new_y<y_size  && values[y][x] < values[new_y][new_x]){
                //아직 dp를 못찾은 경우
                if(dp[new_y][new_x] == -1){
                    tracking(new_y, new_x);
                    sum+=dp[new_y][new_x];
                }
                //dp를 찾은 경우
                else{
                    sum+= dp[new_y][new_x];
                }

            }
        }

        //System.out.printf("dp[%d][%d] = %d (tracking)\n", y, x, sum);
        dp[y][x] = sum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x=  sc.nextInt();
        x_size = x;
        y_size = y;
        values = new int[y][x];
        visited = new int[y][x];
        dp = new int[y][x];

        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                values[i][j] = sc.nextInt();
                visited[i][j] = 0;
                dp[i][j] = -1;
            }
        }
        dp[0][0] = 1;

        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                if(i==0 && j ==0){
                    continue;
                }
                int sum = 0;
                for(int k=0; k<4; k++){
                    int new_y = i+dy[k];
                    int new_x = j+dx[k];

                    //새로운 곳이 값이 ㄱㅊ은 범위이고 내가 더 작은 값인경우 주위 값을 궁금해한다.
                    if(new_x>=0 && new_y>=0 && new_x<x && new_y<y && values[i][j] < values[new_y][new_x]){
                        //아직 dp를 못찾은 경우
                        if(dp[new_y][new_x] == -1){
                            tracking(new_y, new_x);
                            sum+=dp[new_y][new_x];
                        }
                        //dp를 찾은 경우
                        else{
                            sum+= dp[new_y][new_x];
                        }

                    }

                }
                //System.out.printf("dp[%d][%d] = %d (main)\n", i, j, sum);
                dp[i][j] = sum;


            }
        }
        System.out.printf("%d", dp[y-1][x-1]);

    }
}
