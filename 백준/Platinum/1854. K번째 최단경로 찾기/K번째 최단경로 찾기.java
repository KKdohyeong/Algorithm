
import java.util.*;
import java.io.*;


public class Main{


	public static int N;
	public static int M;
	public static int K;
	
	
	public static int[][] daik;
	
	public static LinkedList<Integer>[] list;
	
	public static void debug_array() {
		for(int i=1; i<K+1; i++) {
			for(int j=1; j<N+1; j++) {
				System.out.printf("(%d : %d) ", j, daik[i][j]);
			}
			System.out.println();
		}
	}
	
	//queue에 넣을 떄 갯수 보고 넣어야할듯 내가볼 때
	public static class Node{
		int start;
		int arrive;
		int value;
		
		public Node(int start, int arrive, int value) {
			this.arrive = arrive;
			this.value = value;
			this.start=start;
		}
	}
	
	public static class Queue_Node{
		int arrive;
		int value;
		
		public Queue_Node(int arrive, int value) {
			this.arrive = arrive;
			this.value = value;
		}
	}
	
	
	public static void getAnswer() {
		//이제 큐에 넣어서 계속 돌리면 돼.
		
		//1번 노드 근처의 애들을 큐에 넣고 정렬한다.
		for(Node next : lists[1]) {
			queue.add(new Queue_Node(next.arrive, next.value));
		}
		
		while(!queue.isEmpty()) {
			
			//꺼낸다
			Queue_Node node = queue.poll();
			
			
			//꺼냈다는 것은 그놈을 확정하겠다는 것이다. 꺼냈으면 이제 값을 저장하고 다시 추가로 넣는다.
			for(int i=1; i<=K; i++) {
				
				//만약 2번 노드로 도착하기로 했고 그것의 daik이 -1이라면 아직 갱신 안된 것
				//k넘으면 큐에 안넣는거 무조건 주의해아함

				if(daik[i][node.arrive]== -1) {
					daik[i][node.arrive] = node.value;
					
					for(Node next : lists[node.arrive ]) {
						queue.add(new Queue_Node(next.arrive, daik[i][node.arrive] + next.value));
					}
					
					
					break;
				}
			}
			
		}
		
		
		
	}
	
	
	public static LinkedList<Node>[] lists;
	public static PriorityQueue<Queue_Node> queue;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		K = Integer.parseInt(stk.nextToken());
		
		
		daik = new int[K+1][N+1];
		lists = new LinkedList[N+1];
		queue = new PriorityQueue<>(new Comparator<Queue_Node>() {
			@Override
			public int compare(Queue_Node o1, Queue_Node o2) {
				return Integer.compare(o1.value, o2.value);
			}
		});
		
		for(int i=1; i<N+1; i++) {
			lists[i] = new LinkedList<>();
		}
		
		for(int i=1; i<K+1; i++) {
			Arrays.fill(daik[i], -1);
		}
		
		daik[1][1] = 0;


		
		for(int i=0; i<M; i++) {
			stk = new StringTokenizer(br.readLine());

			int start = Integer.parseInt(stk.nextToken());
			int finish = Integer.parseInt(stk.nextToken());
			int value = Integer.parseInt(stk.nextToken());
			
			lists[start].add(new Node(start, finish, value));
		}
		
		getAnswer();
		//debug_array();
		
		
		for(int i=1; i<N+1; i++) {
			System.out.println(daik[K][i]);
		}
	}

}