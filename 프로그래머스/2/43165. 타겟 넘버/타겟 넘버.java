import java.util.*;
import java.io.*;

/*
숫자 갯수는 20개네. 완전탐색? 모든경우의수 해봐야함.

*/

class Solution {
    
    public int answer = 0;
    
    public void traverse(int depth, int[] numbers, int target){
        //계산하기
        if(depth == numbers.length){
            int total = 0;
            for(int i=0; i<numbers.length; i++){
                total+=numbers[i];
            }
            
            if(total == target){
                answer++;
            }
            return;
        }
        
        //+로 넘기기
        traverse(depth+1, numbers, target);
        //-로 넘기기, 그리고 돌려놓을필요 없어보이기도 하는데
        numbers[depth] = -numbers[depth];
        traverse(depth+1, numbers, target);
        numbers[depth] = -numbers[depth];
        
        
        
        
    }
    
    public int solution(int[] numbers, int target) {
        
        traverse(0, numbers, target);
        return answer;
    }
}