
import java.util.*;

public class Main {

    public static int[][] visited;
    public static int[][] answer;
    public static int[][] value;
    public static int sum = 0;
    public static int total_height;
    public static int total_width;

    public static void bfs(){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        visited[0][0] = 1;

        while(!queue.isEmpty()){
            int size = queue.size();

            for(int i=0; i<size; i++){
                int[] visit = queue.poll();
                int height = visit[0];
                int width = visit[1];

                // 다르면 3x3 뒤집기
                if(value[height][width] != answer[height][width]){
                    sum++;
                    for(int j=0; j<3; j++){
                        for(int k=0; k<3; k++){
                            if(height+j >= total_height || width+k >= total_width){
                                sum=-1;
                                return;
                            }
                            if(value[height+j][width+k]==1){
                                value[height+j][width+k]=0;
                            } else{
                                value[height+j][width+k]=1;
                            }
                        }
                    }
                }

                // 다음 칸으로 이동
                if(height+1 < total_height && visited[height+1][width] == 0){
                    queue.add(new int[]{height+1, width});
                    visited[height+1][width] = 1;
                }

                if(width+1 < total_width && visited[height][width+1] == 0){
                    queue.add(new int[]{height, width+1});
                    visited[height][width+1] = 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 첫 줄에서 높이, 너비 읽기
        total_height = sc.nextInt();
        total_width = sc.nextInt();

        // 배열 초기화
        visited = new int[total_height][total_width];
        value = new int[total_height][total_width];
        answer = new int[total_height][total_width];

        // value 입력 (줄 단위로 읽어서 각각 분해)
        for(int i=0; i<total_height; i++){
            String line = sc.next();  // 예: "0000"
            for(int j=0; j<total_width; j++){
                visited[i][j] = 0;         // 방문배열 초기화
                value[i][j] = line.charAt(j) - '0';
            }
        }

        // answer 입력 (줄 단위로 읽어서 각각 분해)
        for(int i=0; i<total_height; i++){
            String line = sc.next();  // 예: "1001"
            for(int j=0; j<total_width; j++){
                answer[i][j] = line.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(sum);
    }
}
