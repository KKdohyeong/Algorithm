import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(int value : people){
            list.add(value);
        }
        
        Collections.sort(list, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                return Integer.compare(o1, o2);
            }
        });
        
        
        
        int start = 0;
        int finish = people.length-1;
        //같이 이동하자
        while(start <= finish){
            //finish를 먼저보자
            //혼자 타야하는 경우
            if(list.get(finish) + list.get(start) > limit){
                answer++;
                finish--;
                continue;
            }
            else{
                start++;
                finish--;
                answer++;
                continue;
            }
            
        }
        
        return answer;
    }
}