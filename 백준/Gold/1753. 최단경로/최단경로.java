import java.util.*;
import java.io.*;

/*
 * 파악한 근거
 * 
 * 1. 시작점이 정해져있고 거기서부터 모든 경로의 최솟값이다
 * 2. 최솟값이니까 먼가 여러 경로 or 사이클이 있어도 걱정이 없다
 * 3. 노드 수가 2만 30만 이래서 다익스트라 O(ELogE)로 해결 쌉가능
 * 
 * 
 * 
 * 
 * edge로 다 처리하려니까 뭔가 헷갈릴거같냐
 * 
 */




public class Main{
	

	
	
	
	
	public static int N;
	public static int E;
	public static int start_node;
	
	public static class Edge{
		public int arrive;
		public int value;
		
		public Edge(int arrive, int value) {
			this.arrive = arrive;
			this.value = value;
		}
	}


	
	public static LinkedList<Edge>[] lists;
	public static long[] dp;
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk  = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(stk.nextToken());
		E = Integer.parseInt(stk.nextToken());

		lists = new LinkedList[N+1];
		for(int i=1; i<=N; i++) {
			lists[i] = new LinkedList<>();
		}
		dp = new long[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		 stk  = new StringTokenizer(br.readLine());
		 start_node = Integer.parseInt(stk.nextToken());

		
		 for(int i=0; i<E; i++) {
			 stk  = new StringTokenizer(br.readLine());
			 int start = Integer.parseInt(stk.nextToken());
			 int arrive = Integer.parseInt(stk.nextToken());
			 int value = Integer.parseInt(stk.nextToken());
			 
			 lists[start].add(new Edge(arrive, value));
		 }
		
		 
		 
		 //시작해보자 다익
		 
		 PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
			 @Override
			 public int compare(Edge o1, Edge o2) {
				 return Integer.compare(o1.value, o2.value);
			 }
		 });
		 
		 dp[start_node] = 0;
		 queue.add(new Edge(start_node, 0));
		 
		 while(!queue.isEmpty()) {
			 Edge edge = queue.poll();
			 int value_arrive_here = edge.value;
			 int arrive = edge.arrive;
			 //종료조건은 이제 값이 더 커버리면 의미없음
			 if(dp[arrive] < value_arrive_here) {
				 continue;
			 }
			 
			 dp[arrive] = value_arrive_here;
			 
			 for(Edge next_edge : lists[arrive]) {
				 int next_arrive = next_edge.arrive;
				 int next_value = value_arrive_here + next_edge.value;
				 if(next_value < dp[next_arrive]) {
					 dp[next_arrive] = next_value;
					 queue.add(new Edge(next_arrive, next_value));
					 
				 }
				 
				 
				 
				 
			 }
			 
			 
		 }
		 
		for(int i=1; i<=N; i++) {
			if(dp[i] != Integer.MAX_VALUE) {
				sb.append(dp[i]);
				sb.append("\n");				
			}
			else {
				sb.append("INF\n");
			}

		}
		System.out.println(sb.toString());
		
		

	}
	
}
