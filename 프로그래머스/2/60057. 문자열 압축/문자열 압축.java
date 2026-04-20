/*
1. 특징 aa -> 2a 이렇게 바뀐다. ccc는 3c이렇게 바뀐다.
2. 2개단위로도 보고면된다. ababab -> 3ab
3. 6개 단위로도 가능하다. abababababab -> 2ababab 이렇게 볼 수도 있다.

즉 어떤 단위로 보던간에 가장 짧게 볼 수 있는 것으로 결정하는것이다.
결국 가장 짧은 길이를 얻는 것이 목표이다

제한 사항
문자열 길이 1~1000
소문자로만


1. 브루트포스
1.1 1을 넣으면 
*/

class Solution {
    
    
    //jari로 2가 나오면 2개 단위로 자르기
    
    /*
    앞에서부터 확인할것이다.
    
    while문으로 한칸 씩 이동
    
    */
    public int get_min_line(String s, int jari){
        int i=0;
        int total=0;
        while (i + jari <= s.length()) {                // (1)
            String pattern = s.substring(i, i + jari);
            int count = 0;
            while (i + 2 * jari <= s.length()
                   && pattern.equals(s.substring(i + jari, i + 2 * jari))) {
                i += jari;
                count++;
            }

            if (count > 0) {
                total += String.valueOf(count + 1).length() + jari;   // (2)
                i += jari;                                            // (3)
            } else {
                total += jari;
                i += jari;
            }
        }
        return total + (s.length() - i); 
    }
    
    public int get(String s){
        int min = Integer.MAX_VALUE;
        for(int i=1; i<=s.length()/2; i++){
            int result = get_min_line(s, i);
            if(result < min){
                min = result;
            }
        }        
        return min;
        
    }
    
    public int solution(String s) {
        
        
        int answer = get(s);
        if(s.length()==1){
            answer = 1;
        }
        return answer;
    }
}