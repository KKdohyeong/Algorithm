
import java.io.*;
import java.util.*;



public class Main
{

    static int[][] values;
    static int[][][] visited;
    // 위 오른쪽 아래 왼쪽
    static int[] dx1 = {0, 1, 0 ,-1};
    static int[] dy1 = {-1, 0, 1, 0};

    //오른쪽 위부터
    static int[] dx2 = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dy2 = {-2, -1, 1, 2 ,2, 1 ,-1, -2};




    public static int BFS(int x_size, int y_size, int max_k){
        Queue<int[]> queue = new LinkedList<>();

        //초기 시작
        queue.add(new int[]{0,0,0});
        visited[0][0][0] = 1;
        int count = 0;
        while(!queue.isEmpty()){
            int size = queue.size();


            for(int l=0; l<size; l++){
                int[] y_x_k = queue.poll();
                int y = y_x_k[0];
                int x = y_x_k[1];
                int k = y_x_k[2];

                //종료조건
                if(x==x_size-1 && y==y_size-1){
                    return count;
                }

                //먼저 이동 가능한 하나씩

                for(int i=0; i<4; i++){
                    int new_y = y + dy1[i];
                    int new_x = x + dx1[i];
                    //이동하려는 좌표가 가능한 좌표이면서 장애물도 없고 현재 k인 버전에서 방문도 안했을 경우
                    if(new_x >=0 && new_x < x_size && new_y >=0 && new_y < y_size && visited[new_y][new_x][k] ==0 && values[new_y][new_x]==0 ){
                        queue.add(new int[]{new_y, new_x, k});
                        visited[new_y][new_x][k] = 1;
                    }
                }


                //말로 이동해보자
                for(int i=0; i<8; i++){
                    int new_y = y + dy2[i];
                    int new_x = x + dx2[i];
                    int new_k = k+1;
                    //이동하려는 좌표가 가능한 좌표이면서 장애물도 없고 k가 max이하인지 확인
                    if(new_x >=0 && new_x < x_size && new_y >=0 && new_y < y_size && new_k <= max_k  && values[new_y][new_x]==0 && visited[new_y][new_x][new_k]==0 ){
                        queue.add(new int[]{new_y, new_x, new_k});
                        visited[new_y][new_x][new_k] = 1;
                    }
                }

            }
            count++;

        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        values = new int[y][x];
        visited = new int[y][x][k+1];

        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                values[i][j] = sc.nextInt();
            }
        }
        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                for(int l=0; l<=k; l++){
                    visited[i][j][l] = 0;
                }
            }
        }


        int answer=  BFS(x, y, k);
        System.out.println(answer);
    }
}
