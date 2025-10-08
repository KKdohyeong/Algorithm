/*
1만개의 숫자 질의,

원소 숫자는 10의  15승까지 가능. 질의하는게

(1) 10진수를 2진수로 바꾸는 것 -> 10의15승을 2의 O(1)인듯


모든 수는 2진수로 표현이 가능해

number 1 예외처리하자
*/

import java.util.*;
import java.io.*;

class Solution {
    
    
    //2의 승이면 10의15보다 더 커짐. 이걸 기준으로 하나씩 줄이면서 계산하면 돼.
    public long max_two_count = 60;//50승
    public long max_two = 1;
    public int n;
    public int depth;
    // 배열 50짜리 만들어서 하면 된다.
    
    public void set_max_two(){
        for(int i=0; i<max_two_count; i++){
            max_two *= 2;
        }
    }
    
    public void make_full(){
        Queue<Integer> queue = new LinkedList<>();
        
        int now_size = n;
        int traverse = 2;
        int plus_zero = 0;
        for(int i=1; i<=60; i++){
            if(traverse-1 == n){
                plus_zero = 0;
                depth = i;
                break;
            }
            
            //처음으로 커진 순간, 예를 들어 6개짜리인데 3개, 7개 순회하다 7을 만 순간
            if(traverse-1 > n){
                plus_zero = traverse-1 - n;
                depth = i;
                break;
            }
            traverse *= 2;
        }
        
        //이제 큐에다가 0을 먼저 넣고 
        for(int i=0; i<plus_zero; i++){
            queue.add(0);
        }
        
        for(int i=0; i<n; i++){
            queue.add(arrays[i]);
        }
        n = n + plus_zero;
        
        int size = queue.size();
        for(int i=0; i<size; i++){
            int value = queue.poll();
            arrays[i] = value;
        }
        
    }
    
    //stack에다가 넣고 배열에다가넣자
    public void set_arrays(long number){
        Queue<Integer> queue = new LinkedList<>();
        long traverse = max_two;
        boolean start = false;
        for(int i=0; i<=60; i++){
            if(start){
                if(number>=traverse){
                    queue.add(1);
                    number -= traverse;
                }
                else{
                    queue.add(0);
                }
            }
            else{
                if(number>= traverse){
                    queue.add(1);
                    number -= traverse;
                    start = true;
                }
            }
            traverse = traverse/2;
        }
        
        //이제 stack을 배열에다가 넣자
        n = queue.size();
        
        
        //pop하면 이제 들어감
        
        //System.out.println("시작");
        for(int i=0; i<n; i++){
            arrays[i] = queue.poll();
            //System.out.printf("%d", arrays[i]);
        }
        //System.out.println();
        
    }
    
    public int get_up(int how_many){
        int value = 1;
        for(int i=0; i<how_many; i++){
            value *=2;
        }
        return value;
    }
    
    public int get_answer(){
        int middle = get_up(depth-1)-1;
        if(arrays[middle]==0){
            //System.out.println("중간이안돼");
            return 0;
        }
        for(int i=0; i<depth-1; i++){
            //칸 이동은 2의 i+1승
            int move_block = get_up(i+1); 
            //시작하는 첫 index는 i가 0이면 0, i가 1 이상이면 2의 i승 -1 
            int start_index =get_up(i) - 1;
            //몇 번 이동은? 2의 (i+1)승
            //int move_time = get_up(depth-i-1) - 1;
            
            //몇번 인덱스를 이동해서 부모를 봐야해? 2의 (i-1승)
            int check_up = get_up(i);
            
            //System.out.printf("%d높이의 트리에서 %d번째에 관한 정보입니다. %d에서 시작하고 %d씩 오른쪽으로 이동하며 그리고 부모는 %d칸 이동해서보면 됩니다.\n", depth, i, start_index, move_block, check_up);
            
            int count = 0;
            for(int j=start_index; j<n; j+= move_block){
                //이러면 부모가 무조건 1이어야함 그리고 왼쪽인경우
                if(arrays[j] ==1  && count%2 ==0){
                    
                    if(arrays[j + check_up] ==0){
                        //System.out.printf("%d인덱스가 0인데 %d부모가 0입니다\n", j, j+check_up);
                        return 0;
                    }
                }
                
                if(arrays[j] ==1  && count%2 ==1){
                    
                    if(arrays[j - check_up] ==0){
                        //System.out.printf("%d인덱스가 0인데 %d부모가 0입니다\n", j, j-check_up);

                        return 0;
                    }
                }
                
                count++;
                
            }
            
        }
        return 1;
    }
    
    public int[] arrays;
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        set_max_two();
        int k =0;
        for(long number : numbers){
            arrays = new int[120];
            Arrays.fill(arrays, 0);
            set_arrays(number);
            make_full();
            //System.out.println("시작");
            for(int i=0; i<n; i++){
                //System.out.printf("%d", arrays[i]);
                
            }
            //System.out.println();
            //System.out.printf("높이는 %d이다\n", depth);
            
            
            int a = get_answer();
            System.out.println(a);
            answer[k] = a;
            k++;
        }
        
        
        return answer;
    }
}