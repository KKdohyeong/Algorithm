
import java.util.*;

public class Main {

    public static int[][] visited;
    public static int sum = 0;
    public static int size;

    public static void tracking(int depth){
        if(depth==size){
            sum++;
            return;
        }

        for(int i=0; i<size; i++){
            if(visited[depth][i] == 0){
                //추가
                int width = 0;
                for(int start= depth+1; start<size; start++){
                    width++;

                    if(i-width>=0){
                        visited[start][i-width]+=1;
                    }

                    visited[start][i] += 1;

                    if(i+width<size){
                        visited[start][i+width]+=1;
                    }


                }
                //보내기
                tracking(depth+1);
                //없애기
                width = 0;
                for(int start= depth+1; start<size; start++){
                    width++;

                    if(i-width>=0){
                        visited[start][i-width]-=1;
                    }

                    visited[start][i] -= 1;

                    if(i+width<size){
                        visited[start][i+width]-=1;
                    }
                }

            }
        }

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();

        visited = new int[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                visited[i][j] = 0;
            }
        }
        tracking(0);
        System.out.print(sum);

    }


}