import java.util.*;

class Solution {
    
    // 가능한 할인율 배열
    static int[] DISCOUNT_RATES = {10, 20, 30, 40};
    // 각 이모티콘에 적용된 할인율을 저장할 배열
    static int[] currentDiscounts;
    // 최종 결과를 저장할 변수
    static int maxSubscribers = 0;
    static int maxSales = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        currentDiscounts = new int[emoticons.length];
        
        // DFS 시작 (0번째 이모티콘부터 할인율 결정 시작)
        dfs(0, users, emoticons);
        
        int[] answer = {maxSubscribers, maxSales};
        return answer;
    }
    
    /**
     * @param depth 현재 할인율을 결정할 이모티콘의 인덱스
     */
    public void dfs(int depth, int[][] users, int[] emoticons) {
        // [종료 조건] 모든 이모티콘의 할인율이 결정되었을 때
        if (depth == emoticons.length) {
            int currentSubscribers = 0;
            int currentSales = 0;
            
            // 모든 유저에 대해 구매 비용 계산
            for (int[] user : users) {
                int userMinDiscount = user[0];
                int userMaxCost = user[1];
                int totalCost = 0;
                
                // 각 이모티콘을 살지 말지 결정
                for (int i = 0; i < emoticons.length; i++) {
                    if (currentDiscounts[i] >= userMinDiscount) {
                        totalCost += emoticons[i] * (100 - currentDiscounts[i]) / 100;
                    }
                }
                
                // 유저의 기준에 따라 플러스 가입 또는 구매 확정
                if (totalCost >= userMaxCost) {
                    currentSubscribers++;
                } else {
                    currentSales += totalCost;
                }
            }
            
            // 최종 결과 업데이트
            if (currentSubscribers > maxSubscribers) {
                maxSubscribers = currentSubscribers;
                maxSales = currentSales;
            } else if (currentSubscribers == maxSubscribers) {
                maxSales = Math.max(maxSales, currentSales);
            }
            
            return; // 탐색 종료
        }
        
        // [재귀] 현재 이모티콘(depth)의 할인율을 바꿔가며 다음 단계로 진행
        for (int rate : DISCOUNT_RATES) {
            currentDiscounts[depth] = rate;
            dfs(depth + 1, users, emoticons);
        }
    }
}