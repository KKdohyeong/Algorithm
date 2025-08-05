import java.util.*;


import java.io.*;


public class Main{


	
	public static int N;
	
	public static int[] trees;
	public static int[] values;
	
	public static int least;
	
	public static long answer;

	//난 tree에 그 value에 해당하는 index를 넣자! 
	
	public static void calculate_least() {
		least = 1;
		while(least < N) {
			least = least*2;
		}
		
	}
	
	
	public static void set_trees() {
		for(int i=N+least-1; i>0; i--) {
			if(trees[i/2] == -1) {
				trees[i/2] = trees[i];
			}
			else {
				//기존에 값이 존재한다면 beforeㅡ
				int before_h = values[trees[i/2]];
				
				
				int now_h = values[trees[i]];
				
				//이럼 교체해야지. 내 index가 더 작은거야.
				if(now_h <= before_h) {
					trees[i/2] = trees[i];
				}
				
			}
		}
	}
	
	public static int query(int start, int finish) {
		int min_value = Integer.MAX_VALUE;
		int min_index = 0;
		
		
		while(start <= finish && start>0) {
			if(start %2 ==1) {
				int here_min_index = trees[start];
				int here_min_value = values[here_min_index];
				
				if(min_value > here_min_value) {
					min_index = here_min_index;
					min_value = here_min_value;
				}
				
			}
			if(finish %2 ==0) {
				
				int here_min_index = trees[finish];
				int here_min_value = values[here_min_index];
				
				if(min_value > here_min_value) {
					min_index = here_min_index;
					min_value = here_min_value;
				}
				
			}
			start = (start+1)/2;
			finish = (finish-1)/2;
		}
		
		return min_index;
	}
	
	
	
	//start finish는 value index니까 트리로 할 떄는 트리로 바꿔서 넣어야지
	public static void half_search(int start, int finish) {
		/*
		 * start~finish의 최솟값을 구한다.
		 * 
		 * 
		 * 그리고 좌우로 half로 이동해서 더 한다.
		 * 
		 * 
		 */
		
		
		if(start < 0 || finish >= N ||start > finish || finish < start) {
			return;
		}
		
		int tree_start = start +least;
		int tree_finish = finish + least;
		
		int min_index = query(tree_start, tree_finish);
		int min_h = values[min_index];
		
		answer = Math.max(answer, (long)(finish-start+1) * (long)min_h);
		
		if(min_index+1 > start) {
			half_search(min_index+1, finish);
			
		}
		
		if(min_index-1 < finish) {
			half_search(start, min_index-1);
			
		}
		//System.out.printf("%d %d탐색 값은 %d * %d \n", start, finish, finish-start+1, min_h);

		
		
	}

	
	
	public static void main(String[] args) throws IOException {
		
		
		
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			
			while(true) {
				StringTokenizer stk = new StringTokenizer(br.readLine());
				
				N = Integer.parseInt(stk.nextToken());
				
				
				if(N==0) {
					break;
				}
				answer = 0;
				
				trees = new int[4*N];
				values = new int[N+1];
				
				calculate_least();
				
				Arrays.fill(trees,  -1);
				
				for(int i=0; i<N; i++) {
					int value = Integer.parseInt(stk.nextToken());
					values[i] = value;
					trees[least+i] = i;
				}
				
				set_trees();
				half_search(0, N-1);
                sb.append(answer);
                sb.append("\n");
                
				
				
				
			}
        System.out.println(sb.toString());
			

		
	}
	
}
