import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] array = new int[n];
        for(int i=0; i<n; i++){
            array[i] = sc.nextInt();
        }
        int main = sc.nextInt();
        int sub = sc.nextInt();

        long result = 0;

        for(int i=0; i<n; i++){
            array[i] -= main;
            result++;

            //음수나 0이면 통과해야지

            if(array[i] <=0){
                continue;
            }
            //이거면 깔끔하게 더하면 돼.
            if(array[i] % sub == 0){
                result += array[i]/sub;
            }
            else{
                result+=array[i]/sub;
                result++;
            }
        }
        System.out.println(result);
    }
}