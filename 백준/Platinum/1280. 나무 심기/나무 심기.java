import java.util.*;


import java.io.*;

/*
 * 그냥 좌표 순으로 박는다.
 *
 * 그리고 count tree랑 좌표 트리 두 개를 만들자
 * 
 * 그리고 번호가 작은 것부터 박을 것이다.
 * 
 * 좌우 쿼리 ->
 * 
 * 
 * 
 */


public class Main{
	
	
	
	public static class Tree{
		public int number;
		public int x;
		public int index;
		
		public Tree(int number, int x) {
			this.number = number;
			this.x = x;
			this.index = -1;
		}
	}


	
	public static int N;
	
	public static int least;
	
	public static PriorityQueue<Tree> index_queue;
	public static PriorityQueue<Tree> queue;
	
	
	public static int[] count_trees;
	public static long[] sum_x_trees;
	
	public static void calculate_least() {
		least = 1;
		while(least < N) {
			least = least * 2;
		}
	}
	

	public static void update_count(int start) {
		while(start>0) {
			count_trees[start]++;
			start = start/2;
			
		}
	}
	
	public static void update_sum(int start, int value) {
		sum_x_trees[start] += value;
		while(start>0) {
			if(start%2 ==0) {
				sum_x_trees[start/2] = sum_x_trees[start] +sum_x_trees[start+1]; 
				start = start/2;
			}
			else {
				sum_x_trees[start/2] = sum_x_trees[start] +sum_x_trees[start-1]; 
				start = start/2;
			}
		}
	}
	
	
	public static long query_sum(int start, int finish) {
		long answer = 0;
		if(finish < least || start >= N + least || start > finish) {
			return 0;
		}
		
		while(start <= finish && start > 0) {
			if(start%2==1) {
				answer += sum_x_trees[start];
			}
			if(finish%2==0) {
				answer += sum_x_trees[finish];
			}
			start = (start+1)/2;
			finish = (finish-1)/2;
			
		}
		
		
		return answer;
	}
	
	public static long query_count(int start, int finish) {
		long answer = 0;
		if(finish < least || start >= N + least || start > finish) {
			return 0;
		}
		
		while(start <= finish && start > 0) {
			if(start%2==1) {
				answer += count_trees[start];
			}
			if(finish%2==0) {
				answer += count_trees[finish];
			}
			start = (start+1)/2;
			finish = (finish-1)/2;
			
		}
		
		
		return answer;
	}
	
	/*
	 * 1. 가장 number가 작은 애를 뽑아
	 * 
	 * 2. 왼쪽에 갯수 몇개인지 확인
	 * 2.1 왼쪽 좌표들 sum 확인
	 * 2.2 거리 += (갯수 * 본인 x) - (왼쪽 좌표들 sum)
	 * 
	 *  3. 오른쪽에 갯수 몇개인지 확인
	 *  3.1 오른쪽 좌표 개수 sum 확인
	 *  3.2 거리 += (오른쪽 좌표들 sum) - (갯수 * 본인x)
	 *  
	 *  4. 본인 갯수, sum index tree에서 업데이트
	 * 
	 */
	
	
	public static long get_answer() {
		long answer = 1;
		int count = 0;
		while(!queue.isEmpty()) {
			Tree tree = queue.poll();
			int tree_index = tree.index;
			long left_count = query_count(least, tree_index-1);
			long left_sum = query_sum(least, tree_index -1);
			
			long left_answer = (left_count * tree.x) - left_sum;
			
			long right_count = query_count(tree_index+1, least+N-1);
			long right_sum = query_sum(tree_index+1, least+N-1);
			
			long right_answer = right_sum - (right_count * tree.x);
			

			
			long gob = left_answer %1000000007 + right_answer % 1000000007;
			if(count!=0) {
				answer = answer % 1000000007;
				gob = gob % 1000000007;
				answer = answer * gob;		
				answer = answer % 1000000007;
			}
			update_count(tree_index);
			update_sum(tree_index, tree.x);
			count++;
			//System.out.printf("%d번을 뽑았고 %d에 위치한 놈이야 왼쪽 값은 %d를 구했고 오른쪽은 %d를 구함\n", tree.number, tree.x, left_answer, right_answer);
			
		}
		
		
		
		
		return answer;
	}

	
	public static void main(String[] args) throws IOException {
		
		
		
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			StringTokenizer stk = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(stk.nextToken());
			
			calculate_least();
			
			count_trees = new int[4*N];
			sum_x_trees = new long[4*N];
			Arrays.fill(count_trees,  0);
			Arrays.fill(sum_x_trees,  0);
			
			
			index_queue = new PriorityQueue<>(new Comparator<Tree>() {
				@Override
				public int compare(Tree o1, Tree o2) {
					return Integer.compare(o1.x, o2.x);
				}
			});
			
			queue = new PriorityQueue<>(new Comparator<Tree>() {
				@Override
				public int compare(Tree o1, Tree o2) {
					return Integer.compare(o1.number, o2.number);
				}
			});
						
			
			for(int i=1; i<=N; i++) {
				 stk = new StringTokenizer(br.readLine());
				 int x = Integer.parseInt(stk.nextToken());
				 index_queue.add(new Tree(i, x));
			}
			
			//이제 queue를 기반으로 index번호를 정해주자
			
			int index = 0;
			while(!index_queue.isEmpty()) {
				Tree tree = index_queue.poll();
				//뽑힌 순서대로 인덱스 하자
				tree.index = index + least;
				index++;
				
				queue.add(tree);
			}
			
			long answer = get_answer();
			answer = answer % 1000000007;
			System.out.println(answer);
			//System.out.println("ㅁ");
		
	}
	
}
