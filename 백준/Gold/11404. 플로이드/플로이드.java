import java.util.*;
import java.io.*;

/*
 * 파악한 근거
 * 
 * 
 * 
 */




public class Main{
	

	
	
	
	
	public static int N;
	public static int E;
	
	public static long[][] dp;
	
	
	public static void floid() {
		
		
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					//k로 가볼래?
					
					if(dp[i][k] == Long.MAX_VALUE || dp[k][j] == Long.MAX_VALUE ) {
						continue;
					}
					dp[i][j] = Math.min(dp[i][j] , dp[i][k] + dp[k][j]);
					
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(dp[i][j] != Long.MAX_VALUE) {
					System.out.printf("%d ", dp[i][j]);
					
				}
				else {
					System.out.printf("0 ");

				}
			}
			System.out.println();
		}
		
	}
	
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk  = new StringTokenizer(br.readLine());
		
		StringBuilder sb = new StringBuilder();

		
		N = Integer.parseInt(stk.nextToken());
		stk  = new StringTokenizer(br.readLine());

		E = Integer.parseInt(stk.nextToken());

		dp = new long[N+1][N+1];

		for(int i=1; i<=N; i++) {
			Arrays.fill(dp[i],  Long.MAX_VALUE);
			
			dp[i][i] = 0;
		}
		
		
		
		for(int i=0; i<E; i++) {
			 stk  = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(stk.nextToken());
				int arrive = Integer.parseInt(stk.nextToken());
				int value = Integer.parseInt(stk.nextToken());
				
				dp[start][arrive] = Math.min(dp[start][arrive], value);
				
		}
	
		
		floid();
		
		
		
		
		
		
		
	}
	
}
