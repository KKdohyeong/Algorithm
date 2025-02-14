
import java.util.*;

public class Main {

    public static String calculate_line;
    public static int find_false = 0;
    public static int calculate(int start, int finish){
        int sum = 0;
        int calculate_find = 0;
        for(int i=start; i<finish; i++){

            if(calculate_find==0 &&  calculate_line.charAt(i) == ')'){
                find_false = -1;
                return -1;
            }
            else if(calculate_find==0 &&  calculate_line.charAt(i) == ']'){
                find_false=-1;
                return -1;
            }

            if(calculate_find == 0 && calculate_line.charAt(i) == '('){
                int new_finish = -1;
                int find = 1;
                for(int j=i+1; j<finish; j++){
                    if(calculate_line.charAt(j) == ')'){
                        find -=1;
                    }
                    else if (calculate_line.charAt(j) == '('){
                        find +=1;
                    }

                    if(find ==0){
                        new_finish = j;
                        break;
                    }
                    //System.out.printf("index %d에서 find값은 %d\n", j, find);
                }

                //어디서 끝나고 뭐하는지 찾음

                if(new_finish == -1){
                    find_false = -1;
                    return -1;
                }
                //System.out.printf("시작 : %d   끝 : %d 그리고 () \n", i, new_finish+1);
                if(new_finish-i+1 == 2){
                    sum += 2;
                }
                else{
                    sum += 2*calculate(i+1, new_finish);
                }
                i = new_finish;
            }
            else if(calculate_find == 0 && calculate_line.charAt(i) == '['){
                int new_finish = -1;
                int find = 1;
                for(int j=i+1; j<finish; j++){
                    if(calculate_line.charAt(j) == ']'){
                        find -=1;
                    }
                    else if (calculate_line.charAt(j) == '['){
                        find +=1;
                    }

                    if(find ==0){
                        new_finish = j;
                        break;
                    }
                }

                //어디서 끝나고 뭐하는지 찾음

                if(new_finish == -1){
                    find_false = -1;
                    return -1;
                }
                //System.out.printf("시작 : %d   끝 : %d 그리고 [] \n", i, new_finish+1);
                if(new_finish-i+1==2){
                    sum+=3;
                }
                else{
                    sum += 3*calculate(i+1, new_finish);
                }
                i = new_finish;
            }

        }

        //System.out.printf("sum is %d\n", sum);

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc=  new Scanner(System.in);
        calculate_line=sc.nextLine();
        int finish= calculate_line.length();
        int result = calculate(0, finish);
        if(find_false==-1){
            System.out.print(0);
        }
        else{
            System.out.println(result);
        }
    }
}
