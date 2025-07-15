import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static int[] depth;
    public static int[] parent;
    public static int[] visited;
    public static LinkedList<Integer>[] arounds;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        depth = new int[n+1];
        parent = new int[n+1];
        arounds = new LinkedList[n+1];
        visited = new int[n+1];
        parent[1] = 0;
        for(int i=0; i<n+1; i++){
            arounds[i] = new LinkedList<>();
            visited[i] = 0;
        }

        for(int i=0; i<n-1; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            arounds[b].add(a);
            arounds[a].add(b);
        }

        init();

        int how_many = sc.nextInt();

        for(int i=0;i <how_many; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int answer = lca(a, b);
            System.out.println(answer);
        }
        
    }

    public static int lca(int a, int b){
        int a_depth = depth[a];
        int b_depth = depth[b];

        if(a_depth > b_depth){
            for(int i=0; i<a_depth - b_depth; i++){
                a = parent[a];
            }
        }
        else{
            for(int i=0; i<b_depth - a_depth; i++){
                b = parent[b];
            }
        }

        while(parent[a] != parent[b]){
            a = parent[a];
            b = parent[b];
        }

        if(a==b){
            return a;
        }
        
        return parent[a];
        
    }

    public static void init(){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = 1;
        int depth_size = 1;
        while(!queue.isEmpty()){
            int size = queue.size();

            //이제 머 3번을 보자.
            for(int i=0; i<size; i++){
                int traverse = queue.poll();
                visited[traverse] = 1;
                depth[traverse] = depth_size;

                for(int next : arounds[traverse]){
                    // 1의 근처에 2랑 3이 있어. 2랑 3이 방문이 안됬다면 2와 3의 부모는 1이지
                    if(visited[next] == 0){
                        queue.add(next);
                        parent[next] = traverse;
                    }
                }
                
            }
            depth_size++;
        }
    }
}