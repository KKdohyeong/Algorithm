import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".


class Main {

    public static class Command{
        public int time;
        public int type;
        public String command;

        public Command(int time, int type, String command){
            this.time = time;
            this.type = type;
            this.command = command;
        }
        
    }

    //이전 index로 시작 불러야함 꼭!
    public static void add_undo(int now_time, int back_time, int index){
        for(int i=index; i>=0; i--){
            if (commands[i].time>=now_time-back_time){
                undo_where[index+1].add(i);
            }
        }
    }

    // type이 0이면 type이고 type이 1이라면 undo
    public static Command[] commands = new Command[51];
    public static LinkedList<Integer>[] undo_where = new LinkedList[51];
    public static int[] do_command = new int[51];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0; i<51; i++){
            undo_where[i] = new LinkedList<>();
            do_command[i] = 1;
        }
        for(int i=0; i<n; i++){
            String type = sc.next();
            String command = sc.next();
            int time = sc.nextInt();
            if(type.equals("type")){
                commands[i] = new Command(time, 0, command);
                //System.out.printf("type %s %d\n", command, time);
            }
            else if(type.equals("undo")){
                commands[i] = new Command(time, 1, command);
                //System.out.printf("undo %s %d\n", command, time);
                
                if(i>0){
                    add_undo(time, Integer.parseInt(command), i-1);                
                }
            }
        }


        //이제 n개를 꺼내서 봐야지 누가 유효한 커맨드인지
        
        for(int i=n-1; i>=0; i--){
            if(commands[i].type == 1 && do_command[i] == 1){
                for(Integer cannot_index : undo_where[i]){
                    //System.out.printf("%d 인덱스에서는 %d인덱스를 undo한다\n", i, cannot_index);
                    if(do_command[cannot_index] == 0){
                        do_command[cannot_index] = 1;
                    }
                    else{
                        do_command[cannot_index] = 0;
                    }
                }
            }
        }

        for(int i=0; i<n; i++){
            if(do_command[i] ==1 && commands[i].type == 0){
            System.out.printf("%s", commands[i].command);
            }
        }
        
        
    }
}