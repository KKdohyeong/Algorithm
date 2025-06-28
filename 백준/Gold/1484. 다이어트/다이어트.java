import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		long s= 1;
		long e =2;
		
		boolean flag = false;
		while(e<100_000) {
			long ps = s*s;
			long pe = e*e;
			if(pe-ps == n) {
				System.out.println(e);
				flag = true;
			}
			
			if(pe-ps >n){
				s++;
			}else {
				e++;
			}
		}
		
		if(!flag) {
			System.out.println(-1);
		}
	}
}