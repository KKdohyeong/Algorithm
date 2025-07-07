import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {


    public static int getL(String s){
        int count =0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'L'){
                count++;
            }
        }
        return count;
    }
    public static int getO(String s){
        int count =0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'O'){
                count++;
            }
        }
        return count;
    }
    public static int getV(String s){
        int count =0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'V'){
                count++;
            }
        }
        return count;
    }
    public static int getE(String s){
        int count =0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'E'){
                count++;
            }
        }
        return count;
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        int count = sc.nextInt();
        LinkedList<String> list = new LinkedList<>();
        int L = getL(name);
        int O = getO(name);
        int V = getV(name);
        int E = getE(name);
        int answer = -1;
        String answer_str;
        for(int i=0; i<count; i++){
            String str = sc.next();
            int L1 = L + getL(str);
            int O1 = O + getO(str);
            int V1 = V + getV(str);
            int E1 = E + getE(str);
            int temp = ((L1+O1) * (L1 + V1) * (L1 + E1) * (O1 + V1) * (O1 + E1) * (V1 + E1))%100;
            //System.out.println(temp);
            if(temp > answer){
                answer = temp;
                list = new LinkedList<>();
                list.add(str);
            }
            else if (temp == answer){
                temp = answer;
                list.add(str);
            }
        }

        Collections.sort(list);
        answer_str = list.get(0);
        
        System.out.println(answer_str);
        
    }
}