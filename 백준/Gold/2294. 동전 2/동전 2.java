import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] dp = new int[200010];
        int[] okay = new int[200010];
        LinkedList<Integer> my_list = new LinkedList<>();

        //중복 제거 완료
        for(int i=0; i<n; i++){
            my_list.add(sc.nextInt());    
        }
        Set<Integer> tempset = new HashSet<>(my_list);
        List<Integer> list = new ArrayList<>(tempset);

        
        for(int i=0; i<target+1; i++){
            dp[i] = Integer.MAX_VALUE;
            okay[i] = 0;
        }


        for(int i=1; i<target+1; i++){
            //갱신 되었거나 시작하는 값이면 시작해야지
            if(list.contains(i)){
                dp[i] = Math.min(dp[i], 1);
                for(int plus : list){
                    okay[i+plus] = 1;
                    dp[i+plus] = Math.min(dp[i+plus], dp[i] + 1);
                }
            }
            else if(okay[i]==1){
                for(int plus : list){
                    okay[i+plus] = 1;
                    dp[i+plus] = Math.min(dp[i+plus], dp[i] + 1);
                }
            }
            
        }

        if(dp[target]==Integer.MAX_VALUE){
            System.out.println("-1");
        }
        else{
            System.out.println(dp[target]);
        }

        
        
    }
}