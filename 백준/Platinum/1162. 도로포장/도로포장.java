import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {


    static class Edge{
        public int next_node;
        public long value;
        public int k;

        public Edge(int next_node, long value, int k){
            this.next_node = next_node;
            this.value = value;
            this.k = k;
            
        }
        
    }
    
    
    public static int N;
    public static int M;
    public static int K;

    
    
    public static long[][] dp;
    public static LinkedList<Edge>[] arrays;

    public static int[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());
        K = Integer.parseInt(stk.nextToken());

        dp = new long[K+1][N+1];
        visited = new int[N+1];
        
        for(int i=0; i<K+1; i++){
            for(int j=0; j<N+1; j++){
                dp[i][j] = Long.MAX_VALUE/4;
            }
        }
        
        for(int i=0; i<N+1; i++){
            visited[i] = 0;
        }
        arrays = new LinkedList[N+1];
        for(int i=0; i<N+1; i++){
            arrays[i] = new LinkedList<>();
        }

        for(int i=0; i<M; i++){
            stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());
            int value = Integer.parseInt(stk.nextToken());
            arrays[a].add(new Edge(b, value,0));
            arrays[b].add(new Edge(a, value,0));
        }



         PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
        	 @Override
        	 public int compare(Edge o1, Edge o2) {
        		 return Long.compare(o1.value, o2.value);
        	 }
         });
            
         
         //이제 1번에서 출발하는 것들을 담는것이다. 근데 1번에서 1번으로 0값이고 0번 없앴다.
         queue.add(new Edge(1, 0 , 0));
            
         /*
          * 
          * 1.초기값들은 전부 max값이고 1시작값을 이제 0으로 준다
          * 
          * 2. 큐에서 이제 내가 도착한 값이 기존 값보다 작으면 ㅇㅋ한다.
          * 
          * 3. 작은 값을 업데이트 해야겠지??
          * 
          */

         
         
         while(!queue.isEmpty()) {
        	 Edge edge = queue.poll();
        	 int arrive = edge.next_node;
        	 long arrive_value = edge.value;
        	 int k = edge.k;
        	 
        	 if(dp[k][arrive] <= arrive_value) {
        		 continue;
        	 }

        	 dp[k][arrive] = Math.min(dp[k][arrive], arrive_value);
        	 
        	 
        	 for(Edge next : arrays[arrive]) {
        		 
        		 int next_arrive = next.next_node;
        		 long middle_value = next.value;

        		 

        		 if(dp[k][next_arrive] > dp[k][arrive] + middle_value) {
            		 queue.add(new Edge(next_arrive,dp[k][arrive] + middle_value, k));
        		 }
        		 
        		 for(int i=k+1; i<=K; i++) {
        			 	
        			 if(dp[i][next_arrive] > dp[i-1][arrive]) {
        				 queue.add(new Edge(next_arrive, dp[i-1][arrive], i));
        				
        			 }
        			 
        		 }
        		 
        		 
        	 }
        	 
         }
    	 
         long min = Long.MAX_VALUE;
         
    	 for(int i=0; i<=K; i++) {
    		 min = Math.min(min,  dp[i][N]);
    	 }
         System.out.println(min);


        
    }
}