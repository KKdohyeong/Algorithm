import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static boolean check(int i, int j){
        if(i-1 < 0 || j-1 < 0){
            return false;
        }
        return true;
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        String b = sc.next();
        int[][] dp = new int[a.length()][b.length()];

        int max = -1;
        for(int i=0; i<a.length(); i++){
            for(int j=0; j<b.length(); j++){
                //경로 ㄱㅊ으면
                if(check(i, j)){
                    //일치하면
                    if(a.charAt(i) == b.charAt(j)){
                        int two_max = Math.max(dp[i-1][j], dp[i][j-1]);
                        dp[i][j] = Math.max(two_max, dp[i-1][j-1]+1);
                    }
                    //일치 안하면?
                    else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
                //경로 이상하면
                else{
                    //일치 하면
                    if(a.charAt(i) == b.charAt(j)){
                        dp[i][j] = 1;
                    }
                    else if(i-1 < 0){
                        if(j-1 >= 0){
                            dp[i][j] = Math.max(0, dp[i][j-1]);
                        }
                        else{
                            dp[i][j] = 0;
                        }
                    }
                    else if(j-1 < 0){
                        dp[i][j] = Math.max(0, dp[i-1][j]);
                    }
                }

                if(max < dp[i][j]){
                    max = dp[i][j];
                }
            }
            
        }
        System.out.println(max);
    }
}