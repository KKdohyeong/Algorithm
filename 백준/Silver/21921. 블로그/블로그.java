import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static int X;
    public static int N;
    public static int[] arrays;
    public static HashMap<Integer, Integer> hashmap = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        X = sc.nextInt();

        arrays = new int[N+1];
        
        for(int i=0; i<N; i++){
            int value = sc.nextInt();
            arrays[i] = value;
        }


        // 2개면

        //초기세팅
        int max = 0;
        for(int i=0; i<X; i++){
            max += arrays[i];
        }
        hashmap.put(max, 1);
        int temp = max;

        //시작 3
        for(int start = 0; start<N-X; start++){
            int finish = start + X ;

            //start 빼버려
            temp = temp - arrays[start];    
            //finish 더해버려
            temp = temp + arrays[finish];

            //이제 확인해봐

            //키 있는값이면 +1하면되자낭
            if(hashmap.containsKey(temp)){
                int value = hashmap.get(temp);

                hashmap.put(temp, value+1);
            }
            else{
                hashmap.put(temp, 1);
            }

            //max값설정해야지
            max = Math.max(max, temp);
        }

        if(max ==0){
            System.out.println("SAD");
        }
        else{
            System.out.println(max);
            System.out.println(hashmap.get(max));
        }
    }
}