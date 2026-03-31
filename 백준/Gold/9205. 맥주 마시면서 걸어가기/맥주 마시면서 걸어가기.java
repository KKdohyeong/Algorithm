import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static class Location{
        public int x;
        public int y;
        public int number;

        public Location(int x, int y, int number ){
            this.x=x;
            this.y=y;
            this.number=number;
        }
    }
    
    public static int t;
    public static int n;
    public static int success;
    public static Location home;
    public static Location final_location;
    public static LinkedList<Location> locations;
    public static int[] visited;


    public static void bfs(){


        Queue<Location> queue = new LinkedList<>();

        queue.add(home);
        visited[0] = 1;
        
        while(!queue.isEmpty()){
            Location location  = queue.poll();
            int x = location.x;
            int y = location.y;
            

            //자 이제 중복없이 새롭게 여기서 시작이야.
            // 내가 이동 가능한 맥주집이 어딧는지 확인해보자. 1000m 이동가능
            Iterator<Location> iterator = locations.iterator();

            int temp = Math.abs(x - final_location.x) + Math.abs(y - final_location.y);

            if(temp <= 1000){
                success = 1;
                return;
            }
            
            while(iterator.hasNext()){
                Location next_location = iterator.next();

                if(visited[next_location.number]==1){
                    iterator.remove();
                    continue;
                }

                int distance = Math.abs(x - next_location.x) + Math.abs(y - next_location.y);
                //이동가능
                if(distance <= 1000){
                    queue.add(next_location);
                    visited[next_location.number]=1;
                    iterator.remove();
                }
                
            }
            
        }

        
        
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());

        t = Integer.parseInt(stk.nextToken());

        for(int i=0; i<t; i++){
            success = 0;
            stk = new StringTokenizer(br.readLine());   
            n = Integer.parseInt(stk.nextToken());
            visited = new int[n+2];
            Arrays.fill(visited, 0);
            locations = new LinkedList<>();

            // 상근집, 편의점, 페스티벌 좌표
            stk = new StringTokenizer(br.readLine());   
   
            int x = Integer.parseInt(stk.nextToken());   
            int y = Integer.parseInt(stk.nextToken());
            home = new Location(x, y, 0);
            
            for(int j=1; j<=n; j++){
                stk = new StringTokenizer(br.readLine());   
                x = Integer.parseInt(stk.nextToken());   
                y = Integer.parseInt(stk.nextToken());
                Location location = new Location(x, y, j);

                locations.add(location);
            }

            
            stk = new StringTokenizer(br.readLine());   
   
            x = Integer.parseInt(stk.nextToken());   
            y = Integer.parseInt(stk.nextToken());
            final_location = new Location(x, y, n+1);



            //이제 bfs하자.
            bfs();

            if(success==0){
                System.out.println("sad");
            }
            else{
                System.out.println("happy");
            }
            
        }

        
    }
}