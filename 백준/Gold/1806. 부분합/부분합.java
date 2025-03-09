
import java.util.*;
/*


 */

public class Main {



    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int min_sum = sc.nextInt();
        int[] values = new int[size];

        for(int i=0; i<size; i++){
            values[i] = sc.nextInt();
        }


        int pointer_b =0;
        long sum = 0;
        int min_size = Integer.MAX_VALUE;
        sum+=values[0];
        for(int pointer_a = 0; pointer_a<size; pointer_a++){

            //한 칸 이동했으니 값 하나 빼야지
            if(pointer_a>=1){
                sum-=values[pointer_a-1];
            }

            //값이 작고 오른쪽으로 이동 가능하면 하나 이동
            while(sum<min_sum && pointer_b+1 < size){
                pointer_b++;
                sum+=values[pointer_b];
                //System.out.printf("%d ~ %d : %d\n", pointer_a, pointer_b, sum);
            }

            //여기는 이제 성공한거겠지
            if(sum>=min_sum){
                int len = pointer_b -pointer_a + 1;
                if(len < min_size){
                    min_size = len;
                }
                //System.out.printf("%d ~ %d까지 합이 %d라서 %d넘음\n", pointer_a, pointer_b, sum, min_sum);
            }

        }
        if(min_size!=Integer.MAX_VALUE){
            System.out.println(min_size);
        }
        else{
            System.out.println(0);
        }



    }


}
