import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".



//회전 하는 방법





class Main {

    public static void debug(){
        System.out.println();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                System.out.printf("%d ", answers[i][j]);
            }
            System.out.println();
        }
    }
    
    public static void debug_array(int row, int col, Building building){
        System.out.println();
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                System.out.printf("%d ", building.arrays[i][j]);
            }
            System.out.println();
        }
    }


    public static class Building{
        public int row;
        public int col;
        public int arrays[][];

        public Building(int row, int col){
            this.row = row;
            this.col = col;
            arrays = new int[row][col];
        }
    }
    
    public static Building Rotate(Building building){
//이제 row랑 col을 바꿔버린다.

        int row = building.row;
        int col = building.col;

        Building after_Building = new Building(col, row);

        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                after_Building.arrays[j][row-1-i] = building.arrays[i][j];
            }
        }


        return after_Building;
        
    }


    public static boolean check(int row, int col, Building building){
        for(int i=row; i<row + building.row; i++){
            for(int j=col; j< col + building.col; j++){
                //이제 내 building은 1인데 answers은 1이면 실패

                if(i < 0 || j < 0 || i>= N || j >= M){
                    return false;
                }
                
                if(building.arrays[i-row][j-col] == 1 && answers[i][j] == 1){
                    return false;
                }
            }
        }

        return true;
    }

    public static void put(int row, int col, Building building){
        for(int i=row; i<row + building.row; i++){
            for(int j=col; j< col + building.col; j++){

                if(building.arrays[i-row][j-col] == 1 && answers[i][j] == 0){
                    answers[i][j] = 1;
                }
                else if(building.arrays[i-row][j-col] == 1 && answers[i][j] == 1){
                    System.out.println("에러발생");
                }
            }
        }        
    }

    public static void put_building(Building building){
        for(int k=0; k<4; k++){
            //먼저 가능한 위치를 찾는다

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){

                        //이제 확인해보자 붙일 수 잇는지
                        //여긴 이제 가능한거야 그럼 붙여야지
                        if(check(i, j, building)){
                            put(i, j, building);
                            return;
                        }
                        
                        
                    
                }
            }

            //다 실패했으면 돌려봐야죠?
            //System.out.println("엥");
            building =Rotate(building);
            //debug_array(building.row, building.col, building);

        }
    }

    
    public static int N;
    public static int M;
    public static int K;

    public static int[][] answers;
    
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        answers = new int[N][M];

        for(int i=0; i<N; i++){
            Arrays.fill(answers[i], 0);
        }
        
        for(int k=0; k<K; k++){
            int b_row = sc.nextInt();
            int b_col = sc.nextInt();

            Building building = new Building(b_row, b_col);

            for(int i=0; i<b_row; i++){
                for(int j=0; j<b_col; j++){
                    building.arrays[i][j] = sc.nextInt();
                }
            }
         
            //debug_array(b_row, b_col, building);
 /*           
            Building after = Rotate(building);
            debug_array(after.row, after.col, after);

            after = Rotate(after);
            debug_array(after.row, after.col, after);

            after = Rotate(after);
            debug_array(after.row, after.col, after);

            
            after = Rotate(after);
            debug_array(after.row, after.col, after);

            */

            put_building(building);
           // debug();
        }

        int result = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(answers[i][j] ==1){
                    result++;
                }
            }
        }
        System.out.println(result);
        
        
    }
}