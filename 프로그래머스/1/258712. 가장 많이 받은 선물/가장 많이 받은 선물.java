import java.util.*;

class Solution {
    
    public Map<String, Integer> indexMap = new HashMap<>();

    public int solution(String[] friends, String[] gifts) {
        
        int index = 0;
        for (String friend : friends) {
            indexMap.put(friend, index);
            index++;
        }
        
        int[][] arrays = new int[index][index];
        int[] gift_score = new int[index];
        int[] answers = new int[index];
        Arrays.fill(answers, 0);
        Arrays.fill(gift_score, 0);
        
        for (int i = 0; i < index; i++) {
            Arrays.fill(arrays[i], 0);
        }
        
        // a가 b에게 선물을 줬다
        for (String gift : gifts) {
            // ❌ "split(\"\")" → ✅ "split(\" \")" (공백 기준)
            // gift는 "A B" 이런 형태여야 합니다. 즉, 띄어쓰기로 구분
            String[] people = gift.split(" ");
            
            int a = indexMap.get(people[0]);
            int b = indexMap.get(people[1]);
            
            arrays[a][b]++;
            gift_score[a]++;
            gift_score[b]--;
        }
        
        for (int i = 0; i < index; i++) {
            for (int j = i + 1; j < index; j++) {
                // 서로 준 수가 같으면
                if (arrays[i][j] == arrays[j][i]) {
                    if (gift_score[i] > gift_score[j]) {
                        answers[i]++;
                    } else if (gift_score[j] > gift_score[i]) {
                        answers[j]++;
                    }
                }
                // 준 횟수가 더 많은 쪽이 다음달 선물 받음
                else if (arrays[i][j] > arrays[j][i]) {
                    answers[i]++;
                } else {
                    answers[j]++;
                }
            }
        }
        
        int answer = 0;
        for (int i = 0; i < index; i++) {
            answer = Math.max(answer, answers[i]);
        }
        
        return answer;
    }
}
