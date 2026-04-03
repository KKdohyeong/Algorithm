import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
/*
1. 

*/
class Main {

    public static class Egg{
        public int hp;
        public int weight;

        public Egg(int hp, int weight){
            this.hp = hp;
            this.weight = weight;
        }
    }

    public static int N;
    public static int[] visited;
    public static Egg[] arrays;
    public static int answer = 0;

    public static void break_egg(int start, int finish){
        arrays[start].hp = arrays[start].hp - arrays[finish].weight;
        arrays[finish].hp = arrays[finish].hp - arrays[start].weight;
        return;
    }

    public static void return_egg(int start, int finish){
        arrays[start].hp = arrays[start].hp + arrays[finish].weight;
        arrays[finish].hp = arrays[finish].hp + arrays[start].weight;
    }

    
    //깨버리는 2번과정
    public static void daik(int start){
        //근데 만약 가장 오른쪽이면 그만둬

        if(start ==  N){
            int here_answer = 0;
            for(int i=0; i<N; i++){
                //System.out.printf("%d는 피가 %d이다\n", i, arrays[i].hp);
                if(arrays[i].hp <= 0){
                    here_answer++;
                }
            }
            //System.out.printf("개수는 %d개\n", here_answer);

            answer = Math.max(answer, here_answer);
            return;
        }


        //이제 하나를 선택해서 깨버려. 근데 손에 든게 깨진거면 다음거를 선택해
        if(arrays[start].hp <=0){
            daik(start+1);

            //다시 돌아오는거 진행해야하나? 고민해보자
        }
        else{
            int yes_egg = 0;
            for(int i=0; i<N; i++){
                if(i==start){
                    continue;
                }

                if(arrays[i].hp <=0){
                    continue;
                }
                yes_egg=1;
                //이제 i번은 당첨. 깨버리기 시도 가능
                break_egg(start, i);
                //System.out.printf("%d번으로 %d깨버림\n", start, i);

                daik(start+1);

                return_egg(start, i);                
                
            }
            if(yes_egg==0){
                daik(start+1);
            }
            
        }

        //이제 손에든게 깨지지않았으니 2번을 진행해
        
    }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        visited = new int[N];
        arrays = new Egg[N];


        Arrays.fill(visited, 0);
        
        for(int i=0; i<N; i++){
            int hp = sc.nextInt();
            int weight = sc.nextInt();

            Egg egg = new Egg(hp, weight);
            arrays[i] = egg;   
        }

        daik(0);
        System.out.println(answer);

        







        
    }
}