class Solution {
    
    public int solution(int[][] triangle) {
        int answer = 0;
        
        for(int i=1; i<triangle.length; i++){
            for(int j=0; j<triangle[i].length; j++){
                if(j==0){
                    triangle[i][j] = triangle[i-1][j] + triangle[i][j];
                }   
                else if(j==triangle[i].length-1){
                    triangle[i][j] = triangle[i-1][j-1] + triangle[i][j];
                }
                else{
                    triangle[i][j] = triangle[i-1][j] > triangle[i-1][j-1] ? triangle[i][j] + triangle[i-1][j] : triangle[i][j] + triangle[i-1][j-1];
                }
            }
        }
        
        int i = triangle.length -1;
        for(int j=0; j<triangle[i].length; j++){
            if(triangle[i][j] > answer){
                answer = triangle[i][j];
            }
        }
        return answer;
    }
}