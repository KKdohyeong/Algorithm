import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static int N;
    public static int least;


    public static int[] trees;

    public static void calculate_least(){
        least = 1;
        while(least<2000001){
            least = least * 2;
        }
    }


    public static void update_plus(int start){
        trees[start]++;
        while(start>0){
            trees[start/2]++;
            start = start/2;
        }
    }

    //이거 하기 전에 본인 마이너스 하고오세요
    public static void update_minus(int start){
        trees[start]--;
        while(start>0){
            trees[start/2]--;
            start = start/2;
        }
    }


    //자 아래에서 small_count만큼인것을 찾는겁니다.
    public static int find(int small_count){
        int node = 1;

        //리프 노드 가면 멈춰야지예
        while(node < least){
            int left_sum = trees[node*2];

            if(left_sum >= small_count){
                node = node*2;
            }
            else{
                small_count = small_count - left_sum;
                node = node*2 + 1;
            }
            
            //왼쪽 이동의 의미 : 왼쪽 숫자가 지금 small_count보다 크거나 같으면 우리 쪽에서 감당 가능하니까 넘어오라 하는 것

            //오른 쪽 이동의 의미  : 왼쪽 숫자가 넘 작아서 감당이 안되니까 오른쪽에서 찾아야지. 그럼 small_count - 왼족 하고 오른쪽 가야할듯
        }

        return node;
    }


    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        
        N = Integer.parseInt(stk.nextToken());

        calculate_least();
        trees = new int[4*2000001];

        Arrays.fill(trees, 0);
        
        for(int i=0; i<N; i++){
             stk = new StringTokenizer(br.readLine());

            int which =  Integer.parseInt(stk.nextToken());

            //1은 index 0에 있음.
            if(which ==1){
                 int value = Integer.parseInt(stk.nextToken());
                update_plus(value-1 + least );
            }
            else{
                //2라면 2번째로 작은 수를 말하고 삭제해야함
                int back_prize = Integer.parseInt(stk.nextToken());
                int find_tree_index = find(back_prize);
                update_minus(find_tree_index);
                sb.append(find_tree_index-least+1);
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
        

    }
}