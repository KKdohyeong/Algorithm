import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".



class Main {

// 2 3 이렇게 입력이 들어온다 A랑 B인데 3이 되려면 2번이 이루어져야한다는 뜻이다.
/*

이거 그거인듯? 


    */



    public static class Node{
        public int number;
        public int watched;
        public int answer;
        
        public Node(int number, int watched){
            this.number = number;
            this.watched =watched;
            this.answer = 0;
        }
    }
    
    public static int N;
    public static int M;
    
    public static LinkedList<Integer>[] lists;

    public static Node[] nodes;

    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stk  = new StringTokenizer(br.readLine());

        
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        nodes = new Node[N+1];
        lists  = new LinkedList[N+1];

        for(int i=0; i<N+1; i++){
            lists[i] = new LinkedList<>();
        }

        for(int i=1; i<=N; i++){
            nodes[i] = new Node(i, 0);
        }
        
        for(int i = 1; i<=M; i++){
            stk = new StringTokenizer(br.readLine());
            
            int first = Integer.parseInt(stk.nextToken());
            int after = Integer.parseInt(stk.nextToken());


            //1이 되어야 3이 가능하면, 3이 1명이 지켜본다 생각하면 ㅗ대.
            nodes[after].watched++;


            //이제 1이 끝나면 3을 지켜봐야하니까
            lists[first].add(after);
            
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i=1; i<=N; i++){
            //1번은 아무도 안보니까 1이 추가될듯
            if(nodes[i].watched==0){
                queue.add(i);
            }
        }

        int time = 1;
        while(!queue.isEmpty()){
            int size  = queue.size();

            for(int i=0; i<size; i++){
                
                int node = queue.poll();

                //먼저 나는 언제부터 괜찮은지 이제 설정한다
                nodes[node].answer = time;
                
                //이제 이 번호가 바라보는 놈들을 watche -1하자.
                for(int next : lists[node]){
                    nodes[next].watched--;

                    if(nodes[next].watched==0){
                        queue.add(next);
                    }
                }    
            }

            time++;
        }

        for(int i=1; i<=N; i++){
            System.out.printf("%d ", nodes[i].answer);
        }

        
        
        
    }
}