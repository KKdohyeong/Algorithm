import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {


    public static class Building{
        public int location;
        public int red_time;
        public int green_time;

        public Building(int location, int red_time, int green_time){
            this.location = location;
            this.red_time = red_time;
            this.green_time = green_time;
        }

        
    }    
    public static int N;

    public static int L;
    
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);


        Queue<Building> Buildings = new LinkedList<>();
        
        N = sc.nextInt();
        L = sc.nextInt();

        for(int i=0; i<N; i++){
            int location  = sc.nextInt();
            int red_time = sc.nextInt();
            int green_time = sc.nextInt();

            Buildings.add(new Building(location, red_time, green_time));

            
        }


        int time = 0;
        int location  =0;

        while(!Buildings.isEmpty()){
            Building building = Buildings.poll();

            int red_green = building.red_time + building.green_time;
            //먼저 거기까지 도달하자
            time += building.location  - location;

            location = building.location;

            int left_time = time % red_green;

            //아직 빨강이 남아있단말임.
            if(left_time - building.red_time<0){
                time += building.red_time - left_time;
            }
            else{
                
            }
            
        }

        //이제 남은거리 마무리
        if(location < L){
            time += L - location;
        }

        System.out.println(time);
    }
}