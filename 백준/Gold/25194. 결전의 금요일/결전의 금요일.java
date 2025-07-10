import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Main {
	static int N;
	static boolean result = false;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		list = new ArrayList<>();
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken());
			if(a % 7 != 0) {
				cnt++;
				list.add(a);
			}
		}
		if(cnt >= 7)
			result = true;
		else {
			dfs(0,0, list.size());
		}
		if(result)
			System.out.println("YES");
		else
			System.out.println("NO");
	}
	static void dfs(int idx, int tmp, int size) {
		if(idx == size)
			return;
		if((tmp + list.get(idx)) % 7 == 4)
			result = true;
		dfs(idx+1, tmp, size);
		dfs(idx+1, tmp+list.get(idx), size);
	}
}