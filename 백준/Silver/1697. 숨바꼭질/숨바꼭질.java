import java.util.*;




public class Main {

    public static int[] dp;

    public static void find(int start, int finish){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        dp[start] = 0;


        int depth = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int value = queue.poll();
                /*if(value == finish){
                    return ;
                }
                */
                if(value+1 >= 0 && value+1 <=100000 && dp[value+1] == -1 ){
                    dp[value+1] = depth;
                    queue.add(value+1);
                }

                if(value-1 >= 0 && value-1 <=100000 && dp[value-1] == -1){
                    dp[value-1] = depth;
                    queue.add(value-1);
                }

                if(value*2 >= 0 && value*2 <=100000 && dp[value*2] ==-1 ){
                    dp[value*2] = depth;
                    queue.add(value*2);
                }
            }
            depth++;
        }


    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int finish = sc.nextInt();

        dp = new int[100001];
        for(int i=0; i<100001; i++){
            dp[i] = -1;
        }

        find(start, finish);


        int min,max;
        if(start>finish){
            max= start;
            min= finish;
        }
        else{
            max= finish;
            min= start;
        }
        for(int i=min; i<=max; i++){
            //System.out.printf("%d의 도달 값은 %d이다.\n", i, dp[i]);
        }

        int answer = dp[finish];
        System.out.println(answer);

    }


}