import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
/*
1. 정사각형은 서로 겹치면 안된다.
2. 정사각형은 변끼리 연결
3. 도형은 모두 연결

5가지의 형태가 있음

회전, 대칭 가능

어딘가에 놓아서 최대가 되는 합을 구해라


80가지, 250000가지

2000 0000


못놓는 경우는 없어

회전 : 좌우 바꾸기, 바꾸기 한 후에 왼쪽에 -붙이기

상하 : 

좌우 : 

회전해보자

{ {0,0}, {1,0}, {1,1}, {2,1} 

    좌우 대칭버전
    { {0,0}, {1,0}, {1,-1}, {2,-1} }

    상하 대칭 버전
    { {0,0}, {-1,0}, {-1,1}, {-2,1}  }

위로 90도

{ {0,0} , {0,1}, {-1,1}, {-1, 2} }

또 90도

{ {0,0}, {-1, 0}, {-1,-1}, {-2,-1}  }




*/



class Main {

    public static int N;
    public static int M;

    public static int[][] arrays;
                        
    public static int[][][] possibles ={ { {0,0}, {0,1}, {0,2}, {0,3} }, { {0,0}, {0,1}, {1,0}, {1,1}  },  { {0,0}, {1,0}, {2,0}, {2,1}  }   ,{ {0,0}, {1,0}, {1,1}, {2,1}  } , {  {0,0}, {0,1}, {1,1}, {0,2}} };
    
    public static int result = 0;


    public static int[] rotate(int row, int col){
        return new int[] {-col, row};
    }

    public static int[] up_down (int row, int col){
        return new int[] {-row, col};
    }

    public static int[] left_right (int row, int col){
        return new int[] {row, -col};
    }


    //4개구나 무조건
    public static int get_rotate_value(int row, int col, int k){
        int result = 0;
        for(int i=0; i<4; i++){
            int next_row = row + possibles[k][i][0];
            int next_col = col + possibles[k][i][1];

            if(check(next_row, next_col)){
                result += arrays[next_row][next_col];
            }
            else{
                return 0;
            }
        }


        return result;
        
    }

    public static int get_up_down_value(int row, int col, int k){
        int result = 0;
        for(int i=0; i<4; i++){
            int next_row = row - possibles[k][i][0];
            int next_col = col + possibles[k][i][1];

            if(check(next_row, next_col)){
                result += arrays[next_row][next_col];
            }
            else{
                return 0;
            }
        }


        return result;
        
    }


    public static int get_left_right_value(int row, int col, int k){
        int result = 0;
        for(int i=0; i<4; i++){
            int next_row = row + possibles[k][i][0];
            int next_col = col - possibles[k][i][1];

            if(check(next_row, next_col)){
                result += arrays[next_row][next_col];
            }
            else{
                return 0;
            }
        }


        return result;
        
    }

    
    
    public static int calculate(int row, int col){
        int here_max = 0;
        
        //5개에 대해서
        for(int k=0; k<5; k++){


            //회전을 4번 한다
            for(int i=0; i<4; i++){

                //회전에 대한 도형 움직임 얻기
                int value = get_rotate_value(row, col, k);
                here_max = Math.max(here_max, value);
                //좌우에 대한 값 얻기
                value = get_left_right_value(row, col, k);
                here_max = Math.max(here_max, value);

                //상하에 대한 값 얻기
                value = get_up_down_value(row, col, k);
                here_max = Math.max(here_max, value);


                //회전시키기
                for(int j=0; j<4; j++){
                    int temp = possibles[k][j][0];
                    possibles[k][j][0] = -possibles[k][j][1];
                    possibles[k][j][1] = temp;
                }
            }
        }


        return here_max;
        
    }

    public static boolean check(int row, int col){
        if(row<0 || col <0  || row >=N || col>=M){
            return false;
        }
        return true;
    }

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        arrays = new int[N+1][M+1];
        
        
        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++){
                int value = Integer.parseInt(stk.nextToken());
                arrays[i][j] = value;
            }
        }


        for(int i=0; i<N; i++){

            for(int j=0; j<M; j++){
                int value = calculate(i, j);
                result = Math.max(result, value);
                
            }
        }

        
        System.out.println(result);
    }
}