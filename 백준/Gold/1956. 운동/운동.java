import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {


    public static int[][] array;
    public static int V;
    public static int E;
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        array = new int[V][V];
       
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                array[i][j] = Integer.MAX_VALUE;
                if(i==j){
                    array[i][j] = 0;
                }
            }
        }

        
        for(int i=0; i<E; i++){
            int start = sc.nextInt()-1;
            int finish = sc.nextInt()-1;
            int value = sc.nextInt();
            array[start][finish] = value;
        }

        for(int mid = 0; mid<V; mid++){
            for(int start = 0; start<V; start++){
                for(int fin = 0; fin<V; fin++){
                    if(array[start][mid] != Integer.MAX_VALUE && array[mid][fin] != Integer.MAX_VALUE){
                        array[start][fin] = Math.min(array[start][fin],  array[start][mid]+ array[mid][fin]);
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){
                if(array[i][j]!= Integer.MAX_VALUE && array[j][i] != Integer.MAX_VALUE&& i!=j){
                    answer = Math.min(answer, array[i][j] + array[j][i]);
                }
            }
        }  
        if(answer==Integer.MAX_VALUE){
            answer = -1;
        }
        System.out.println(answer);
    
    }
}