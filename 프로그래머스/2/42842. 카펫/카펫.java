import java.util.*;
class Solution {
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int map[][];
        int total = brown+yellow;
        
        // 카펫의 길이는 가로가 더 길거나 같다
        for(int i=1; i<=total; i++) {
            if(total%i==0) {
                int row = i;
                int col = total/row;
                
                if(row > col) {
                    continue;
                }
                
                int inside_block = (row-2)*(col-2);
                if(inside_block == yellow) {
                    answer[0] = col;
                    answer[1] = row;
                    return answer;
                }
            }
        }
        
        
        
        
        
        
        
        return answer;
    }
}