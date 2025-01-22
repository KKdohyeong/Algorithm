class Solution {
    public int solution(int[][] board, int[][] skill) {
        int N = board.length;
        int M = board[0].length;
        int answer = 0;

        int[][] prefix_sum = new int[N+1][M+1];
        
        for(int i=0; i<=N; i++){
            for(int j=0; j<=M; j++){
                prefix_sum[i][j] = 0;
            }
        }
        
        for(int[] attack : skill){
            int type = attack[0];
            int r1 = attack[1];
            int c1 = attack[2];
            int r2 = attack[3];
            int c2 = attack[4];
            int degree = attack[5];
            
            
            //공격
            if(type == 1){
                prefix_sum[r1][c1] += -degree;
                prefix_sum[r2+1][c1] += degree;
                prefix_sum[r1][c2+1] += degree;
                prefix_sum[r2+1][c2+1] += -degree;
            }
            
            //수비
            if(type==2){
                prefix_sum[r1][c1] += degree;
                prefix_sum[r2+1][c1] += -degree;
                prefix_sum[r1][c2+1] += -degree;
                prefix_sum[r2+1][c2+1] += degree;
            }
            
        }
        
        for(int i=0; i<N; i++){
            for(int j=1; j<M; j++){
                prefix_sum[i][j] += prefix_sum[i][j-1];
            }
        }
        
        
        for(int i=1; i<N; i++){
            for(int j=0; j<M; j++){
                prefix_sum[i][j] += prefix_sum[i-1][j];
            }
        }
        
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                board[i][j] += prefix_sum[i][j];
                if(board[i][j] >0){
                    answer++;
                }
            }
        }
        
        
        
        
        
        
        
        return answer;
    }
}