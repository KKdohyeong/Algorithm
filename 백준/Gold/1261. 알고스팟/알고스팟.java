import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    // 자료구조가 node번호, 시작점에서 최소거리 즉 걍 int[]면 될듯

    //동서남북
    public static int[] dr = {0, 0, 1, -1};
    public static int[] dc = {1, -1, 0, 0};
    // 주변 애들을 정리해야하는데 이건 위아래옆다가능하니 빼자?
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] array = new int[n][m];
        int[][] daik = new int[n][m];
        int[][] visited = new int[n][m];
        for(int i=0; i<n; i++){
            String str = sc.next();
            for(int j=0; j<m; j++){
                array[i][j] = str.charAt(j) - '0';
                daik[i][j] = Integer.MAX_VALUE;
                visited[i][j] = 0;
            }
        }

        daik[0][0] = 0;

        while(true){
            //serach해서 가장 당첨된 노드 찾기
            int row = -1;
            int col = -1;
            int min = Integer.MAX_VALUE;
            for(int i=0;i <n; i++){
                for(int j=0; j<m; j++){
                    if(visited[i][j] ==0 && min > daik[i][j]){
                        min = daik[i][j];
                        row = i;
                        col = j;
                    }
                }
            }

            //더이상 업데이트 없으면 끝
            if(min == Integer.MAX_VALUE){
                break;
            }
            //이제 거기서 시작해야지 
            visited[row][col] = 1;

            for(int i=0; i<4; i++){
                int next_row = row + dr[i];
                int next_col = col + dc[i];
                
                if(next_row>=0 && next_col >=0 && next_row < n && next_col < m){
                    daik[next_row][next_col] = Math.min(daik[next_row][next_col], daik[row][col] + array[next_row][next_col]);
                }
            }
        
        }
        System.out.println(daik[n-1][m-1]);
        
    }
}