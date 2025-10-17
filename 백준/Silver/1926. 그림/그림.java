import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static int r;
    public static int c;
    public static int count =0;
    public static int max= 0;
    
    public static int[][] arrays;
    public static int[][] visited;

    //동서남북
    public static int[] dr = {0, 0, 1, -1};
    public static int[] dc= {1, -1, 0, 0};


    public static boolean check(int row, int col){

        if(row<0 || col < 0 || row>=r || col>=c){
            return false;
        }

        return true;
    }
    
    public static void dfs(int start_row, int start_col){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start_row, start_col});
        visited[start_row][start_col] = 1;

        int value = 0;
        while(!queue.isEmpty()){
            value++;

            int[] where = queue.poll();

            for(int i=0; i<4; i++){
                int next_row = where[0] + dr[i];
                int next_col = where[1] + dc[i];

                if(check(next_row, next_col) && visited[next_row][next_col]==0 && arrays[next_row][next_col] ==1){
                     queue.add(new int[] {next_row, next_col});
                    visited[next_row][next_col] = 1;
                }
            }
            
        }


        max = Math.max(max, value);
    }

    
    public static void solution(){
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(visited[i][j] == 0 && arrays[i][j] ==1){
                    dfs(i,j);
                    count++;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();

        arrays =new int[r][c];
        visited = new int[r][c];
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                int value = sc.nextInt();
                arrays[i][j] = value;
                visited[i][j] = 0;
            }
        }

        solution();

        System.out.println(count);
        System.out.println(max);
        
    }
}