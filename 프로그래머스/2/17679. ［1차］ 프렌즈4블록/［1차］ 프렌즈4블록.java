import java.util.*;
class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] table = new char[m][n];
        for(int i=0;i<m;i++){
            table[i]=board[m-i-1].toCharArray();
        }
        while(true){
        boolean flag=true;
        boolean[][] check =new boolean[m][n];
        for(int i=0;i<m-1;i++){
            for(int j=0;j<n-1;j++){
                if(table[i][j]=='-'){
                    continue;
                }
                char a =table[i][j];
                if(table[i][j+1]==a&&table[i+1][j]==a&&table[i+1][j+1]==a){
                    check[i][j]=true;
                    check[i][j+1]=true;
                    check[i+1][j]=true;
                    check[i+1][j+1]=true;
                    flag=false;
                }
            }
        }
            
        if(flag){
            break;
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(check[i][j]){
                    table[i][j]='-';
                    answer++;
                }
            }
        }
        //블록 내리기 (윗블록과 스왑하기)
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(table[i][j]=='-'){
                   for(int k=i;k<m;k++){
                       if(table[k][j]=='-'){
                           continue;
                       }
                       table[i][j]=table[k][j];
                       table[k][j]='-';
                       break;
                   }
                }
            }
        }
    }   
        return answer;
    }
}
