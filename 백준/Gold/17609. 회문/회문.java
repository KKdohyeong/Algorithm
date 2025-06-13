import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int how_many = sc.nextInt();
        for(int i=0; i<how_many; i++){
            String str = sc.next();
            String left_out = str;
            String right_out = str;
            int start =0;
            int finish = str.length() -1;
            int answer=  0;
            while(start<=finish){
                //앞 뒤
                if(str.charAt(start) == str.charAt(finish)){
                    start++;
                    finish--;
                    continue;
                }
                if(str.charAt(start) != str.charAt(finish)){
                    answer = 2;
                    left_out = new StringBuilder(str).deleteCharAt(start).toString();
                    right_out = new StringBuilder(str).deleteCharAt(finish).toString();
                    break;
                }
                
            }
            if(answer==2){
                start = 0;
                finish = left_out.length()-1;
                while(start<=finish){
                    if(left_out.charAt(start) == left_out.charAt(finish)){
                        start++;
                        finish--;
                        continue;
                    }
                    if(left_out.charAt(start) != left_out.charAt(finish)){
                        break;
                    }
                }
                if(start>finish){
                    answer = 1;
                }
                start = 0;
                finish = right_out.length()-1;
                while(start<=finish){
                    if(right_out.charAt(start) == right_out.charAt(finish)){
                        start++;
                        finish--;
                        continue;
                    }
                    if(right_out.charAt(start) != right_out.charAt(finish)){
                        break;
                    }
                }
                if(start>finish){
                    answer=1;
                }
                
                
            }
            
        System.out.println(answer);
            
        }
    }
}