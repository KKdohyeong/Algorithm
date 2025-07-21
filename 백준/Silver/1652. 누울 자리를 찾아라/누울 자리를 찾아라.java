import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static int[][] array;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        array = new int[n+2][n+2];
        int[][] visited = new int[n+2][n+2];
        int[][] visited_two = new int[n+2][n+2];
        for(int i=0; i<n+2; i++){
            for(int j=0; j<n+2; j++){
                array[i][j] = 1;
                visited[i][j] = 0;
                visited_two[i][j] = 0;
            }
        }

        
        for(int i=1; i<=n; i++){
            String str = sc.next();
            for(int j=1; j<=n; j++){
                if(str.charAt(j-1) == '.'){
                    array[i][j] = 0;
                }
                else{
                    array[i][j] = 1;
                }
            }
        }

/*
        for(int i=0; i<n+2; i++){
            
            for(int j=0; j<n+2; j++){
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
*/

        //가로로 먼저 -> row가이동
        int first = 0;
        for(int i=0; i<n+2; i++){   
            for(int j=0; j<n+2; j++){
                if(array[i][j] == 0 && visited[i][j] ==0){
                    int count = 0;
                    while(true){
                        if(array[i][j] ==1 || visited[i][j] == 1){
                            break;
                        }
                        //System.out.printf("%d,%d를 색칠 값은 %d\n", i, j, first);
                        visited[i][j] = 1;
                        j++;
                        count++;
                    }
                    if(count >=2){
                        first++;
                    }
                }
            }
        }
        System.out.print(first);

        int second = 0;
        for(int j=0; j<n+2; j++){   
            for(int i=0; i<n+2; i++){
                if(array[i][j] == 0 && visited_two[i][j] ==0){
                    int count = 0;
                    while(true){
                        if(array[i][j] ==1 || visited_two[i][j] == 1){
                            break;
                        }
                        visited_two[i][j] = 1;
                        i++;
                        count++;
                    }
                    if(count >=2){
                        second++;
                    }
                }
            }
        }
        System.out.printf(" %d", second);
        
        
    }
}