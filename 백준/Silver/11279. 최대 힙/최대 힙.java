
import java.util.*;



public class Main
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int size = sc.nextInt();

        for(int i=0; i<size; i++){
            int num = sc.nextInt();
            if(num ==0){
                if(maxHeap.isEmpty()){
                    System.out.println(0);
                }
                else{
                    System.out.println(maxHeap.poll());
                }
            }
            else{
                maxHeap.add(num);
            }
        }
    }
}
