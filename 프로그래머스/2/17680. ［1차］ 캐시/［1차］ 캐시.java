/*

캐시가 0이 들어올 수도 있따.

대소문자구분하지않으니까 소문자가 들어올 수도 있다. 대문자랑다르게

n개 가지고 있다가 캐시 들어오면 삭제하고 맨 뒤에넣고

캐시에 없으면 맨앞에 삭제하고 넣고

*/
import java.util.*;

class Solution {
    
    public LinkedList<String> lists = new LinkedList<>();
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        
        
        for(String city : cities){
            city = city.toLowerCase();
            int size = lists.size();
            if(cacheSize==0){
                answer+=5;
                continue;
            }
            
            //꽉안차있으니까 걍 넣으면 돼.
            if(size < cacheSize){
                
                if(lists.contains(city)){
                    int where = lists.indexOf(city);
                    lists.remove(where);
                    lists.add(city);
                    answer++;
                }
                else{
                    lists.add(city);
                    answer+=5;
                }
                
            }
            else if(size == cacheSize){
                
                if(lists.contains(city)){
                    int where = lists.indexOf(city);
                    lists.remove(where);
                    lists.add(city);
                    answer++;
                }
                else{
                    lists.remove();
                    lists.add(city);
                    answer += 5;                    
                }
                

            }
        
        }
        
        
        
        return answer;
    }
}