import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static int[][] array;
    public static int[][][] visited;

    public static int N;
    public static int M;
//동서남북
    public static int[] dr = {0, 0, 1, -1};
    public static int[] dc = {1, -1, 0, 0};

    

    public static int bfs(int s_row, int s_col, int f_row, int f_col){

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {s_row, s_col, 0});
        visited[s_row][s_col][0] = 1;
        int answer = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int k =0; k<size; k++){
                int[] where = queue.poll();
                 
                for(int i=0; i<4; i++){
                    int next_row = where[0] + dr[i];
                    int next_col = where[1] + dc[i];
                    if(next_row == f_row && next_col == f_col){
                        answer++;
                        return answer;
                    }
                    int count = where[2];
                    if(next_row <0 || next_col <0 || next_row >N || next_col > M){
                        continue;
                    }
                    //벽인데 아직 난 안뚫었고 그 곳은 1로 방문 안했을 경우
                    if(array[next_row][next_col] == 1 && count ==0 && visited[next_row][next_col][1] == 0){
                        queue.add(new int[] {next_row, next_col, count+1});
                        visited[next_row][next_col][count+1] = 1;
                    }
    //빈방인데 visited가 아닌 경우
                    else if(array[next_row][next_col] == 0 && visited[next_row][next_col][count] == 0){
                        queue.add(new int[] {next_row, next_col, count});
                        visited[next_row][next_col][count] = 1;
                    }
                }   
            }
            answer++;
        }

        
        return -1;
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        array = new int[N+2][M+2];
        visited = new int[N+2][M+2][2];

        for(int i=0; i< N+2; i++){
            String str = "nothing";
            if(i>0 && i< N+1){
                str = sc.next();
            }
            for(int j=0; j < M+2; j++){
                if(i==0 || j==0 || i == N+1 || j == M+1){
                    array[i][j] = 1;
                    continue;
                }

                if(str.charAt(j-1) == '0'){
                    array[i][j] = 0;
                }
                else{
                    array[i][j] = 1;
                }
                
                
            }
        }


        for(int i=0; i< N+2; i++){
            for(int j=0; j < M+2; j++){
                for(int k=0; k<1; k++){
                    visited[i][j][k] = 0;
                }
            }
        }
/*
        for(int i=0; i< N+2; i++){
            for(int j=0; j < M+2; j++){
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        */
        if(N==1 && M==1){
            System.out.println(1);
        }
        else{
            System.out.println(bfs(1,1,N, M));
            
        }
        

        
    }

    
}