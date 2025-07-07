import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int total_tree_number = sc.nextInt();
        int cut_cost = sc.nextInt();
        int one_tree_cost = sc.nextInt();
        LinkedList<Integer> list = new LinkedList<>();
        
        for(int i=0; i<total_tree_number; i++){
            int tree = sc.nextInt();
            list.add(tree);
        }

        Collections.sort(list, Collections.reverseOrder());
        int max = list.get(0);
        //System.out.println(max);

        long result = -1;
        for(int i=1; i<=max; i++){
            long total_money = 0;
            // 나무를 가지고 이제 i로 짜르는거야.
            for(Integer tree : list){
                //즉 4를 1로 나누면 4가 나오는데 이는 
                int cut_count = tree / i;
                int total_cutted_number = tree/i;
                int nanugi_check = tree%i;
                
                //깔끔하게 나누어지면  n번 짜르는게 아니라 n-1번 짜르는거임.
                if(nanugi_check == 0 && tree/i >=1){
                    cut_count--;
                }
                int here_money = total_cutted_number * one_tree_cost * i - cut_cost*cut_count;
                if(here_money >0){
                    total_money += here_money;
                }
            }

            //이게 끝나면 몇 번 짜르고 몇 개 나무가 생겻는지 나옴
            //System.out.println(total_money);
            result = Math.max(result, total_money);
        }
        System.out.print(result);

        
    
    }
}