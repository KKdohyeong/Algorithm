
import java.util.*;

public class Main {

    static int[][] visited;
    static int[][] values;
    static int[] dx = {0 ,1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int x_size;
    static int y_size;

    public static int findBFS(int sy, int sx){
        // visited_temp[y][x] = (sy, sx)로부터 (y, x)까지의 "거리"를 저장
        int[][] visited_temp = new int[y_size][x_size];
        Queue<int[]> queue = new LinkedList<>();

        // 초기화: 0이면 아직 방문 안 함
        for(int i=0; i<y_size; i++){
            for(int j=0; j<x_size; j++){
                visited_temp[i][j] = 0;
            }
        }

        // 시작점 설정 (거리=1로 둠)
        visited_temp[sy][sx] = 1;
        queue.add(new int[]{sy, sx});

        // BFS를 진행하면서 가장 먼 지점(거리) 추적
        int maxDist = 1;
        int maxY = sy;
        int maxX = sx;

        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int cy = curr[0];
            int cx = curr[1];

            // 현재 지점의 거리
            int dist = visited_temp[cy][cx];

            // dist가 지금까지의 최대 거리보다 크면 갱신
            if(dist > maxDist){
                maxDist = dist;
                maxY = cy;
                maxX = cx;
            }

            // 상하좌우 탐색
            for(int i=0; i<4; i++){
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                // 맵 범위 내 && 육지(L=0) && 아직 방문 안한 곳
                if(ny>=0 && ny<y_size && nx>=0 && nx<x_size
                        && values[ny][nx] == 0 && visited_temp[ny][nx] == 0){
                    visited_temp[ny][nx] = dist + 1;  // 거리 = 현재(dist) + 1
                    queue.add(new int[]{ny, nx});
                }
            }
        }

        // BFS 종료 후, (sy, sx)에서 가장 먼 지점 좌표 반환
        return maxDist-1;
    }

    /*public static int BFS(int y, int x){
        //System.out.printf("y : %d x : %d\n", y, x);
        //먼저 이 곳을 기준으로 두 정답을 찾아보자
        int[] start_y_x = new int[]{y,x};
        //int[] start_y_x = new int[] {0, 1};

        Queue<int[]> queue = new LinkedList<>();
        int start_y = start_y_x[0];
        int start_x = start_y_x[1];
        //System.out.printf("start BFS y : %d x : %d\n", start_y, start_x);
        queue.add(new int[]{start_y, start_x});
        visited[start_y][start_x] = 1;

        int count=-1;
        //마무리에서 추가된게없으면 종료하자
        while(!queue.isEmpty()){
            int size = queue.size();

            //하나를 꺼내와서
            for(int i=0; i<size; i++){
                int[] y_x = queue.poll();

                //4개의 방향으로 움직여본다.
                for(int j=0; j<4; j++){
                    int new_x = y_x[1] + dx[j];
                    int new_y = y_x[0] + dy[j];

                    if(new_x>=0 && new_y >= 0 && new_x < x_size && new_y < y_size && values[new_y][new_x] == 0 && visited[new_y][new_x] ==0){
                        queue.add(new int[]{new_y, new_x});
                        visited[new_y][new_x] = 1;
                    }
                }

            }
            count++;
        }
        return count;
    }



*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x = sc.nextInt();
        y_size = y;
        x_size = x;
        visited = new int[y][x];
        values = new int[y][x];

        sc.nextLine();
        for(int i=0; i<y; i++){
            String str = sc.nextLine();
            for(int j=0; j<x; j++){
                visited[i][j] = 0;

                char c = str.charAt(j);
                if(c == 'W'){
                    values[i][j] = 1;
                }
                if(c=='L'){
                    values[i][j] = 0;
                }
            }
        }

        int answer = 1;

        for(int i=0; i<y; i++){
            for(int j=0; j<x; j++){
                if(values[i][j]==0){
                    int result =findBFS(i, j);
                    //System.out.printf("y : %d x : %d result : %d\n", i, j, result);
                    if(result > answer){
                        answer = result;
                    }
                }
            }
        }

        System.out.println(answer);

    }
}
