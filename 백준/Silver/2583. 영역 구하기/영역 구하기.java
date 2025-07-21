import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static int[][] array;
    public static int row;
    public static int col;
    public static int[][] visited;
    //동서남북
    public static int[] dr = {0, 0, 1, -1};
    public static int[] dc = {1, -1, 0, 0};
    
    public static void color(int s_row, int s_col, int f_row, int f_col){
        for(int i=s_row; i<f_row; i++){
            for(int j=s_col; j<f_col; j++){
                array[i][j] = 1;
            }
        }
    }


    public static int bfs(int s_row, int s_col){
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[] {s_row, s_col});
        visited[s_row][s_col] = 1;
        int answer = 1;

        while(!queue.isEmpty()){
            int[] where = queue.poll();

            for(int i=0; i<4; i++){
                int next_row = where[0] + dr[i];
                int next_col = where[1] + dc[i];

                if(next_row >=0 && next_col >=0 && next_row < row && next_col < col && array[next_row][next_col] == 0 && visited[next_row][next_col] == 0){
                    visited[next_row][next_col] = 1;
                    answer++;
                    queue.add(new int[] {next_row, next_col});
                }
            }
        }
        return answer;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        col = sc.nextInt();
        row = sc.nextInt();
        int n = sc.nextInt();
        array = new int[row+1][col+1];
        visited = new int[row+1][col+1];

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                array[i][j] = 0;
                visited[i][j] = 0;
            }
        }

        for(int i=0; i<n; i++){
            int s_row = sc.nextInt();
            int s_col = sc.nextInt();
            int f_row = sc.nextInt();
            int f_col = sc.nextInt();
            color(s_row, s_col, f_row, f_col);
        }
/*
        
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        */

        LinkedList<Integer> list = new LinkedList<>();

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(array[i][j] ==0 && visited[i][j] == 0){
                    list.add(bfs(i, j));
                }
            }
        }

        
        

        Collections.sort(list);
        System.out.println(list.size());
        for(int answer : list){
            System.out.printf("%d ", answer);
        }
    }
}