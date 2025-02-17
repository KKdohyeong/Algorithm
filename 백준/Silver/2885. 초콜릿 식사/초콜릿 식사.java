import java.util.*;



public class Main
{

    static int remain_result;
    static int min_chocolate;
    static int break_count = 0;
    static int[] find_min_chocolate = new int[22];

    public static void tracking(int chocolate_size){
       // System.out.println(remain_result);

        if(remain_result==0){
            return;
        }
        if(chocolate_size==1 && remain_result>0){
            //1짜리 크기 뭐 지우는 행동 해야지! 근데 올리가 없는데.. 흠
            remain_result--;
            return;
        }
        int broken_chocolate_size = chocolate_size/2;
        //초콜릿을 2개로 나누고 그게 빼기가 가능하면 빼고 카운트하자. 근데 2개니까 2번 해야함

        if(remain_result >= broken_chocolate_size){
           // System.out.printf("첫번 째 성공할것, reamin : %d 그리고 빼기 : %d\n", remain_result, broken_chocolate_size);
            remain_result -= broken_chocolate_size;
        }
        else if(remain_result>0){
           // System.out.printf("첫번 째 빼기실패, reamin : %d 그리고 빼기 : %d\n", remain_result, broken_chocolate_size);
            break_count++;
            tracking(broken_chocolate_size);
        }

       // System.out.printf("엥, reamin : %d 그리고 빼기 : %d\n", remain_result, broken_chocolate_size);

        if(remain_result > broken_chocolate_size){
            //System.out.printf("두번 째 성공할것, reamin : %d 그리고 빼기 : %d\n", remain_result, broken_chocolate_size);

            remain_result -= broken_chocolate_size;
        }
        else if (remain_result>0){
            //System.out.printf("두번 째 빼기실패, reamin : %d 그리고 빼기 : %d\n", remain_result, broken_chocolate_size);

            break_count++;
            tracking(broken_chocolate_size);
        }



    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start= 1;
        for(int i=0; i<22; i++){
            find_min_chocolate[i]=start;
            start *=2;
        }

        remain_result = sc.nextInt();


        for(int i=0; i<21; i++){
            if(find_min_chocolate[i] == remain_result){
                min_chocolate = find_min_chocolate[i];
                break;
            }

            if(find_min_chocolate[i] < remain_result &&  remain_result < find_min_chocolate[i+1]){
                min_chocolate = find_min_chocolate[i+1];
                break;
            }
        }

        if(min_chocolate==remain_result){
            System.out.printf("%d %d", min_chocolate, 0);
        }else{
            break_count++;
            tracking(min_chocolate);
            System.out.printf("%d %d", min_chocolate, break_count);

        }



    }
}
