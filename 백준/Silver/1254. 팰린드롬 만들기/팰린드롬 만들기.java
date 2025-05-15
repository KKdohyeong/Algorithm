import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static boolean check_pelin(String a){
        StringBuilder sb = new StringBuilder(a);
        String a_reverse = sb.reverse().toString();

        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) != a_reverse.charAt(i)){
                return false;
            }
        }

        return true;
        
    }

    public static int result(String str){
        int length = str.length();
        int plus_str = 0;
        for(int i=0; i<length; i++){
            if(check_pelin(str)==true){
                return length+plus_str;
            }
            else{
                str = str.substring(1);
                plus_str++;
            }
        }
        return 0;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str = bf.readLine();
        System.out.print(result(str));
    }
}