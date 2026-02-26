import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {


    public static boolean check(int where, int one_length, String str){
        for(int i=0; i<5; i++){
            if(str.charAt(where + one_length * i) == '#'){
                return true;
            }
        }
        return false;
    }

    public static boolean check_one(int where, int one_length, String str){
        //내앞뒤가불가한경우
        if(where==0 && where==one_length-1){
            return true;
        }
        
        //where가 0인경우
        if(where==0){
            //내 앞에가 불가하다면
            if(!check(where+1, one_length, str)){
                return true;
            }
        } else if(where == one_length-1){
            if(!check(where-1, one_length, str)){
                return true;
            }
        }
        else{
            //앞뒤가 둘다 불가여야지

            if(check(where, one_length, str) && !(check(where-1, one_length, str)) && !(check(where+1, one_length, str))   ){
                return true;
            }
        }

        return false;
        
    }

    public static boolean check_one_line(int where, String str){
        for(int i=0; i<3; i++){
            if(str.charAt(where + i) == '.'){
                return false;
            }
        }

        return true;
    }

        public static boolean check_two_line(int where, String str){
        for(int i=0; i<3; i++){
            if(str.charAt(where + i) == '.'){
                return false;
            }
        }

        return true;
    }
        public static boolean check_three_line(int where, String str){
        for(int i=0; i<3; i++){
            if(str.charAt(where + i) == '.'){
                return false;
            }
        }

        return true;
    }
        public static boolean check_four_line(int where, String str){
        for(int i=0; i<3; i++){
            if(str.charAt(where + i) == '.'){
                return false;
            }
        }

        return true;
    }
        public static boolean check_five_line(int where, String str){
        for(int i=0; i<3; i++){
            if(str.charAt(where + i) == '.'){
                return false;
            }
        }

        return true;
    }

    //2재줄, 4째줄
        public static int check_eight_or_nine(int i, int one_length,String str){
        if(str.charAt(i + one_length) == '#' && str.charAt(i + one_length + 1) == '.' && str.charAt(i + one_length+ 2) == '#' ){

            if(str.charAt(i + one_length*3) == '#'){
                return 8;
            }else{
                return 9;
            }
            
        }

        return -1;
    }

        public static int check_two_or_three(int i, int one_length,String str){
        if(str.charAt(i + one_length) == '.' && str.charAt(i + one_length + 1) == '.' && str.charAt(i + one_length+ 2) == '#' ){

            if(str.charAt(i + one_length*3) == '#'){
                return 2;
            }else{
                return 3;
            }
            
        }

        return -1;
    }

        public static int check_five_or_six(int i, int one_length,String str){
        if(str.charAt(i + one_length) == '#' && str.charAt(i + one_length + 1) == '.' && str.charAt(i + one_length+ 2) == '.' ){

            if(str.charAt(i + one_length*3) == '#'){
                return 6;
            }else{
                return 5;
            }
            
        }

        return -1;
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();

        int one_length = n / 5;

        String answer = "";
        int i=0;
        while(i < one_length){
            if(!check(i, one_length, str)){
                i++;
                continue;
            }

            //앞뒤가 false이고 본인은 가능이면 1이다 정답이.
            if(check_one(i, one_length, str)){
                //1을 추가하자.
                answer += 1;

                i++;
                continue;
                
            }

            //0인지 확인 성 실실실 성 
            if(check_one_line(i, str) && !check_two_line(i + one_length, str) && !check_three_line(i + 2*one_length, str) && 
              !check_four_line(i + 3*one_length, str) && check_five_line(i + 4*one_length, str)){

                answer += 0;

                i+=3;
                continue;
              }

            //4인지 확인 실실성실실
            if(!check_one_line(i, str) && !check_two_line(i + one_length, str) && check_three_line(i + 2*one_length, str) && 
              !check_four_line(i + 3*one_length, str) && !check_five_line(i + 4*one_length, str)){

                answer += 4;

                i+=3;
                continue;
              }

             //7인지 확인 성실실실실
            if(check_one_line(i, str) && !check_two_line(i + one_length, str) && !check_three_line(i + 2*one_length, str) && 
              !check_four_line(i + 3*one_length, str) && !check_five_line(i + 4*one_length, str)){

                answer += 7;

                i+=3;
                continue;
              }            

            //8인지 9인지 확인
            if(check_eight_or_nine(i, one_length, str) == 8 ||check_eight_or_nine(i, one_length, str) == 9 ){
                int value = check_eight_or_nine(i, one_length, str);
            

                answer += value;

                i+=3;
                continue;
        }

            //2 or 3
            if(check_two_or_three(i, one_length, str) == 2 ||check_two_or_three(i, one_length, str) == 3 ){
                int value = check_two_or_three(i, one_length, str);
            

                answer += value;

                i+=3;
                continue;
        }


            if(check_five_or_six(i, one_length, str) == 5 ||check_five_or_six(i, one_length, str) == 6 ){
                int value = check_five_or_six(i, one_length, str);
            

                answer += value;

                i+=3;
                continue;
        }

            
        }
        

        
        System.out.println(answer);
    }
}