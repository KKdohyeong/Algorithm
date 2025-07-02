import java.util.*;
import java.lang.*;
import java.io.*;



// The main method must be in a class named "Main".
class Main {

    public static void debug_array(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.printf("%d ", array_going[i][j]);
            }
            System.out.println();
        }
    }

    public static class Where{
        public int row;
        public int col;
        public int value;

        public Where(int row, int col, int value){
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }

    public static void update(int row, int col){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(array_going[i][row] != Integer.MAX_VALUE && array_going[row][j] != Integer.MAX_VALUE){
                    array_going[i][j] = Math.min(array_going[i][j], array_going[i][row] + array_going[row][j]);                
                }

                if(array_going[i][col] != Integer.MAX_VALUE && array_going[col][j] != Integer.MAX_VALUE){
                    array_going[i][j] = Math.min(array_going[i][j], array_going[i][col] + array_going[col][j]);                
                }
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(array_going[i][row] != Integer.MAX_VALUE && array_going[row][j] != Integer.MAX_VALUE){
                    array_going[i][j] = Math.min(array_going[i][j], array_going[i][row] + array_going[row][j]);                
                }

                if(array_going[i][col] != Integer.MAX_VALUE && array_going[col][j] != Integer.MAX_VALUE){
                    array_going[i][j] = Math.min(array_going[i][j], array_going[i][col] + array_going[col][j]);                
                }
            }
        }        
    }
    
    public static int solution(){
        while(!queue.isEmpty()){
            // 해당 좌표의 값이 실제에서 무한대 or   이 케이스는 아직 생각하지 말까?? 나보다 크다면
            Where where = queue.poll();
            int row = where.row;
            int col = where.col;
            int value = where.value;
            if(array_going[row][col] == Integer.MAX_VALUE){
                //System.out.printf("첫번째 (%d,%d) %d\n", row, col, value);
                array_going[col][row] = value;
                array_line[col][row] = value;
                array_going[row][col] = value;
                array_line[row][col] = value;
            }
            // 해당 좌표가 이미 값이 존재하다면 
            else{
                if(array_going[row][col] > value){
                    //System.out.printf("두번째 (%d,%d) %d\n", row, col, value);
                    array_going[col][row] = value;
                    array_line[col][row] = value;
                    array_going[row][col] = value;
                    array_line[row][col] = value;
                }
            }
            update(row, col);
            
            
        }
        return 1;
    }

    public static boolean check_false(){
        boolean okay = true;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(array_total[i][j] != array_going[i][j]){
                    okay = false;
                }
            }
        }
        return okay;
    }

    
    public static int N;
    public static int[][] array_total;
    public static int[][] array_line;
    public static int[][] array_going;
    public static PriorityQueue<Where> queue;

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        array_total = new int[N][N];
        array_line = new int[N][N];
        array_going = new int[N][N];
        
        
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                array_total[i][j] = sc.nextInt();
                array_line[i][j] = Integer.MAX_VALUE;
                array_going[i][j] = Integer.MAX_VALUE;
                if(i==j){
                    array_total[i][j] = 0;
                    array_line[i][j] = 0;
                    array_going[i][j] = 0;
                }
            }
        }

        queue = new PriorityQueue<>(new Comparator<Where>(){
            @Override
            public int compare(Where o1, Where o2){
                return Integer.compare(o1.value, o2.value);
            }
        });
        
        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                queue.add(new Where(i, j, array_total[i][j]));    
            }
        }
/*
        while(!queue.isEmpty()){
            Where where = queue.poll();
            System.out.printf("(%d,%d) 값:  %d\n", where.row, where.col, where.value);
        }
  */      

        solution();
        int answer = 0;
        for(int i=0; i<N; i++){
            for(int j=i; j<N; j++){
                if(array_line[i][j] != Integer.MAX_VALUE){
                    answer += array_line[i][j];
                }
            }
        }
        if(!check_false()){
            answer = -1;
        }
        System.out.println(answer);
        //debug_array();
    }
}