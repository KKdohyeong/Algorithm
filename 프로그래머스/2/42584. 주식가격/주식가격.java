import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        //stack에 시간, 가격을 넣는다.
        Stack<int[]> stack = new Stack<>();
        int[] answer = new int[prices.length];
        for(int i=0; i<prices.length; i++){
            answer[i] = 0;
        }
                
        for(int i=0; i<prices.length; i++){
            if(stack.isEmpty()){
                stack.push(new int[] {i, prices[i]});
            }
            else{
                //이제 꺼내면서 이 값으로 꺼내지는 놈들을다 꺼내버려서 해야지.
                int price = prices[i];
                int time = i;
                while(!stack.isEmpty()){
                    if(stack.peek()[1] > price){
                        int[] where = stack.pop();
                        answer[where[0]] =time - where[0];
                    }
                    else{
                        break;
                    }
                }
                stack.push(new int[] {i, prices[i]});
            }
        }
        
        while(!stack.isEmpty()){
            int[] where = stack.pop();
            int i = prices.length-1;
            answer[where[0]] = i - where[0];
        }
        
        return answer;
    }
}