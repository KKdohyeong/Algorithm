import java.util.*;

class Solution {
    public int solution(int []A, int []B) {
        int array_len = A.length;
        Arrays.sort(A);
        Arrays.sort(B);
        
        int sum = 0;
        
        for(int i=0; i<array_len; i++){
            int a = A[i];
            int b = B[array_len-i-1];
            sum+=a*b;
        }
        return sum;
    }
}
