

import java.util.*;

public class Main {
/*
1. quack는 항상 quack순서대로 크기가 같거나 작아져야한다 내 말은

q는 5개 a는 4개 이런 식이어야한다.

5 5 4 4 3 은 괜찮아. 인정? ㅇㅇ

2. quack의 모든 값이 1 이상이 되면 하나를 flush한다.
5 5 4 4 1 되는 순간
4 4 3 3 0 으로 보자
5 4 3 3 0


3 3
3. q의 갯수가 그 당시의 남아있는 오리의 개수이다.

실패 케이스들
qquuaacckkkq

 */



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] quack = new int[5];
        String line = sc.nextLine();

        int max_duck = 0;
        boolean success = true;
        for(int i=0; i<line.length(); i++){
            if(line.charAt(i)=='q'){
                quack[0]++;
            }
            if(line.charAt(i)=='u'){
                quack[1]++;

            }
            if(line.charAt(i)=='a'){
                quack[2]++;

            }
            if(line.charAt(i)=='c'){
                quack[3]++;

            }
            if(line.charAt(i)=='k'){
                quack[4]++;

            }



            if(quack[0] > max_duck){
                max_duck = quack[0];
            }

            //한 마리 다 울면 flush
            boolean duck_finish = true;
            for(int j=0; j<quack.length; j++){
                if(quack[j]<1){
                    duck_finish=false;
                }
            }

            if(duck_finish){
                for(int j=0; j<quack.length; j++){
                    quack[j]--;
                }
            }



            for(int j=0; j<quack.length-1; j++){
                if(quack[j] < quack[j+1]){
                    success=false;
                }
            }

        }


        for(int j=0; j<quack.length; j++){
            if(quack[j]!=0){
                System.out.println("-1");
                return;
            }
        }

        if(success) {
            System.out.println(max_duck);
        }
        else{
            System.out.println("-1");
        }
    }


}