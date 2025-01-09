class Solution {
    
    int calculatePlus(int[] numbers, int target, int depth, int sum){
        int result = 0;
        if(depth<=numbers.length){
            sum += numbers[depth-1];
        }
        
        if(depth==numbers.length){
            //System.out.printf("합계는 %d\n", sum);
            if(sum==target){
                System.out.println("다 돼");
                return 1;
            }
            if(sum!=target){
                return 0;
            }
        }
        
        result += calculatePlus(numbers, target, depth+1, sum);
        result += calculateMinus(numbers, target, depth+1, sum);
        
        return result;
    }
    
    int calculateMinus(int[] numbers, int target, int depth, int sum){
        int result = 0;
        if(depth<=numbers.length){
            sum -= numbers[depth-1];
        }
        
        if(depth==numbers.length){
            if(sum==target){
                return 1;
            }
            if(sum!=target){
                return 0;
            }
        }
        
        result += calculatePlus(numbers, target, depth+1, sum);
        result += calculateMinus(numbers, target, depth+1, sum);
        //System.out.printf("result is %d and sum is %d\n", result, sum);
        return result;
    }
    
    public int solution(int[] numbers, int target) {
        int answer = 0;
        int depth = 1;
        answer += calculatePlus(numbers, target, 1, 0);
        answer += calculateMinus(numbers, target, 1, 0);
        return answer;
    }
    
}