import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {


    //k를 구하는 함수
    public static void calculate_k(){
        //n개가 들어오면 k는 몇인가를 구해야함
        k = 0;
        int value = 1;
        //n이 5면 value는 8이 되고 k는 3이 되어야함.
        while(value<N){
            value *= 2;
            k++;
        }
        k_value = value;
    }

    public static long calculate_hap(int start, int finish){
        
        //종료 조건은 start보다 index가 작아지는걸꺼야
        long result = 0;
        while(start <=finish){

            //start를 보고 더하자
            if(start %2 == 1){
                    result+=trees[start];
                    //System.out.printf("%d노드 값 %d를 더해서 %d가됐다\n", start, trees[start], result);
            }

            //start 이동하자 
            start = (start+1)/2;


            //finish는 왼쪽에 있어야 선택(끝)이다.
            if(finish%2==0){
                result+= trees[finish];
                //System.out.printf("%d노드 값 %d를 더해서 %d가됐다\n", finish, trees[finish], result);                
            }

            //finish 이동하자
            finish = (finish-1)/2;       
        }
        return result;
    }


    //이거는 이제 어디부터 시작하고 
    public static int k;
    public static int N;
    public static int k_value;
    public static int M;
    public static int K;
    public static long[] trees;

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        //1단계 k를 구한다 
        calculate_k();
        
        //2단계 트리 값 설정

        //넉넉하게 4N한다하더라
        trees = new long[4*N];
        trees[0] = 0;
        for(int i=0; i<N; i++){
            trees[i+k_value] = sc.nextLong();
        }

        //2.1단계 부모 설정하러 가자
        for(int i=N + k_value -1; i > 1; i--){
            trees[i/2] += trees[i];
        }


        //3단계 값들을 더해나가자
        for(int i=0; i<M+K; i++){
            int which = sc.nextInt();
/*
        for(int j=0; j<N+k_value; j++){
            System.out.printf("%d노드의 값이 %d이다\n", j, trees[j]);
        }
          */  
            if(which==2){
                int start = sc.nextInt();
                int finish = sc.nextInt();
                System.out.println(calculate_hap(start+k_value-1, finish+k_value-1));   
            }
            if(which==1){
                int node = sc.nextInt();
                long change = sc.nextLong();
                trees[node+k_value-1] = change;
                node += k_value - 1;

                //node부터 이제 부모로 올라가야함
                while(node>0){
                    trees[node/2] =0;
                    if(node%2 ==0){
                        trees[node/2] = trees[node] + trees[node+1];
                    }
                    else{
                        trees[node/2] = trees[node] + trees[node-1];
                    }
                    node = node/2; 
                }
            }
        }


    
        
    }
}