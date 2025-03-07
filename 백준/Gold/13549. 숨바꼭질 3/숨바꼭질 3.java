import java.util.*;
/*


이것도 dp로 가능할 것 같은데

어디부터 어디까지 최소는 정해질 수 있잖아?


시작점은 100이라 하자
변수 2개 운영해서 위아래로 이동해볼까? 흠... 근데.. 흠..

업데이트 하면 거기 기준으로 분기를 다시 해야하...잖아? 흠...

while
큐에넣기

시작 100 ->

(1) 먼저 곱하기 2를 먼저 쭉 하자
(2)
location (좌표, 시간) 을 넣고

priority queue를 써서 좌표가 같으면 시간이 짧은 순으로 넣자.

좌표가 달라도 시간 짧은 순으로 넣어보자


visited에대한처리? 비슷한걸 할까말까고민
 */



public class Main {


    public static class Location implements Comparable<Location> {
        int location;
        int time;

        public Location(int location, int time){
            this.location=location;
            this.time = time;
        }

        @Override
        public int compareTo(Location o){
            return Integer.compare(this.time, o.time);
        }

    }

    public static int tracking(int start, int finish){
        PriorityQueue<Location> locations = new PriorityQueue<>();

        locations.add(new Location(start, 0));
        times[start] = 0;
        while(!locations.isEmpty()){
            Location loc = locations.poll();
            int loc_x = loc.location;
            int loc_time = loc.time;
            //System.out.printf("좌표 : %d 값 %d 갱신\n", loc_x, loc_time);
            if(loc_x == finish){
                return times[finish];
            }
            //*2도 최신화인지 비교. 근데 계속 할 필요 있나? 1개씩만할까?
            if(loc_x*2<=max && times[loc_x*2] > loc_time){
                locations.add(new Location(loc_x*2, loc_time));
                times[loc_x*2] = loc_time;
            }

            //먼저 왼쪽 이동 최신화인지 비교
            if(loc_x-1 >=0 && times[loc_x-1] > loc_time + 1){
                locations.add(new Location(loc_x-1, loc_time+1));
                times[loc_x-1] = loc_time+1;
            }

            //오른쪽 이동 최신화인지 비교
            if(loc_x+1 <=max && times[loc_x+1] > loc_time + 1){
                locations.add(new Location(loc_x+1, loc_time+1));
                times[loc_x+1] = loc_time+1;
            }

        }
        return times[finish];
    }



    public static int min = 0;
    public static int max = 100000;
    public static int[] times = new int[100001];

    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int finish = sc.nextInt();
        for(int i=0; i<100001; i++){
            times[i] = Integer.MAX_VALUE;
        }

        System.out.println(tracking(start, finish));


    }


}
