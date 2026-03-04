import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
/*
10만일동안 1만원
10만, 100만, 1000만, 1억, 10억

1원~10억


*/
class Main {
    public static int N;
    public static int M;
    public static int[] arrays;

    //n일동안 진행하면서 가능한지
    public static boolean check(int K){
        int count = 0;
        int now_money = 0;
        for(int i=0; i<N; i++){
            int need_money = arrays[i];

            //나 돈 없어 그럼 인출해
            if(need_money > now_money){
                now_money = K;
                count++;
            }

            //이제 돈 빼가
            if(need_money > now_money){
                return false;
            }
            now_money = now_money - need_money;
        }

        //이제 인출이 넘 많앗으면 false야
        if(count > M){
            return false;
        }

        return true;
        
    }

    
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        arrays = new int[N];

        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            int value = Integer.parseInt(stk.nextToken());

            arrays[i] = value;
        }
        
        int left = 1;
        int right = 1000000001;

        while(left < right){
            int mid = (left+right)/2;

            // 우리가찾는 조건보다 mid가 크거나같다. 
            if( check(mid) ){
                right = mid;
            }
            else{
                left = mid+1;
            }
            
        }

        //이제 left가 가능한 최소 그거잖아. 없다고는 생각하지말자 일단.
        System.out.println(left);
        
        
    }
}