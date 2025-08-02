import java.util.*;


import java.io.*;


public class Main{

	public static int N;

	public static ArrayList<Integer> answers;
	
	
	
	// 이거 지금 get에서 음... O(N)이 걸ㄹ려서 O(N^2)걸리는거아냐? 이분탐색이면 머하냐 흠
	
	public static int find_index(int value) {
	    int l = 0;
	    int r = answers.size() - 1;
	    int mid;

	    // while(l <= r)을 사용하되, l을 최종 반환 값으로 쓴다.
	    while (l <= r) {
	        mid = (l + r) / 2;

	        if (answers.get(mid) >= value) {
	            // 현재 중간 값이 value보다 크거나 같으면, 더 왼쪽에서 찾아본다.
	            // right = mid; 와 같음
	            r = mid - 1; 
	        } else {
	            // 현재 중간 값이 value보다 작으면, 더 오른쪽에서 찾아본다.
	            l = mid + 1;
	        }
	    }
	    // 루프가 끝난 후, l은 value가 삽입되어야 할 위치를 정확히 가리킨다.
	    return l;
	}
	
	public static int[] set_index;
	public static int[] arrays;
	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
    
		
		N = Integer.parseInt(stk.nextToken());


		answers = new ArrayList<>();	
		set_index = new int[N];
		arrays = new int[N];
		
		
		
		 stk = new StringTokenizer(br.readLine());

		 for(int i=0; i<N; i++) {
			 int value = Integer.parseInt(stk.nextToken());
			 arrays[i] = value;
			 //첫 시작만 따로 뺴자
			 if(i==0) {
				 answers.add(value);
				 set_index[i] = 1;
				 continue;
			 }
			 
			 int list_max = answers.get(answers.size()-1);
			 
			 
			 //큰값이면 마지막에 추가해야지
			 if(value > list_max) {
				 answers.add(value);
				 set_index[i] = answers.size();

			 }else {
				 int get_index = find_index(value);
				 //System.out.printf("%d값은 %d index에 넣자\n", value, get_index);
				 answers.set(get_index, value);
				 set_index[i] = get_index+1;
			 }
			 
			 
		 }
		 
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(answers.size());
        sb.append("\n");
        
		 
		 
		 
		 Stack<Integer> stack = new Stack<>();
		 
		 int count = answers.size();
		 for(int i=N-1; i>=0; i--) {
			 if(count==0) {
				 break;
			 }
			if(count == set_index[i]) {
				count--;
				stack.push(arrays[i]);
			}
		 }
		 
		 
		 

		 
		 while(!stack.isEmpty()) {
			 sb.append(stack.pop());
			 sb.append(" ");
		 }
		 System.out.print(sb.toString());
		 
	}
	
}
