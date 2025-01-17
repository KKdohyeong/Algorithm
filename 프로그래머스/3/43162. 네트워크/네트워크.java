import java.util.LinkedList;
import java.util.Queue; 

class Solution {
    
    static boolean[] visited;
     
    public int check_visited(int length){
        int start = -1;
        for(int i=0; i<length; i++){
            if(visited[i]==false){
                start = i;
                return start;
            }
        }
        return start;
    }
    
    public void bfs(int start, int [][] computers){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        
        while(!queue.isEmpty()){
            int check = queue.poll();
            visited [check] = true;
            
            for(int j = 0; j<computers.length; j++){
                if(computers[check][j] ==1 && visited[j]==false){
                    queue.add(j);
                    visited[j] = true;
                }
            }
        }
        
    }
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for(int i=0; i<computers.length; i++){
            visited[i] = false;
        }
        int start;
        while(check_visited(computers.length) >= 0){
            start = check_visited(computers.length);
            bfs(start, computers);
            answer++;
        }
        return answer;
    }
}