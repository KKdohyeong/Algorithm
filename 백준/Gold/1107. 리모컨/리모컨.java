import java.util.*;
import java.io.*;

class Main {
     // 500,000번 이상으로 넘어갔다가 -로 내려오는 경우를 위해 넉넉하게 잡아야 함
    public static int max = 1000000;
    public static int target;
    public static int answer;
    public static boolean[] broken = new boolean[10];

    public static void traverse(int depth, int value){
        if(depth >= 7){ 
            return;
        }

        if(depth == 0){
            for(int i=0; i<=9; i++){
                if(!broken[i]){
                    traverse(1, i);                    
                }
            }
        }
        else{
            int clicks = Math.abs(value - target) + depth;
            if(answer > clicks){
                answer = clicks;
            }

            int add_value = 1;
            for(int i=0; i<depth; i++){
                add_value *= 10;
            }

            for(int i=0; i<=9; i++){
                if(!broken[i]){
                    // 기존: value + add_value
                    // 수정: value + (i * add_value) -> 예: 5 + (4 * 10) = 45
                    int new_value = value + (i * add_value);
                    traverse(depth + 1, new_value);
                }
            }
        }
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        target = sc.nextInt();

   
        answer = Math.abs(target - 100);

        int how_many = sc.nextInt();

        for(int i=0; i<how_many; i++){
            int no = sc.nextInt();
            broken[no] = true; 
        }

        traverse(0, 0); 

        System.out.println(answer);
    }
}