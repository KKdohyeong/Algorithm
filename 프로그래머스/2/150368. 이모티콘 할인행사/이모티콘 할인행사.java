import java.util.*;
import java.io.*;
/*
1. 이모티콘 플러스 가입자를 최대한 늘려야함

2. 이모티콘 판매액을 최대한 늘려야해.

n은 100명까지, m은 7개까지 (4개의 할인율 가능)


*/

class Solution {
    
    public int[] discounts = {10, 20, 30, 40};
    public int max_people = 0;
    public int max_money = 0;
    
    public void traverse(int depth, int[][] users, int[] emoticons, int[] emoticon_discounts){
        
        //계산시작
        if(depth == emoticons.length){
            int[] user_buy = new int[users.length];
            int here_people = 0;
            int here_total_money = 0;
            
            Arrays.fill(user_buy, 0);
            
            //이제 돌면서 다 더하자.
            for(int i=0; i<emoticons.length; i++){
                
                for(int j=0; j<users.length; j++){
                    if(emoticon_discounts[i] >= users[j][0]){
                        user_buy[j] += (emoticons[i] / 100) * (100 - emoticon_discounts[i]) ;
                    }
                }
            }
            
            //이제 최종계산하자
            for(int i=0; i<users.length; i++){
                //System.out.printf("%d번쨰 사람은 %d가격을 샀어\n", i, user_buy[i]);
                if(user_buy[i] >= users[i][1]){
                    here_people++;
                }
                else{
                    here_total_money+=user_buy[i];
                }
            }
            
            if(here_people > max_people){
                max_people = here_people;
                max_money = here_total_money;
            }
            else if(here_people == max_people){
                max_money = Math.max(max_money, here_total_money);
            }
            
            
            return;
            
        }
        
        for(int i=0; i<4; i++){
            emoticon_discounts[depth] = discounts[i];
            
            traverse(depth+1, users, emoticons, emoticon_discounts);   
        }
        
        
    }
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] emoticon_discounts = new int[emoticons.length];
        
        
        
        traverse(0, users, emoticons, emoticon_discounts);
        
        
        int[] answer = new int[2];
        answer[0] = max_people;
        answer[1] = max_money;
        
        
        return answer;
    }
}