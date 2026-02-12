import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {


    public static boolean check(int row, int col){
        if(row<0 || col <0 || row>=R || col>=C){
            return false;
        }
        return true;
    }
    
    
    public static int R;
    public static int C;

    public static int[][] arrays;
    public static int[][] visited;;

    //오른족, 아래, 왼쪽, 위에
    public static int[] dr = {0, 1, 0, -1};
    public static int[] dc = {1, 0, -1, 0};

    public static Queue<int[]> queue = new LinkedList<>();
    public static Queue<int[]> fire_queue =new LinkedList<>();
    // (#,1,벽), (., 0, 가능),  (F, 2, 불 위치) , (J, 3, 지훈이 위치),
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        
        arrays = new int[R][C];
        visited = new int[R][C];

        for(int i=0; i<R; i++){
            String str = sc.next();
            for(int j=0; j<C; j++){
                
                visited[i][j] =0;
                Character c = str.charAt(j);
                if(c=='#'){
                    arrays[i][j] = 1;
                }
                else if(c=='.'){
                    arrays[i][j] = 0;
                }
                else if(c=='F'){
                    arrays[i][j] = 2;
                    fire_queue.add(new int[]{i,j});
                }
                else if(c=='J'){
                    arrays[i][j] = 3;
                    visited[i][j]=1;
                    queue.add(new int[]{i,j});
                }
            }
        }

        int number = 0;
        int answer = -1;
        while(!queue.isEmpty()){
            int size = queue.size();

    // (#,1,벽), (., 0, 가능),  (F, 2, 불 위치) , (J, 3, 지훈이 위치),


            //사람을 이동시켜
            for(int i=0; i<size; i++){
                int[] where = queue.poll();
                int row = where[0];
                int col = where[1];

                //내 위치 불타면 넘겨버령
                if(arrays[row][col]==2){
                    continue;
                }


                //불 안타고있으면 4가지 방향 이동 가능 해봐야지
                for(int j=0; j<4; j++){
                    int next_row =row + dr[j];
                    int next_col = col + dc[j];


                    if(next_row<0 || next_col <0 || next_row >=R || next_col >=C){
                        answer = number+1;
                        System.out.println(answer);
                        return;
                    }

                    //불 안나야하고 벽아니여야하고 지훈이가 방문 안했어야해. 
                    if(check(next_row, next_col) && arrays[next_row][next_col] != 1 && arrays[next_row][next_col] != 2 && visited[next_row][next_col] ==0){

                        visited[next_row][next_col]=1;
                        queue.add(new int[] {next_row, next_col});
                    }
                }
        
                
                
            }

            //불을 이동시켜
            //새로운 곳을 감염시키는 경우에만 (. 또는 지훈이 위치 가능) 에만 추가한다.
            size = fire_queue.size();

            for(int i=0; i<size; i++){
                int[] where = fire_queue.poll();
                int row = where[0];
                int col = where[1];


                for(int j=0; j<4; j++){
                    int next_row = row+dr[j];
                    int next_col = col + dc[j];

                    //당연히 방문 가능해야함.  그리고 . 또는 지훈이 첫 위치가능.. 그리고
                    if(check(next_row, next_col) && ( arrays[next_row][next_col] == 0 || arrays[next_row][next_col] == 0 )  ){


                        //이제 추가하자.
                        arrays[next_row][next_col] = 2;

                        fire_queue.add(new int[] {next_row, next_col});
                    }
                    
                }
                
                
            }


            number++;
        }

        System.out.println("IMPOSSIBLE");

        
        
    }
}