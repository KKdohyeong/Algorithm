import java.util.*;
import java.lang.*;
import java.io.*;

/*
두 팀의 능력 최솟값.

10명 10명 나누면돼.

나누면? 

잠은 최고야. 


자바스크립트는 프론트(화면 그냥 뚝 멈춰있는) 동적인 이벤트를 넣는 언어 자바스크립트

프로그램 c언어, 파이선, 자바


*/

// The main method must be in a class named "Main".
class Main {

    public static int n;

    public static int[][] values;
    public static int[] visited;
    public static int min = Integer.MAX_VALUE;

    
    //n이 4라면 2개 선택하면 멈춰야해

    public static void traverse(int depth, int start){

        if(depth ==(n/2)){

            int value_first = 0;
            int value_second = 0;
            
            for(int i=0; i<n; i++){
                
                if(visited[i] == 1){
                    for(int j=n-1; j>i; j--){
                        if(visited[j] == 1){
                             
                            value_first += values[i][j];  
                            value_first += values[j][i];   
                        }
                    }   
                }
                else{
                    for(int j=n-1; j>i; j--){
                        if(visited[j] == 0){
                             
                            value_second += values[i][j];
                            value_second += values[j][i];   
                        }
                    }
                }
            }
            min = Math.min(min, Math.abs(value_first - value_second));
            return;
        }


        for(int i=start; i<n; i++){

            //선택하자
            if(visited[i] ==0){

                visited[i] = 1;

                traverse(depth+1, i+1);

                visited[i] = 0;
            }
            
        }
        
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        values = new int[n][n];
        visited = new int[n];
        Arrays.fill(visited, 0);

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int value = sc.nextInt();
                values[i][j]= value;
            }
        }

        traverse(0, 0);

        System.out.println(min);
    }
}