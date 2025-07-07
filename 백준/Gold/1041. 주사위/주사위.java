import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static int oneMax = -1;
    public static long oneMin = Integer.MAX_VALUE;
    public static long twoMin = Integer.MAX_VALUE;
    public static long threeMin = Integer.MAX_VALUE;

    public static int[] out;
    
    
    public static void gettwoMin(int[] dice, int[] visited, int depth, int value){
        //System.out.printf("%d번째, 값 : %d\n", depth, value);
        if(depth == 2){
            
            if(value < twoMin){
                twoMin = value;
            }
            return;
        }
        for(int i=0; i<6; i++){
            //i번째 주사위 부위 선택
            if(visited[i] == 0){
            //i번째랑 i번째 건너편 불가
            visited[i] = 1;
            visited[out[i]] = 1;                
            gettwoMin(dice,visited ,depth+1, value + dice[i]);
            visited[i] = 0;
            visited[out[i]] = 0;
            }
        }
    }


    public static void getthreeMin(int[] dice, int[] visited, int depth, int value){
        //System.out.printf("%d번째, 값 : %d\n", depth, value);
        if(depth == 3){
            
            if(value < threeMin){
                threeMin = value;
            }
            return;
        }
        for(int i=0; i<6; i++){
            //i번째 주사위 부위 선택
            if(visited[i] == 0){
            //i번째랑 i번째 건너편 불가
            visited[i] = 1;
            visited[out[i]] = 1;                
            getthreeMin(dice,visited ,depth+1, value + dice[i]);
            visited[i] = 0;
            visited[out[i]] = 0;
            }
        }
    }

    public static long solution(long n){
        long answer = 0;
        //1개짜리 더하자
        answer +=  ((n-2)*(n-2) + 4*(n-1)*(n-2)) * oneMin;
        //System.out.println(answer);
        //2개짜리 더하자
        answer += (4*(n-1) + 4*(n-2)) * twoMin;
        //System.out.println(answer);

        //3개짜리 더하자
        answer += 4 * threeMin;
        //System.out.println(answer);

        return answer;
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        out = new int[6];
        int[] visited = new int[6];
        int[] visited2 = new int[6];
        out[0] = 5;
        out[5] = 0;
        out[1] = 4;
        out[4] = 1;
        out[2] = 3;
        out[3] = 2;
        
        int dice_number = sc.nextInt();

        int[] dice = new int[6];
        int total_temp = 0;
        for(int i=0; i<6; i++){
            dice[i] = sc.nextInt();
            if(dice[i] < oneMin){
                oneMin = dice[i];
            }
            oneMax = Math.max(oneMax, dice[i]);
            
            total_temp += dice[i];
            visited[i] = 0;
            visited2[i] = 0;
        }
        gettwoMin(dice, visited, 0, 0);
        getthreeMin(dice, visited2, 0, 0);
        //System.out.println(oneMin);
        //System.out.println(twoMin);
        //System.out.println(threeMin);

        long answer = 0;
        if(dice_number>=2){
            answer = solution(dice_number);
        }
        else if(dice_number==1){
            answer = total_temp - oneMax;
        }
        
        System.out.println(answer);
        
    }
}