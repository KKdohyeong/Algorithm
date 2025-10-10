import java.util.*;
import java.io.*;


public class Main{



    public static int find(int number){
        if(parents[number]==number){
            return number;
        }

        parents[number] = find(parents[number]);
        
        return parents[number];
    }



    /*
        우리의 끝 부모는 같니?
    */
    public static boolean union(int a, int b){
        int finish_a = find(a);
        int finish_b = find(b);

        if(finish_a == finish_b){
            return true;
        }

        parents[finish_a] = finish_b;
        return false;
    }
	

	public static int N;
	
	public static int M;

	public static int[] parents;
	

    
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk =new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(stk.nextToken());
		M = Integer.parseInt(stk.nextToken());
		
		parents = new int[N+1];
		for(int i=0; i<=N; i++) {
			parents[i] = i;
		}
		
		for(int i=0; i<M; i++) {
			 stk =new StringTokenizer(br.readLine());
			
			int which= Integer.parseInt(stk.nextToken());
			int a = Integer.parseInt(stk.nextToken());	
			int b = Integer.parseInt(stk.nextToken());	

            //이건 유니온임
            if(which==0){
                 union(a,b);   
            }
                //이건질의임
            else{
                if(find(a) == find(b)){
                    System.out.println("YES");
                }
                else{
                    System.out.println("NO");
                }
            }
			
		}
		

	}

	
}
