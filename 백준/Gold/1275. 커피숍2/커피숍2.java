import java.util.*;
import java.io.*;


public class Main{



    public static void get_least(){
        least = 1;
        //5면은 8이 되고 8이면 8이된다
        while(least < N){
            least = least*2;
        }
    }

    public static void update(int value, int index){
        // 해당 부분의 값을 업데이트, 
        trees[index] = value;

        while(index>0){
            //오른쪽이면
            if(index%2 ==1){
                trees[index/2] = trees[index-1] + trees[index];
            }

            else{
                trees[index/2] = trees[index] + trees[index+1];
            }
            index = index/2;
        }
    }

    public static long sum(int start, int finish){
        long result =0;

        while(start <= finish && start > 0){


            //시작은 오른쪽에 있으면 더해야지
            if(start %2 ==1){
                result += trees[start];
            }
            start = (start+1)/2;


            //끝은 왼족에 있으면 더해야지

            if(finish %2 ==0){
                result+=trees[finish];
            }
            finish = (finish-1)/2;
        }


        return result;

        
    }
    

	public static int N;
	public static int Q;
	
	public static long[] trees;
	
	
	public static int least;
	public static int k;


    
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer stk = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stk.nextToken());
		Q = Integer.parseInt(stk.nextToken());
		
		trees = new long[4*N];
        Arrays.fill(trees, 0);
        
        get_least();
		
		stk = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			int value = Integer.parseInt(stk.nextToken());
            update(value, i+least);
		}


        

        

		for(int i=0; i<Q; i++) {
			stk = new StringTokenizer(br.readLine());
			int start_node = Integer.parseInt(stk.nextToken()) + least -1;
			int finish_node = Integer.parseInt(stk.nextToken()) + least -1;

			if(finish_node < start_node) {
				int temp = finish_node;
				finish_node = start_node;
				start_node = temp;
			}

            
            long result = sum(start_node, finish_node);


            
            System.out.println(result);
            int index = Integer.parseInt(stk.nextToken()) + least -1;
            int value = Integer.parseInt(stk.nextToken());

            update(value, index);
            
			
		}
		
		
		
	}

}