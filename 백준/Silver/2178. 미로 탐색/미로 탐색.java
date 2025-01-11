

import java.util.*;

public class Main {
/*
DNA의 가능 원소는 A, T ,G, C

N개의 DNA가 주어질것이고 길이는 M이다.
이 모든 DNA들과의 hamming distance를 다 합해서 가장 작은 합이 되는 길이가 M인 DNA를 만드는 것이 목적이다.

(1) 모든 원소의 앞을 비교해서 가장 많은 놈을 선택한다.
(2) 만약 같다면 가장 사전순으로 앞에 놈을 선택한다.
 */


    public static int bfs(int[][] maze, int N, int M, int[][] visited){
        Queue<int[]> queue = new LinkedList<int[]>();
        int answer = 0;

        queue.add(new int[]{1, 1});
        visited[1][1] = 1;
        while(!queue.isEmpty()){
            int cycle = queue.size();
            answer++;
            //System.out.println(answer);
            //1cycle을 돌자 , 상하좌우를 보면서 추가가 가능한지 확인
            for(int i=0; i<cycle; i++){
                //위 확인

                int[] location = queue.poll();
                int row = location[0];
                int col = location[1];
                if(row == N && col == M){
                    return answer;
                }
                //System.out.printf("cycle은 %d 이동중인 좌표는 (%d, %d)\n",answer ,row, col);

                if(maze[row-1][col] ==1 && visited[row-1][col]==0){
                    queue.add(new int[]{row-1, col});
                    visited[row-1][col] = 1;
                }

                //왼쪽 확인
                if(maze[row][col-1] == 1 && visited[row][col-1] == 0){
                    queue.add(new int[]{row, col-1});
                    visited[row][col-1] = 1;
                }

                //아래 확인
                if(maze[row+1][col] == 1 && visited[row+1][col]==0){
                    queue.add(new int[]{row+1, col});
                    visited[row+1][col] = 1;
                }

                //오른쪽 확인
                if(maze[row][col+1]==1 && visited[row][col+1] == 0){
                    queue.add(new int[]{row, col+1});
                    visited[row][col+1] = 1;
                }

            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] maze = new int[N+2][M+2];
        int[][] visited = new int[N+2][M+2];
        for(int i=0; i<N+2; i++){
            for(int j=0; j<M+2; j++){
                maze[i][j] = 0;
                visited[i][j] = 0;
            }
        }
        String line = sc.nextLine();

        for(int i=1; i<=N; i++){
            line = sc.nextLine();
            //System.out.printf("get line is %s\n", line);

            for(int j=1; j<=M; j++){
                maze[i][j] = line.charAt(j-1)-'0';
            }
        }


        int answer = bfs(maze, N, M, visited);

        System.out.println(answer);
    }


}