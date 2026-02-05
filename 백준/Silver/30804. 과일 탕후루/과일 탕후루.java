import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int[] values;

    public static HashMap<Integer, Integer> hashmap = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();


        values = new int[N+1];

        for(int i=0; i<N; i++){
            int value = sc.nextInt();
            values[i] = value;
        }


        int start = 0;
        int finish = 0;
        int answer = 0;
        //이거 어떤값하늑네 더 편한지 끝나고 공부
        while(start < N){
            //finish가 추가 가능한 경우
            int finish_value = values[finish];
            int start_value = values[start];

            if(finish == N && hashmap.size() <=2){
                break;
            }


            //기본적으로 finish가 n이하에 위치해야함. 
            // 2개 이하거나 ||  2개임에도 이미 안에 존재하는 값이면 추가하고 finish 추가해도 돼.
            if(finish<N && ( hashmap.size() <2 || (hashmap.size()==2 &&  hashmap.containsKey(finish_value) )) ) {
                int how_many = 0;
                if(hashmap.containsKey(finish_value)){
                    how_many = hashmap.get(finish_value);
                }
                hashmap.put(finish_value, how_many+1);
                
                finish++;

                int can_answer = 0;


                for(int key : hashmap.keySet()){
                    can_answer += hashmap.get(key);
                }
                if(hashmap.size() <= 2){
                    answer = Math.max(answer, can_answer);
                }
                else{
                    answer = Math.max(answer, can_answer -1);
                }
            } 
                //이제 더이상 finish로 안돼. finish 추가하려면 3개가 되버린다구
                //그럼 start를 늘려서 1개 종류로 만들어야지
            else{
                int start_count = hashmap.get(start_value);

                if(start_count ==1){
                    hashmap.remove(start_value);
                    
                }
                else{
                    hashmap.put(start_value, start_count -1);
                }

                
                start++;
                
            }
        }
        System.out.println(answer);
    }
}