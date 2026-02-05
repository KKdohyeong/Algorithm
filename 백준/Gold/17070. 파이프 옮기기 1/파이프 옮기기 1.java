import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {



    public static boolean check(int row, int col){
        if(row < 0 || col < 0 || row >=N || col >=N){
            return false;
        }
        return true;
    }

    public static boolean check_move(int direction, int row, int col){
        if(direction ==0){
            if(arrays[row][col+1]==1){
                return false;
            }
        }
        if(direction==1){
            if(arrays[row][col+1] ==1 || arrays[row+1][col+1] ==1 || arrays[row+1][col]==1){
                return false;
            }
        }
        if(direction ==2){
            if(arrays[row+1][col]==1){
                return false;
            }
        }
        return true;
    }

    public static void dfs(){


        Stack<int[]> stack = new Stack<>();


        //stack 넣는 값 : 전에 이동, row, col 
        stack.add(new int[] {0, 0, 1});
        dp[0][1] = 1;

        while(!stack.isEmpty()){
            int[] where = stack.pop();

            int before_dir = where[0];
            int row = where[1];
            int col = where[2];
            for(int i=0; i<move[before_dir].length; i++){
                int my_move = move[before_dir][i][0];
                int next_row = row+ move[before_dir][i][1];
                int next_col = col + move[before_dir][i][2];


                    //방문 가능하고 0인 곳이라면 이동 가능해 이미 방문한건 딱히..? 
                    if(check(next_row, next_col) && check_move(my_move, row,col) ){
                        dp[next_row][next_col]++;

                        stack.add(new int[] {my_move, next_row, next_col});
                    }
            }
        }
        
    }

    //state를 0,1,2 를 나눌 수 있다. 0은 오른쪽이동 1은 대각선이동 2는 아래이동
    //오른쪽 이동 : 오른족, 대각선    대각선 : 다 가능   아래 : 아래 대각선
    //오른쪽 이동 : (0, 1)   대각선 (1,1)    아래 이동 : (1,0)
    public static int[][][] move = { { {0,0, 1},{1,1,1}   }, { {0,0,1}, {1,1,1}, {2,1,0}  }, { {1,1,1}, {2,1,0}  }  };

    public static int[][] arrays;
    public static int[][] dp;
    public static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        arrays = new int[N][N];
        dp = new int[N][N];
        for(int i=0; i<N; i++){
            Arrays.fill(dp[i], 0);

            for(int j=0; j<N; j++){
                int value = sc.nextInt();

                arrays[i][j] = value;
            }
        }

        dfs();
        
        System.out.println(dp[N-1][N-1]);
    }
}