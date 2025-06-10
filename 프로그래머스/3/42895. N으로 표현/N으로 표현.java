import java.util.*;
class Solution {
    public int solution(int N, int number) {
        int answer = -1;
        ArrayList<Integer>[] lists = new ArrayList[9];
        for(int i=0; i<9; i++){
            lists[i] = new ArrayList<Integer>();
        }
        
        StringBuilder stb = new StringBuilder();
        
        for(int i=1; i<9; i++){
            //먼저 그냥 기본값 더하기
            stb.append(N);
            Integer value = Integer.parseInt(stb.toString());
            lists[i].add(value);
            
            //돌면서 값 있는지 체크하기
            int start = 1;
            int finish = i-1;
            while(start <=finish){
                for(int j=0; j<lists[start].size(); j++){
                    for(int k=0; k<lists[finish].size(); k++){
                        int a = lists[start].get(j);
                        int b = lists[finish].get(k);
                        lists[i].add(a+b);
                        lists[i].add(a-b);
                        lists[i].add(b-a);
                        lists[i].add(a*b);
                        if(b!=0){
                            lists[i].add(a/b);                        
                        }
                        if(a!=0){
                            lists[i].add(b/a);                            
                        }
                    }
                }   
                start++;
                finish--;
            }
                
                
            for(int j=0; j<lists[i].size(); j++){
                if(lists[i].get(j) == number){
                    answer = i;
                    return answer;
                }
            }
        }
        
        return answer;
    }
}