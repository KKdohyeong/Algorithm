import java.util.ArrayList;
import java.util.Stack;

class Solution {
    
    public int[] solution(int[] numbers) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[numbers.length];        
        stack.push(0);
        int temp;
        for(int i=1; i<numbers.length; i++){
            temp = numbers[i];
            while(!stack.empty() && numbers[stack.peek()] < temp){
                answer[stack.pop()] = temp;
            }
            stack.push(i);
        }
        while(!stack.empty()){
            answer[stack.pop()] = -1;
        }
        return answer;
    }
}