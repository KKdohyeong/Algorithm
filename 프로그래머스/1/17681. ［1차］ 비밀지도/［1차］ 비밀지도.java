/*


*/

import java.util.*;
import java.io.*;

class Solution {
    
    
    public void set_n_max(int n){
        for(int i=0; i<n-1; i++){
            n_max *= 2;
        }
    }
    
    public int[] get_value(int value, int n){
        int[] result = new int[n];
        int here_n_max = n_max;
        for(int i=n-1; i>=0; i--){
            if(value >= here_n_max){
                result[i] = 1;
                value -= here_n_max;
            }
            else{
                result[i] = 0;
            }
            here_n_max /= 2;
        }
        
        return result;
    }
    
    public void set(int[] array, int n){
        for(int i=0; i<n; i++){
            int[] result_one_line = get_value(array[i], n);
            
            for(int j=0; j<n; j++){
                if(result_one_line[j] == 1){
                    arrays[i][j] = 1;
                }
            }
            
        }
    }
    
    public int n_max = 1;
    public int[][] arrays; 
    
    
    
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        set_n_max(n);
        arrays = new int[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(arrays[i], 0);
        }
        
        set(arr1, n);
        set(arr2, n);
        
        
        for(int i=0; i<n; i++){
            StringBuilder stb = new StringBuilder();
            for(int j=n-1; j>=0; j--){
                if(arrays[i][j] ==0){
                    stb.append(" ");
                }
                else{
                    stb.append("#");
                }
            }
            answer[i] = stb.toString();
        }
        
        return answer;
    }
}