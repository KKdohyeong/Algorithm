class Solution {
    
    public void quadTree(int[][] arr, int[] answer,  int start_x, int start_y, int finish_x, int finish_y){
        //1. 커다란 하나 check
        //System.out.printf("start_x and start y is (%d, %d) and final is (%d, %d)\n", start_x, start_y, finish_x, finish_y);
        int check = arr[start_x][start_y];
        boolean compression = true;
        for(int i=start_x; i<=finish_x; i++){
            for(int j=start_y; j<=finish_y; j++){
                if(check!=arr[i][j]){
                    compression = false;
                }
            }
        }
        
        //1.1 compression 실행
        if(compression){
            answer[check]++; 
        }
        
        
        //1.2. 안된다면 4분할 하자
        int arr_size = finish_x-start_x;
        if(!compression && arr_size>0){
            int second_x = (start_x + finish_x + 1)/2;
            int second_y = (start_y + finish_y +1) / 2;
            // 왼쪽위
            quadTree(arr, answer, start_x, start_y, second_x-1, second_y-1);
            //오른쪽위
            quadTree(arr, answer, second_x, start_y, finish_x, second_y-1);
            //왼쪽아래
            quadTree(arr, answer, start_x, second_y, second_x-1, finish_y);
            //오른쪽아래
            quadTree(arr, answer, second_x, second_y, finish_x, finish_y);
            
        }
        
        
        return ;
    }
    
    
    public int[] solution(int[][] arr) {
        int[] answer = {0 , 0};
        int finish_x = arr[0].length-1;
        
        quadTree(arr, answer,0, 0, finish_x, finish_x);
        return answer;
    }
}