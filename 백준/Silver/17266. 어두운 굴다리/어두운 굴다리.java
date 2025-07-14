import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int line = sc.nextInt();
        int how_many = sc.nextInt();


        Queue<Integer> queue = new LinkedList<>();
        
        for(int i=0; i<how_many; i++){
            queue.add(sc.nextInt());
        }

        int start = 0;
        int finish;

        int max = 0;
        boolean first = true;
        while(!queue.isEmpty()){
            finish = queue.poll();
            if(first){
                max = Math.max(max, finish - start);
                first = false;
            }
            else{             
                max = Math.max(max, (finish - start + 1) / 2);
   
            }
            //다끝나면
            start = finish;
        }
        max = Math.max(max, line - start);
        
        System.out.println(max);

        
    }
}