import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return Integer.compare(o1, o2);
            }
        });
        
        for(int value : scoville){
            pq.add(value);
        }
        int answer =0 ;
        while(pq.size() >=2 && pq.peek() < K){
            answer++;
            int min = pq.poll();
            int min_two = pq.poll();
            
            pq.add(min + min_two*2);
        }
        
        if(pq.peek() < K){
            answer = -1;
        }
        
        
        
        
        return answer;
    }
}