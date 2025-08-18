import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".



class Main {


    public static int max=  0;

    public static void dfs(int[] visited, int depth, Stack<Integer> stack){
        if(depth == N){
            //System.out.println("시작!\n");
            int here_max = 0;
            //계산하고 끝내자
            int start = -110;
            int before = -110;


            Stack<Integer> temp = new Stack<>();
            while(!stack.isEmpty()){
                start = stack.pop();

                //System.out.printf("   %d값을 뽑음   ", start);
                temp.add(start);
                if(before ==-110){
                    before = start;
                    continue;
                }

                //System.out.printf("%d - %d값을 추가합니다 \n", start, before);
                here_max += Math.abs(start-before);
                before = start;

                
            }

            max = Math.max(max, here_max);
            //System.out.printf("결과는 %d\n", max);

            while(!temp.isEmpty()){
                int value = temp.pop();
                stack.push(value);
                //System.out.printf("%d값을 다시 추가함   ", value);
            }
            return;
        }


        for(int i=0; i<N; i++){
            if(visited[i] == 0){
                stack.push(arrays[i]);
                visited[i] = 1;

                dfs(visited, depth+1, stack);

                stack.pop();
                visited[i] = 0;
            }
        }

        
    }
    
    public static int N;

    public static int[] arrays;
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();


        arrays = new int[N];
        int[] visited = new int[N];
        Arrays.fill(visited, 0);

        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<N; i++){
            int value = sc.nextInt();
            arrays[i] = value;
        }

        dfs(visited, 0, stack);

        System.out.println(max);
        
        
    }
}