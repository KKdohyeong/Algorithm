import java.util.*;
import java.io.*;


public class Main{
	
	
	static class Alphabet{
		Character c;
		int value;
		
		public Alphabet(Character c, int value) {
			this.c = c;
			this.value=value;
		}
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(stk.nextToken());
		
		HashMap<Character, Integer> my_map = new HashMap<>();
		
		
		for(int i=0; i<n; i++) {
			String str = br.readLine();
			int value = 1;
			for(int j=str.length()-1; j>=0 ; j--) {
				Character c = str.charAt(j);
				
				//키 값이 있으면 가져와서 더해서 넣는다.
				if(my_map.containsKey(c)) {
					int now_value = my_map.get(c);
					my_map.put(c,  now_value + value);
			
				}
				//키 값이 없으면 그냥 더한다.
				else {
					
					my_map.put(c, value);
				}
				
				
				value = value * 10;
				
			}
			
		}
		
		LinkedList<Alphabet> lists = new LinkedList<>();
		
		for(Map.Entry<Character, Integer> entry : my_map.entrySet()) {
			//System.out.printf("%c는 %d값이 나온다.\n", entry.getKey(), entry.getValue());
			
			
			lists.add(new Alphabet(entry.getKey(), entry.getValue()));
		}
		
		Collections.sort(lists, new Comparator<Alphabet>() {
			@Override
			public int compare(Alphabet o1, Alphabet o2) {
				
				return Integer.compare(o2.value, o1.value);
			}
		});
		
		int value = 9;
		int result = 0;
		
		for(Alphabet alpha : lists) {
			result = result + alpha.value * value;
			value--;
		}
		
		System.out.println(result);
		
		
		
		
	}

}