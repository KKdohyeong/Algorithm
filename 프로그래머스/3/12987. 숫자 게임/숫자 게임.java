import java.util.*;
import java.io.*;

class Solution {
    
    PriorityQueue<Integer> A_pq = new PriorityQueue<>(new Comparator<Integer>(){
        @Override
        public int compare(Integer o1, Integer o2){
            return Integer.compare(o1, o2);
        }
    });
    
    
    PriorityQueue<Integer> B_pq = new PriorityQueue<>(new Comparator<Integer>(){
        @Override
        public int compare(Integer o1, Integer o2){
            return Integer.compare(o1, o2);
        }
    });
    
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        
        for(int a : A){
            A_pq.add(a);
        }
        for(int b : B){
            B_pq.add(b);
        }
        
        
        //a값을 빼와서 이거보다 큰 B를 처음 만날때까지 뺀다.
        while(!A_pq.isEmpty()){
            int a = A_pq.poll();
            
            
            boolean find_out = true;
            
            while(find_out && !B_pq.isEmpty()){
                int b = B_pq.poll();
                
                if(b>a){
                    answer++;
                    find_out = false;
                }
                
            }
        }
        
        return answer;
    }
}