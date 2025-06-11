import java.util.*;
/*
1. 완전 무식법은 돌면서 1개 차이인거 확인해서 하는법
2. 노드를 만들어서 스트링별로 이동하는거 하는거

1번인듯?
visited string
string.equals()



*/

class Solution {
    public int[] visited;
    public int min= Integer.MAX_VALUE;
    
    public boolean check_diff(String a, String b){
        int count = 0;
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                count++;
            }
        }
        if(count==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    
    public void dfs(String begin, String target, int depth, String[] words){
        if(begin.equals(target) && min > depth){
            min = depth;
        }
        for(int i=0; i < words.length; i++){
            //1개 차이고 방문을 안했으면 dfs로 이동하장 구래!!!!~~~~~~~
            if(visited[i]==0 && check_diff(begin, words[i])){
                //System.out.printf("%s %s 비교 depth는 %d\n", begin, words[i], depth);
                visited[i] = 1;
                
                dfs(words[i], target, depth+1, words);
                
                visited[i] = 0;
                
            } 
            
        }
        
        
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visited = new int[words.length];
        for(int i=0; i<words.length; i++){
            visited[i] = 0;
        }
        
        dfs(begin, target, 0, words);
        if(min==Integer.MAX_VALUE){
            return 0;
        }
        else{
            return min;
        }
        
    }
}