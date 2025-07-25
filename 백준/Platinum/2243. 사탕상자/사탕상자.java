import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static void calculate_least(){
        k = 0;
        int value = 1;

        while(value < 1000000){

            k++;
            value *=2;
        }
        least = value;
        
    }


    //1번 노드는 음....
    public static void set(int node, long how_many){
        //그니까 8번부터 시작이면 8번이 시작을 하게 하고 싶다는 의미인거같다.
        int real_node = node + least - 1;

        //how many만큼 갯수의 변화가 생김
        trees[real_node] += how_many;

        //부모노드를 이동하며 갱신해야함

        while(real_node > 1){
            if(real_node%2==1){
                trees[real_node/2] = trees[real_node] + trees[real_node-1];
            }
            else{
                trees[real_node/2] = trees[real_node] + trees[real_node+1];
            }

            real_node = real_node/2;
        }
        
        
    }

    public static int find(int count) {
        int idx = 1;
    
        // leaf 노드에 도착할 때까지 내려가기
        while (idx < least) {
            if (trees[idx * 2] >= count) {
                // 왼쪽 자식으로 내려감
                idx = idx * 2;
            } else {
                // 오른쪽 자식으로 내려가되, count에서 왼쪽 자식 값 빼줌
                count -= trees[idx * 2];
                idx = idx * 2 + 1;
            }
        }
    
        int result = idx - least + 1; 
        set(result, -1);            
        System.out.println(result);   
        return result;
    }

    public static int n;
    public static int N;
    public static int least;
    public static int k;
    public static long[] trees;
    public static int total;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        total = 4000000;
        trees = new long[total];

        
        //트리설정..했음
        for(int i=0; i<total; i++){
            trees[i] = 0;
        }

        calculate_least();
        n = sc.nextInt();
        //이제 최소값 설정했으니 질의랑 뭐 등등 할 때 편하게 가능
        for(int i=0; i<n; i++){
            int which = sc.nextInt();
            //이건 
            if(which == 1){
                int count = sc.nextInt();
                find(count);
            }
            else{
                int node = sc.nextInt();
                long how_many=  sc.nextInt();
                set(node, how_many);
            }
/*
            for(int j=least; j<least+10; j++){
                System.out.println(trees[j]);
            }
            */
        }
        
    }
}