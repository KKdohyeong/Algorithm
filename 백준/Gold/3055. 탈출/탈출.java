import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
// 3 * 3이니까 나는 5 * 5로 만들것이다.
// 매 분마다 해야하는 것
/*
1. 물 전체 이동 
2. 고슴도치 이동


*/


class Main {

    public static void debug_2d(int[][] array){
        System.out.println();
        for(int i=0; i<R+2; i++){
            System.out.println();
            for(int j = 0; j<C+2; j++){
                System.out.printf("%d ", array[i][j]);
            }
        }    
    }


    //움직이지못하면지우버리자
    public static void bfs_water_start(int start_row, int start_col, int[][] visited){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start_row, start_col});
        visited[start_row][start_col] = 1;

        while(!queue.isEmpty()){
            int[] where = queue.poll();

            for(int i=0; i<4; i++){
                int next_row = where[0] + dr[i];
                int next_col = where[1] + dc[i];

                if(array_2d[next_row][next_col] == 0 && visited[next_row][next_col] == 0){
                    array_2d[next_row][next_col] = 3;
                    visited[next_row][next_col] = 1;
                }
                
            }
            
        }
    }
    
    public static void bfs_water(){
        int[][] visited = new int[R+2][C+2];
        for(int i=0; i<R+2; i++){
            for(int j = 0; j<C+2; j++){
                visited[i][j] = 0;
            }
        }

        for(int i=0; i<R+2; i++){
            for(int j = 0; j<C+2; j++){
                if(array_2d[i][j] == 3 && visited[i][j] == 0){
                    bfs_water_start(i, j, visited);
                }
            }
        }        
        


        
    }


    //움직일 수 있는 애들이 쪼로로로 움직ㄱ일거야. 근데 아무것도 못움직이면 사라져야지.
    public static boolean bfs_person_start(int start_row, int start_col, int[][] visited){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {start_row, start_col});
        visited[start_row][start_col] = 1;
        while(!queue.isEmpty()){
            int[] where = queue.poll();

            for(int i=0; i<4; i++){
                int next_row = where[0] + dr[i];
                int next_col = where[1] + dc[i];

                if(array_2d[next_row][next_col] == 0 && visited[next_row][next_col] == 0 && total_visit[next_row][next_col] == 0){
                    array_2d[next_row][next_col] = 2;
                    visited[next_row][next_col] = 1;
                    total_visit[next_row][next_col] = 1;
                }
                if(array_2d[next_row][next_col] == 4){
                    return true;
                }
                
            }
            
        }
        array_2d[start_row][start_col] = 0;
        return false;
    }
    
    public static boolean bfs_person(){
        int[][] visited = new int[R+2][C+2];
        for(int i=0; i<R+2; i++){
            for(int j = 0; j<C+2; j++){
                visited[i][j] = 0;
            }
        }
        boolean my_bool = false;
        for(int i=0; i<R+2; i++){
            for(int j = 0; j<C+2; j++){
                if(array_2d[i][j] == 2 && visited[i][j] == 0){
                    my_bool = bfs_person_start(i, j, visited);
                    if(my_bool){
                        return true;
                    }
                }
            }
        }        
        return false;
    }

    public static boolean check_exist(){
        boolean exist = false;
        for(int i=0; i<R+2; i++){
            for(int j = 0; j<C+2; j++){
                if(array_2d[i][j] == 2){
                    exist = true;
                }
            }
        }
        return exist;
    }
    

    public static int answer(){
        int result = 0;
        int out = -1;
        while(check_exist()){
         
        bfs_water();
        if(bfs_person()){
            out = 1;
            result++;
            break;
        }
        //debug_2d(array_2d);
        result++;
        }
        if(out == 1){
            return result;
        }
        return -1;
    }


    // 0은 빈공간 1은 벽 2는 두더지 3은 물 4는 도착지
    public static int R;
    public static int C;
    public static int[][] array_2d;
    //위, 아래, 오른쪽, 왼쪽
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, 1, -1};
    public static int[][] total_visit;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        array_2d = new int[R+2][C+2];
        total_visit = new int[R+2][C+2];
        for(int i=0; i<R+2; i++){
            for(int j = 0; j<C+2; j++){
                array_2d[i][j] = 1;
                total_visit[i][j] = 0;
            }
        }
        
        for(int i=0; i<R; i++){
            String s = sc.next();
            for(int j=0; j<C; j++){
                char c = s.charAt(j);
                if(c=='D'){
                    array_2d[i+1][j+1] = 4;
                }
                if(c=='*'){
                    array_2d[i+1][j+1] = 3;
                    
                }
                if(c=='S'){
                    total_visit[i+1][j+1] = 1;
                    array_2d[i+1][j+1] = 2;
                    
                }
                if(c=='.'){
                    array_2d[i+1][j+1] = 0;       
                }
                if(c=='X'){
                    array_2d[i+1][j+1] = 1;
                }
            }
        }
        int result = answer();
        if(result == -1){
            System.out.println("KAKTUS");
        }
        else{
            System.out.println(result);
            
        }
    }
}