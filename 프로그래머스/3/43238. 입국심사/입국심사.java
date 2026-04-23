import java.util.*;

class Solution {
    
    // time이 내가 주어진 시간. n은 총 내가 처리해야하는 사람.
    public boolean check(long time, long n, int[] times){
        long total = 0;
        
        for(int t : times){
            total += (time / t);
            
            // 핵심 방어막: 처리가능한 사람이 n명을 채우면 바로 true 반환!
            // 불필요한 연산을 줄이고, total이 long 범위를 초과하는 것을 막습니다.
            if(total >= n){
                return true;
            }
        }
        return false; // 끝까지 돌았는데도 n명을 못 채우면 false
    }
    
    public long solution(int n, int[] times) {
        long start = 1; // 시간은 최소 1분부터 시작
        
        // finish를 덮어놓고 Long.MAX_VALUE로 잡기보다, 가장 최악의 경우로 잡는 것이 안전합니다.
        // 최악의 경우 = 가장 심사가 오래 걸리는 심사관 혼자서 n명을 전부 심사할 때
        long maxTime = 0;
        for(int t : times){
            if(t > maxTime) maxTime = t;
        }
        long finish = maxTime * n; 
        
        while(start < finish){
            // 오버플로우를 원천 차단하는 안전한 이분 탐색 중앙값 계산법
            long middle = start + (finish - start) / 2; 
            
            // 만족하는 경우 (시간이 넉넉하거나 딱 맞음) -> 시간을 더 줄여본다
            if(check(middle, n, times)){
                finish = middle;   
            }
            // 만족하지 않는 경우 (시간이 부족함) -> 시간을 늘린다
            else{
                start = middle + 1;
            }
        }
        
        return start;
    }
}