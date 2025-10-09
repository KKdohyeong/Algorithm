/*
2번 문제니까 별거없이 해야함

100줄이하. 문제특성 + 간단한구현


2~20이하, 1~10이하. 먼가 완탐가능

메뉴 조합 -> 재구성 -> 메뉴

이전에 각 손님들이 주문할 때 가장 많이 함께 주문한 단품 메뉴를 코스 요리로 (이전기록이네? dp인가)

코스 오류 미네뉴는 2개 이상의 단품 메뉴로 해야해
2명 이상의 손님으로 부터 주문된 조합을 후보로 해야함

이건 완탐인듯

answer큥메ㅏㄴ 넣으면 도니ㅣ까

*/

import java.util.*;
import java.io.*;

class Solution {
    
    
    //알파벳순으로 만들어서 정려해서 줘야함.
    public String getString(int[] visited, String order){
        StringBuilder stb = new StringBuilder();
        
        
        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>(){
            @Override
            public int compare(Character o1, Character o2){
                return Integer.compare((int) o1 - 'a', (int) o2 - 'a');
            }
        });
        
        for(int i=0; i<order.length(); i++){
            if(visited[i]==1){
                pq.add(order.charAt(i));
            }
        }
        
        while(!pq.isEmpty()){
            stb.append(pq.poll());
        }
        
        return stb.toString();
        
    }
    
    //조합을 주고 이걸로 course갯수만큼의 조합을 만들어라
    public void traverse(String order, int course, int[] visited, int depth, int start){
        
        
        //course개를 다 선택했으면 string으로 알파벳순으로 잘 만들어서 그걸 해시맵에있는지 확인
        if(depth == course){
            String get = getString(visited, order);
                //System.out.println(get);

            if(lists.containsKey(get)){
                int value = lists.get(get);
                lists.put(get, value+1);
                max = Math.max(max, value+1);
            }
            else{
                lists.put(get, 1);
                max = Math.max(max, 1);
            }
            return;
        }
        
        //선택하자 누구를 고를지
        for(int i=start; i< order.length(); i++){
            //이놈이 방문이 안되었다면 고르고 넘어감
            if(visited[i] ==0){
                //체크하고
                visited[i] = 1;
                traverse(order, course, visited, depth+1, i+1);
                visited[i] = 0;
            }
            //방문안됬으면 걍 넘어가야지 머 있나
        }    
    }
    
    public HashMap<String, Integer> lists ;
    public int max = 0;
    
    public PriorityQueue<String> answers = new PriorityQueue<>(new Comparator<>(){
        @Override
        public int compare(String o1, String o2){
            return o1.compareTo(o2);
        }
    });
    
    public String[] solution(String[] orders, int[] course) {
        
        for(int course_level : course){
            max = 0;
            lists =new HashMap<>();
            for(String order : orders){
                int[] visited = new int[order.length()];
                Arrays.fill(visited, 0);
                System.out.println();
                traverse(order, course_level, visited, 0, 0);
            }
            
            //System.out.println(max);
            
            //이제 순회하면서 최대 크기를 찾고 1이상이면 큐에 넣자
            Set<String> keySet = lists.keySet();
            
            for(String key : keySet){
                int value = lists.get(key);
                if(value > 1 && max == value){
                    answers.add(key);
                }
            }
        }
    String[] answer = new String[answers.size()];
    int i = 0;
    while (!answers.isEmpty()) {
        answer[i++] = answers.poll(); // PriorityQueue라 사전순으로 빠짐
    }
    return answer;
        
        
    }
}