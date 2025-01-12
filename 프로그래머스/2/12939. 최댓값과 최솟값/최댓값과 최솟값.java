import java.util.ArrayList;

class Solution {

    public String solution(String s) {
        String[] int_arr = s.split(" ");
        ArrayList<Integer> arr_list = new ArrayList<>();
        for(int i=0; i<int_arr.length; i++){
            arr_list.add(Integer.parseInt(int_arr[i]));
        }
        int max = arr_list.get(0);
        int min = arr_list.get(0);
        
        for(int i=0; i<arr_list.size(); i++){
            if(arr_list.get(i) > max){
                max = arr_list.get(i);
            }
            if(arr_list.get(i) < min){
                min = arr_list.get(i);
            }
        }
        String answer = min + " " + max;       
        return answer;
    }
}