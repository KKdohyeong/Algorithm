import java.util.*;
import java.io.*;

class Solution {
    
    public boolean a_is_later(Time time_a, Time time_b){
        if(time_a.hour > time_b.hour){
            return true;
        }
        else if(time_a.hour < time_b.hour){
            return false;
        }
        if(time_a.minute > time_b.minute){
            return true;
        }
        return false;
    }
    
    public boolean time_same(Time time_a, Time time_b){
        if(time_a.hour == time_b.hour && time_a.minute == time_b.minute){
            return true;
        }
        return false;
    }
    
    public Time calculate_bus_time(int t, int count){
        int total_minute = count * t;
        int plus_hour = total_minute / 60;
        int plus_minute = total_minute % 60;
        return new Time(9 + plus_hour, 0 + plus_minute);
    }
    
    public Time minus_minute(Time time){
        if(time.minute==0){
            return new Time(time.hour -1, 59);
        }
        return new Time(time.hour, time.minute-1);
    }

    private String fmt(int hour, int minute) {
        return String.format("%02d:%02d", hour, minute);
    }
    private String fmt(Time t) {
        return fmt(t.hour, t.minute);
    }

    public class Bus{
        public int people;
        public Time LateTime;
        public Time SecondLateTime;
        public Time ArriveTime;
        
        public int m;
        
        
        public Bus(int m, Time ArriveTime){
            this.m = m;
            SecondLateTime = null;
            LateTime = null;
            this.ArriveTime = ArriveTime;
            people = 0;
        }
        
        public void add(Time time){
            people++;
            
            if(LateTime==null){
                LateTime = time;
                return;
            }
            
            //먼저 새로 들어온 사람이 가장 마지막에 들어갈 자격이 있나 확인
            if(a_is_later(time, LateTime)){
                //들어온게 더 늦어! 그러면 이제
                SecondLateTime = LateTime;
                LateTime = time;
            }
            
            //일단 새로 들어온 시간이 맨 뒤보다 느리진 않아.
            //그럼 이제 두번째랑 비교해야지?
            //근데 들어온놈이 마지막 시간이면 걍 더 할 필요 없어.
            if(time_same(LateTime, time)){
                return;
            }
            
            if(SecondLateTime == null){
                SecondLateTime = time;
                return;
            }
            //이제 들어온놈이 가장 마지막 시간이 아니니까 두번 쨰로 느린 시간인지 보자
            if(a_is_later(time, SecondLateTime)){
                SecondLateTime = time;
            }
            
            return;
            
            
        }
        
    }
    
    public class Time{
        public int hour;
        public int minute;
        
        public Time(int hour, int minute){
            this.hour = hour;
            this.minute= minute;
        }
    }
    
    //시간이 빠른게 앞에와야해
    public Queue<Time> people = new PriorityQueue<>(new Comparator<Time>(){
        @Override
        public int compare(Time o1, Time o2){
            if(o1.hour != o2.hour){
                return Integer.compare(o1.hour, o2.hour);
            }
            return Integer.compare(o1.minute, o2.minute);
        }
    });
    public Queue<Bus> buses = new PriorityQueue<>(new Comparator<Bus>(){
        @Override
        public int compare(Bus o1, Bus o2){
            //시간이 늦어야해
            if(o1.ArriveTime.hour != o2.ArriveTime.hour){
                return Integer.compare(o2.ArriveTime.hour, o1.ArriveTime.hour);
            }
            return Integer.compare(o2.ArriveTime.minute, o1.ArriveTime.minute);
        }
    });
    
    public String solution(int n, int t, int m, String[] timetable) {
        
        for(String time : timetable){
            String[] time_split = time.split(":");
            people.add(new Time(Integer.parseInt(time_split[0]), Integer.parseInt(time_split[1])));
        }
        
        int now_people =0;
        int bus_count = 0;
        Bus bus = new Bus(m, new Time(9, 0));
        while(!people.isEmpty() && bus_count < n){
            //bus에넣어야지
            Time busTime = calculate_bus_time(t, bus_count);
            
            
            //사람 빼기전에 알맞은 사람을 빼는건지 확인하자. 현재 버스 시간보다 빠른 사람은 못들어가.
            Time canTime = people.peek();
            //사람 시간이 버스보다 늦으면 버스 출발시키고 다음버스로 해야지
            if(a_is_later(canTime, busTime)){
                buses.add(bus);
                bus_count++;
                bus = new Bus(m, calculate_bus_time(t, bus_count)); 
                now_people=0;
                continue;
            }
            
            Time new_people_time = people.poll();
            //System.out.printf("사람 시간 %d:%d\n", new_people_time.hour, new_people_time.minute);
            if(now_people < m){
                bus.add(new_people_time);
                now_people++;
            }
            //꽉찼으면 다음 버스로 보내버려
            if(now_people == m){
                buses.add(bus);
                bus_count++;
                bus = new Bus(m, calculate_bus_time(t, bus_count)); 
                now_people=0;
            }
            
        }
        if(bus.people>0){
            buses.add(bus);
            bus_count++;
            bus = new Bus(m, calculate_bus_time(t, bus_count));
        }
        
        while(bus_count < n){
            buses.add(bus);
            bus_count++;
            bus = new Bus(m, calculate_bus_time(t, bus_count));
        }
        
        Bus my_bus = buses.poll();
        
        

        StringBuilder stb = new StringBuilder();
        //아무도 버스에 안탔어. 이게 마지막 버스인데. 그러면 버스 출발시간이어야함.
        if(my_bus.LateTime==null){
            int hour = my_bus.ArriveTime.hour;
            int minute = my_bus.ArriveTime.minute;

            return fmt(hour, minute);
        }
        
        //버스 둘 다 시간 존재하고 사람 여유가 있어
        if(my_bus.LateTime !=null && my_bus.SecondLateTime != null && my_bus.people<m){
            int hour = my_bus.ArriveTime.hour;
            int minute = my_bus.ArriveTime.minute;

            // === 포맷 적용 ===
            return fmt(hour, minute);
        }
        
        
        //이거는 이제 둘 다 존재하는데 꽉차있어. 그러면 second로 가야지
        if(my_bus.LateTime !=null && my_bus.SecondLateTime != null && my_bus.people == m){
            Time real = minus_minute(my_bus.LateTime);
            int hour = real.hour;
            int minute = real.minute;

            // === 포맷 적용 ===
            return fmt(hour, minute);
        }
        
        //버스 late time에만 전부다 들어갔고 second는 비어있어. 근데 사람은 남아있음
        if(my_bus.LateTime !=null && my_bus.SecondLateTime == null && my_bus.people<m){
            int hour = my_bus.ArriveTime.hour;
            int minute = my_bus.ArriveTime.minute;

            // === 포맷 적용 ===
            return fmt(hour, minute);
        }
        
        
        
        //이제 second가 null인경우 근데 사람 꽉참
        if(my_bus.LateTime !=null && my_bus.SecondLateTime == null && my_bus.people == m){
            Time real = minus_minute(my_bus.LateTime);
            int hour = real.hour;
            int minute = real.minute;

            // === 포맷 적용 ===
            return fmt(hour, minute);
        }
        
        String answer = stb.toString();
        return answer;
    }
}
