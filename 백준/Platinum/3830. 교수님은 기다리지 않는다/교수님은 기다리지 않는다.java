import java.util.*;


import java.io.*;

/*
 * 1. 1번부터~N번까지네
 * 
 * 
 * 
 * 
 */


public class Main{

	public static int N;

	public static int M;
	
	public static ArrayList<Integer> answers;
	

	public static int[] set_index;
	public static int[] arrays;
	public static int[] parents;
	public static Node[] nodes;
	
	
	public static class Node{
		long diff;
		int number;
		
		public Node(long diff, int number) {
			this.diff = diff;
			this.number = number;
		}
		
	}



    
    public static int find(int node){
        if(parents[node] == node){
            return node;
        }

        int before = parents[node];

        parents[node] = find(parents[node]);

        if(before != parents[node]){
            nodes[node].diff += nodes[before].diff;
        }

        return parents[node];
        
    }


    /*
    지금 small보다 big이 value만큼 크다고 들어옴

    */
    public static void union(int small, int big, int value){
        int small_parent = find(small);
        int big_parent = find(big);
        //여기서 find를 하면은 이제 정렬은 되어있따.
        if(small_parent == big_parent){
            return;
        }

        //부모 정렬 확인

        // 작은 놈이 더 크다면
        if(nodes[small].diff > value + nodes[big].diff){
            parents[big_parent] = small_parent;
            nodes[big_parent].diff = nodes[small].diff - (value + nodes[big].diff);
        }
        //큰 놈이 더 크다면
        else{
            parents[small_parent] = big_parent;
            nodes[small_parent].diff = value + nodes[big].diff - nodes[small].diff;
        }
        
    }

    public static void debug(){
        for(int i=1; i<=N; i++){
            System.out.printf("%d번은 %d가 부모이고 차이는 %d이다\n", i, parents[i], nodes[i].diff);
        }
    }

	
	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			StringTokenizer stk = new StringTokenizer(br.readLine());
			//System.out.println("시작!");

			
			//N은 노드 갯수, M은 query 갯수
			N = Integer.parseInt(stk.nextToken());
			
			M = Integer.parseInt(stk.nextToken());

            if(N==0 && M==0){
                return;
            }
            
			//union find를 위한 부모
			parents = new int[N+1];
			nodes = new Node[N+1];
			
			
			for(int i=1; i<N+1; i++) {
				parents[i] = i;
				nodes[i] = new Node(0, i);
			}
			
			for(int i=0; i<M; i++) {
                //System.out.println();
				 stk = new StringTokenizer(br.readLine());
				 String first = stk.nextToken();
				 if(first.equals("!")) {
					 int small = Integer.parseInt(stk.nextToken());
					 int big = Integer.parseInt(stk.nextToken());
					 int value = Integer.parseInt(stk.nextToken());

                     union(small, big, value);
                     //debug();
				 }
				 else {
					 int a = Integer.parseInt(stk.nextToken());
					 int b = Integer.parseInt(stk.nextToken());
                    
                    if(find(a) != find(b)){
                        System.out.println("UNKNOWN");
                        continue;
                    }
                    System.out.println(nodes[a].diff - nodes[b].diff);
				 }
				 
			}
			
			
			
		
		}
		
		
	}
	
}
