import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long how_many = sc.nextLong();
        for(long i=0; i<how_many; i++){
            long total = sc.nextLong();
            HashMap<Long, Integer> map = new HashMap<>();
            for(long j=0; j<total; j++){
                long number = sc.nextLong();
                //1이 있으면 기존 값 + 1저장, 없으면 1 저장
                if(map.containsKey(number)){
                    int cnt = map.get(number);
                    map.replace(number, cnt+1);
                }
                else{
                    map.put(number, 1);
                }
            }
            
            LinkedList<Long> keySet = new LinkedList<>(map.keySet());

            keySet.sort(new Comparator<Long>(){
               @Override
                public int compare(Long o1, Long o2){
                    return Integer.compare(map.get(o2), map.get(o1));
                }
            });

            long best_number = keySet.get(0);
            if(map.get(best_number) > total/2){
                System.out.println(best_number);
            }
            else{
                System.out.println("SYJKGW");
            }
            
        }
    
    }
}