import java.util.*;
import java.lang.*;
import java.io.*;


/*
특징
1. Go k는 k만큼 보고있는 방향으로 이동하란 말이다.
2. turn left / turn right가 존재한다. 이것은 방향을 바꾸란 말이다. 90도 회전이다.
3. 0은 로봇이 갈 수 있는 지점이다. 1은 로봇이 갈 수 없는 지점이다.
4. 주어질 때 동 : 1 서 : 2 남 : 3 북 : 4 이렇게 주어진다. 이거 바꿔야겠다.
5. M : row N : col
6. 시작 row, col 방향
7. 도착 row, col 바라보는 방향

와 4번 4번 bfs 나눠서 방향바꾸기, 이동하기로 해야겟다. 8번 봐야겠네.

동 1 == 0
서 2 == 2
남 3 == 1
북 4 == 3 으로 바꾸기
dr : 동남서북
dc : 
*/

// The main method must be in a class named "Main".
class Main {
    public static void debug_array(Position[][] array_2d, int m, int n){
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                System.out.printf("%d ", array_2d[i][j].visited);
            }
            System.out.println();
        }
    }

    public static int change_dir(int direction, int value){
        direction+=value;
        if(direction<0){
            direction = 3;
            return direction;
        }
        else if (direction == 4){
            direction=0;
            return direction;
         }
        return direction;
    }

    public static boolean check(int row, int col){
        if(row<0 || col < 0 || row >= M+2 || col >= N+2){
            return false;
        }
        return true;
    }

    
    public static int solution(){
        int[][] visited = new int[M+2][N+2];
        
        for(int row=0; row<M+2; row++){
            for(int col=0; col<N+2; col++){
                if(row==0 || col==0 || row==M+1|| col==N+1){
                    visited[row][col] = 1;
                }
                else{
                    visited[row][col] = array[row][col].visited;
                }
                
            }
        }


        Queue<Person> queue = new LinkedList<>();
        visited[start_person.row][start_person.col] =1;
        queue.add(start_person);
        int answer =0;
        while(!queue.isEmpty()){
            int size = queue.size();
            //System.out.printf("%d째\n", answer);            
            for(int i=0; i<size; i++){
                //먼저 이동부터 
                Person person = queue.poll();
                //System.out.printf("(%d,%d), 방향 %d\n", person.row, person.col, person.direction);
                int dir = person.direction;
                int next_row = person.row + dr[dir];
                int next_col = person.col + dc[dir];
                for(int j=1; j<=3; j++){
                    dir = person.direction;
                    next_row = person.row + dr[dir]*j;
                    next_col = person.col + dc[dir]*j;
                    if(!check(next_row, next_col) || array[next_row][next_col].visited == 1){
                        break;
                    }
                    //최종 도착이면 뭐든 일단 넣어주긴하자.
                    if(next_row == final_person.row && next_col == final_person.col){
                        if(dir == final_person.direction){
                            answer++;
                            return answer;
                        }
                        else{
                            visited[next_row][next_col] = 1;
                            array[next_row][next_col].direction[dir]=1;
                            queue.add(new Person(next_row, next_col, dir));
                        }
                    }
                    else{
                        if(check(next_row, next_col) && (visited[next_row][next_col]== 0 || (array[next_row][next_col].visited==0 && array[next_row][next_col].direction[dir]==0))){
                            visited[next_row][next_col] = 1;
                            array[next_row][next_col].direction[dir]=1;
                            queue.add(new Person(next_row, next_col, dir));
                        }
                    }
                    
                }

                //방향틀자
                int left_dir = change_dir(dir, -1);
                next_row = person.row;
                next_col = person.col;

                if(next_row == final_person.row && next_col == final_person.col){
                    if(left_dir == final_person.direction){
                        answer++;
                        return answer;
                    }
                    else{
                        visited[next_row][next_col] = 1;
                        array[next_row][next_col].direction[left_dir]=1;
                        queue.add(new Person(next_row, next_col, left_dir));
                    }
                }
                else if(check(next_row, next_col) && (visited[next_row][next_col]== 0 || (array[next_row][next_col].visited==0 && array[next_row][next_col].direction[left_dir]==0))){
                        visited[next_row][next_col] = 1;
                        array[next_row][next_col].direction[left_dir]=1;
                        queue.add(new Person(next_row, next_col, left_dir));                    
                }

                
                //방향틀자
                left_dir = change_dir(dir, +1);
                next_row = person.row;
                next_col = person.col;

                if(next_row == final_person.row && next_col == final_person.col){
                    if(left_dir == final_person.direction){
                        answer++;
                        return answer;
                    }
                    else{
                        visited[next_row][next_col] = 1;
                        array[next_row][next_col].direction[left_dir]=1;
                        queue.add(new Person(next_row, next_col, left_dir));
                    }
                }
                else if(check(next_row, next_col) && (visited[next_row][next_col]== 0 || (array[next_row][next_col].visited==0 && array[next_row][next_col].direction[left_dir]==0))){
                        visited[next_row][next_col] = 1;
                        array[next_row][next_col].direction[left_dir]=1;
                        queue.add(new Person(next_row, next_col, left_dir));                    
                }

                
            }
            answer++;
        }

        return -1;

    }


    
    public static class Person{
        public int row;
        public int col;
        public int direction;

        public Person(int row, int col, int direction){
            this.row=row;
            this.col=col;
            this.direction=direction;
        }

        public void debug(){
            System.out.printf("(%d,%d)에 존재 (%d) 방향\n", row, col, direction);
        }
        
    }

    public static class Position{
        public int row;
        public int col;
        public int visited;
        public int[] direction;

        public Position(int row, int col, int visit){
            this.row=row;
            this.col=col;
            this.visited=visit;
            direction = new int[4];
            for(int i=0; i<4; i++){
                direction[i] = 0;
            }
        }
    }

    
    public static Person start_person;
    public static Person final_person;
    public static int M;
    public static int N;
    public static Position[][] array;
    //동남서북
    public static int[] dr = {0, 1, 0, -1};
    public static int[] dc = {1, 0, -1, 0};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        array = new Position[M+2][N+2];

        for(int row=0; row<M+2; row++){
            for(int col=0; col<N+2; col++){
                if(row==0 || col==0 || row==M+1|| col==N+1){
                    array[row][col] = new Position(row, col, 1);
                }
                else{
                    int value = sc.nextInt();
                    array[row][col] = new Position(row, col, value);
                }
                
            }
        }

        //debug_array(array, M+2, N+2);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int dir = sc.nextInt();
        if(dir==1){
            dir = 0;
        }
        else if(dir==3){
            dir =1;
        }
        else if(dir==4){
            dir = 3;
        }

        start_person = new Person(row, col, dir);
        row = sc.nextInt();
        col = sc.nextInt();
        dir = sc.nextInt();
        if(dir==1){
            dir = 0;
        }
        else if(dir==3){
            dir =1;
        }
        else if(dir==4){
            dir = 3;
        }
        final_person = new Person(row, col, dir);
        int answer = solution();
        if(start_person.row == final_person.row && start_person.col == final_person.col && start_person.direction == final_person.direction){
            answer = 0;
        }
        System.out.println(answer);
        
    }


    
}