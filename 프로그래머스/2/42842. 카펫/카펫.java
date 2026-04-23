import java.util.*;
import java.io.*;
    

/*
걍 다 해보면 될거같은데?

세로 1
세로 2

*/

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        for(int row=1; row<= yellow; row++){
            if(yellow % row != 0){
                continue;
            }
            int col = yellow / row;

            
            int total_need = 4 + 2*row + 2*col;
            
            //System.out.printf("(%d,%d)로 %d개\n", row, col, total_need);
            
            
            //0에는 col 1에는 row
            if(total_need == brown){
                answer[0] = col+2;
                answer[1] = row+2;
                break;
            }
            
            
        }
        
        return answer;
    }
}