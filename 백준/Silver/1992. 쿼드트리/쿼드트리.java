import java.util.*;



public class Main
{

    static int[][] values;

    public static String tracking(int start_x, int start_y, int finish_x, int finish_y){
        int first_start_x = start_x;
        int first_finish_x = (start_x+finish_x)/2;
        int second_start_x = first_finish_x+1;
        int second_finish_x = finish_x;

        int first_start_y = start_y;
        int first_finish_y = (start_y + finish_y)/2;
        int second_start_y = first_finish_y +1;
        int second_finish_y = finish_y;


        String sum = "";

        int fail_flag = 0;

        int value = values[start_y][start_x];
        for(int i=start_y; i<=finish_y; i++){
            for(int j=start_x; j<=finish_x; j++){
                //System.out.printf("value is %d and  values[%d][%d] is %d\n", value, i, j, values[i][j]);
                if(value != values[i][j]){
                    fail_flag = 1;
                }
            }
        }

        if(fail_flag==0){
            if(values[start_y][start_x]==0){
                sum = "0";
            }
            if(values[start_y][start_x]==1){
                sum = "1";
            }
        }
        else{
            sum += "(";
            //왼쪽 위
            sum += tracking(first_start_x, first_start_y, first_finish_x, first_finish_y);
            //System.out.println("1  : "  + sum);
            //오른쪽 위
            sum+= tracking(second_start_x, first_start_y, second_finish_x, first_finish_y);
            //System.out.println("2  : "  + sum);
            //왼쪽 아래
            sum+= tracking(first_start_x, second_start_y, first_finish_x, second_finish_y);
            //System.out.println("3  : "  + sum);//오른쪽 아래
            sum += tracking(second_start_x, second_start_y, second_finish_x, second_finish_y);
            //System.out.println("4  : "  + sum);
            sum+= ")";
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        values = new int[size][size];
        sc.nextLine();
        for(int i=0; i<size; i++){
            String temp = sc.nextLine();
            for(int j=0; j<size; j++){
                int a = temp.charAt(j) - '0';
                values[i][j] = a;
            }
        }

        String sum = "";
        sum += tracking(0, 0, size-1, size-1);

        if(sum.length()==1){
            sum = sum;
        }

        System.out.println(sum);




    }
}
