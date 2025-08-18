import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int N;

    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int result = 0;
        for(int i=0; i<=n; i++){
            for(int j=0; j<=i; j++){
                result += i;
                result += j;
            }
        }
        System.out.println(result);
    }
}