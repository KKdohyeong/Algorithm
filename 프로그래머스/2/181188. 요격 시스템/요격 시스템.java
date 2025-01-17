import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });
        int currEnd = -1;
        for(int[] target : targets){
            
            // 첫 폭격미사일인 경우
            if(currEnd == -1){
                answer++; 
                currEnd = target[1];
                continue;
            } 
            
            if(target[0] < currEnd) continue;
            
            answer++; 
            currEnd = target[1]; 

        }
        
        
        return answer;
    }
}