import java.util.*;
import java.io.*;
/*
자카드 유사도 + 다중집합의 원리 생각

교집합 -> 교집합 + 겹치는거는 겹치는 갯수
합집합 -> 합집합 + 겹치는거는 겹치는 그거의 최대갯수


공집합 공집합인경우 1로본다!!
*/

//1. 다중집합 만드는 함수
// 영문만 유효하고 공백, 숫자, 특수문자가 들어오는 두글자짜리는 버린다. isalphabet이런게있나
// tolowercase하고생각하자. 

//교집합 만드는 함수
/*
중복 제거하고

갯수 뽑아오기 (set하고 set갯수뽑기?)
*/



//합집합 만드는 함수
/*
hashmap사용하고 value들 합치면 될듯?
*/



//구하는 함수 (공집합인경우 예외 잘 처리하자)


//소문자인지 확인하는 함수


class Solution {
    

    
    public void make_set(String str, LinkedList<String> list){
        str = str.toLowerCase();
        
        //2글자씩 나누자
        int str_size = str.length();
        
        for(int i=0; i<str_size-1; i++){
            StringBuilder stb = new StringBuilder();
            
            stb.append(str.charAt(i));
            
            stb.append(str.charAt(i+1));
            
            
            String one_str = stb.toString();
            
            if(isAlphabet(one_str)){
                list.add(one_str);
            }
            
        }
        
    }
    
    //0에서 25까지먀ㅕㄴ돼
    public boolean isAlphabet(String str){

        Character a = str.charAt(0);
        int a_int = a - 'a';
        if(a_int <0 || a_int >25){
            return false;
        }
        
        
        if(str.length() >=2){
        Character b = str.charAt(1);
        int b_int = b - 'a';

            
            if(b_int < 0 || b_int > 25){
            return false;
        }

        }
        
        
        return true;
    }
    
    //해시맵부터 만들까
    public void set_hash_map(HashMap<String, Integer> my_map, LinkedList<String> str_set){
        Iterator<String> it = str_set.iterator();
        
        while(it.hasNext()){
            String str = it.next();
            if(my_map.containsKey(str)){
                int value = my_map.get(str);
                my_map.put(str, value+1);
            }
            else{
                my_map.put(str, 1);
            }
        }
        
        
    }
    
    
    public int get_go(){
        
        Iterator<String> it = one_map.keySet().iterator();
        
        int go = 0;
        
        while(it.hasNext()){
            String str_key = it.next();
            int value = one_map.get(str_key);
            
            //가지고있으면 교로 알아보기 가능
            //없으면 0이야
            if(two_map.containsKey(str_key)){
                int value_two = two_map.get(str_key);
                go += Math.min(value, value_two);
            }
            
        }
     
        return go;
    }
    
    
    
    public int get_hab(){
        
        Iterator<String> it = one_map.keySet().iterator();
        
        int hab = 0;
        
        while(it.hasNext()){
            String str_key = it.next();
            int value = one_map.get(str_key);
            
            //가지고있으면
            if(two_map.containsKey(str_key)){
                int value_two = two_map.get(str_key);
                hab += Math.max(value, value_two);
            }
            else{
                hab += value;
            }
            
        }
        
        it = two_map.keySet().iterator();
        while(it.hasNext()){
            String str_key = it.next();
            int value = two_map.get(str_key);
            
            if(!one_map.containsKey(str_key)){
                hab += value;
            }
        }
     
        return hab;
    }
    
    public int calculate(int go, int hab){
        if(go ==0 && hab ==0){
            return 65536;
        }
        System.out.printf("go는 %d hab은 %d이다\n", go, hab);
        
        double temp = (double)go/hab;
        temp = temp *  65536;
        
        return (int)temp;
    }
    
    public LinkedList<String> str_set_one = new LinkedList<>();
    public LinkedList<String> str_set_two = new LinkedList<>();
    public HashMap<String, Integer> one_map = new HashMap<>();
    public HashMap<String, Integer> two_map = new HashMap<>();
    
    public int solution(String str1, String str2) {
        int answer = 0;
        make_set(str1, str_set_one);
        
        make_set(str2, str_set_two);

        set_hash_map(one_map, str_set_one);
        set_hash_map(two_map, str_set_two);
        int go = get_go();
        int hab = get_hab();
        
        
        
        answer = calculate(go, hab);
        
        return answer;
    }
}