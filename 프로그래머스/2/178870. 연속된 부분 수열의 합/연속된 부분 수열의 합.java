import java.util.*;

/*
O(N^2)을 하지말자는건가

sliding window하면뭔가될거같은느낌
*/

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];    
        int start =0;
        int end = 0;
        int start_min = 0;
        int finish_min = 0;
        int min_len = 1000000;
        int total = sequence[0];
        
        while(start<=end && start < sequence.length && end < sequence.length){
            if(total == k){
                if(end-start < min_len){
                    start_min = start;
                    finish_min = end;
                    min_len = end-start;
                }
                total = total - sequence[start];
                start++;
            }
            else if(total > k){
                total = total - sequence[start];
                start++;
            }
            else if(total < k){
                end++;
                if(end >= sequence.length){
                    break;
                }
                total = total + sequence[end];
            }
            
        }
        
        answer[0] = start_min;
        answer[1] = finish_min;
        return answer;
        
    }
}