class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;  
        int result = -1;
        while (left < right) {
            int mid = (left + right) / 2; 
            long totalTime = 0;
            long prevTime = 0;  
            
            for (int i = 0; i < diffs.length; i++) {
                long diff = diffs[i];
                long timeCur = times[i];

                if (mid >= diff) {
                    totalTime += timeCur;
                
                }
                if(mid < diff){
                    totalTime += (diff-mid) * (timeCur + prevTime);
                    totalTime += timeCur;
                }
                prevTime = timeCur;

                if (totalTime > limit) {
                    break;
                }
            }
            
            if (totalTime <= limit) {
                right = mid;
            } 
            else {
                left = mid + 1;
            }
        }
        
        return left;
    }
}
