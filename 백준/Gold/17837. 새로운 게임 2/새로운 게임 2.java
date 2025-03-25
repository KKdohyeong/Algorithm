import java.util.*;


/*
특징
1. N*N체스판
1.1 각 체스 판은 하양, 빨강, 파랑으로 되어있다.
하양 : 말들이 오면 맨 위에 올려놓음
빨강 : (온 말들만!! 순서가 정반대로 된다!)
파랑 : 이동 방향 반대로 전환하고. 1칸 이동시킨다. (그런데 그 곳도 파란색이면 가만히 && 벗어나지도 않는다., 외부도 파란색으로 인식한다.)
2. K개의 말
3. 말 위에 말 가능 (말 3개도 가능)
4. 말은 1~K번 있음
5. 말의 이동방향 정해져있음
6. 턴 1번은 말 1번~K번 순서대로 이동
6-1. 한 말 이동할 때 위의 말도 같이 이동
6-2. 말이 4개 쌓이는 그 순간 바로 게임 종료


자료구조
1. class chess_place (위치,색깔, horse_숫자 linkedlist)
2. arraylist 체스 칸 모아둔 것(1번부터 K번 순서대로 순환하기위해)
3. class horse(방향, dx, dy, 위치)
4. chess_place[][] 이 체스판 배열도 만들어야지 chess_places

함수
1.

순서
1. 초기설정
2. 모든 말들을 이동시킨다.
2.1 말들을 순서대로 이동시킨다.
2.2 하나의 말이




 */


class Main
{
    public static int N;
    public static int K;

    //1. class chess_place (위치,색깔, horse_숫자 linkedlist)
    public static class ChessPlace{
        public int row;
        public int col;
        public int color;
        public ArrayList<Horse> horse_list = new ArrayList<Horse>();

        public ChessPlace(int row, int col, int color){
            this.row=row;
            this.col=col;
            this.color=color;
        }

    }

//2. arraylist 체스 칸 모아둔 것(1번부터 K번 순서대로 순환하기위해)

    public static ArrayList<Horse> horses;

    //3. class horse(방향, dx, dy, 위치)
    public static class Horse{
        public int row;
        public int col;
        public int dr;
        public int dc;
        public int number;

        public Horse(int row, int col, int dr, int dc, int number){
            this.row=row;
            this.col=col;
            this.dr=dr;
            this.dc=dc;
            this.number=number;
        }
    }

    //4. chess_place[][] 이 체스판 배열도 만들어야지 chess_places
    public static ChessPlace[][] chessPlaces;



    /*
    2-1. 말들을 순서대로 이동시킨다.
    2-2. 말을 이동시킨다.
    (1) 말을 리스트에서 꺼낸다
    (2) 말이 있는 칸에 이동해서 본인 위에 올라탄 말들을꺼낸다
    (3) 자신의 이동 위치에 있는 칸의 색깔을 확인한다
    1. 하얀색일 경우 이동방향으로 본인 위에 올라탄 말들과 같이 이동한다.
    2. 빨간색일 경우 이동방향으로 본인 위에 올라탄 말들과 순서를 바꾼 후 이동한다.
    3. 파란색일 경우 이동방향을 반대로 설정 후 해당 방향으로 다시 이동을 시도한다.
    3-1 만약 반대 방향도 파란색이라면 가만히 있는다.
    (4) 말 이동한 칸의 말이 4개 이상이라면 그즉시 멈춘다.
     */
    public static boolean move_Horses(){

        //말들을 순서대로 이동시킨다. (1) 말 꺼내기

        for(Horse horse:horses){

            int row = horse.row;
            int next_row = horse.row+horse.dr;
            int next_col = horse.col+horse.dc;
            int col = horse.col;
            int start = chessPlaces[row][col].horse_list.indexOf(horse);
            int finish = chessPlaces[row][col].horse_list.size()-1;
            LinkedList<Horse> onHorses = new LinkedList<>();
            //System.out.printf("(%d,%d) 말이 (%d,%d)로 이동\n", row, col, next_row, next_col);
            // 본인 위의 말 꺼내서 순서대로 저장하기

            int next_color = chessPlaces[next_row][next_col].color;
           // System.out.printf("(%d,%d) color : %d\n", next_row, next_col, next_color);

            //다음이 파랑이면 방향을 바꾸고 다시 시도한다.
            if(next_color ==2){
                //horse의 방향을 반대로 바꾼다.
                horse.dr = -horse.dr;
                horse.dc = -horse.dc;

                //반대의 색깔을 확인한다.
                next_row = horse.row+horse.dr;
                next_col = horse.col+horse.dc;
                next_color =  chessPlaces[next_row][next_col].color;
            }



            //다음이 흰색이면
            if(next_color ==0){
                for(int i = start; i<=finish; i++){
                    //System.out.printf("%d %d\n", start, finish);
                    if(start>=0){
                        Horse addhorse = chessPlaces[row][col].horse_list.get(start);
                        chessPlaces[row][col].horse_list.remove(start);
                        onHorses.add(addhorse);
                        //System.out.printf("위에 %d말 존재\n", addhorse.number);
                    }
                }

                //다음 칸으로 같이 이동한다. -> 말들위 위치 상태도 바꿔야한다.
                for(int i = 0; i<onHorses.size(); i++){
                    Horse addhorse = onHorses.get(i);
                    addhorse.row = next_row;
                    addhorse.col = next_col;
                    //System.out.printf("%d번 말 (%d,%d)에 추가\n", addhorse.number, next_row, next_col);
                    chessPlaces[next_row][next_col].horse_list.add(addhorse);
                }

                //이동 후에 4개 이상이면 멈춰야한다.
                if(chessPlaces[next_row][next_col].horse_list.size()>=4){
                    return true;
                }
            }

            //다음이 빨강이면
            if(next_color ==1){

                //기존의 체스판에서 본인 말 + 그 위를 가져온다. 그리고 기존것에서 삭제한다.
                for(int i = finish; i>=start; i--){
                    if(start>=0){
                        Horse addhorse = chessPlaces[row][col].horse_list.get(i);
                        chessPlaces[row][col].horse_list.remove(i);
                        onHorses.add(addhorse);
                        //System.out.printf("위에 %d말 존재\n", addhorse.number);

                    }
                }
                //다음 칸으로 같이 이동한다. -> 말들위 위치 상태도 바꿔야한다.
                for(int i = 0; i<onHorses.size(); i++){
                    Horse addhorse = onHorses.get(i);
                    addhorse.row = next_row;
                    addhorse.col = next_col;
                    chessPlaces[next_row][next_col].horse_list.add(addhorse);
                }

                //이동 후에 4개 이상이면 멈춰야한다.
                if(chessPlaces[next_row][next_col].horse_list.size()>=4){
                    return true;
                }

            }

            //암것도안한다.
            if(next_color==2){

            }

            //다음 칸 색깔 확인하기

        }

        return false;
    }


    public static void main(String args[]) throws Exception
    {
        //1. 초기설정
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        //체스판설정
        chessPlaces = new ChessPlace[N+2][N+2];
        for(int row=0; row<=N+1; row++){
            for(int column=0; column<=N+1; column++){
                if(row==0 || row==N+1 || column==0 || column == N+1){
                    int color = 2;
                    chessPlaces[row][column] = new ChessPlace(row, column, color);
                }
                else{
                    int color = sc.nextInt();
                    chessPlaces[row][column] = new ChessPlace(row, column, color);
                }
            }
        }

        //말 설정
        horses = new ArrayList<>();
        for(int i=1; i<=K; i++){
            int row = sc.nextInt();
            int col = sc.nextInt();
            int direction = sc.nextInt();

            Horse horse = null;//오른쪽 ->이니까 dr은 이동 0 dc는 오른쪽으로 하나 +1
            if(direction == 1){
                horse = new Horse(row, col, 0, 1, i);
                horses.add(horse);
            }

            //왼쪽
            if(direction ==2){
                horse = new Horse(row, col, 0, -1, i);
                horses.add(horse);
            }

            //위로 이동
            if(direction ==3){
                horse = new Horse(row, col, -1, 0, i);
                horses.add(horse);
            }
            //아래로 이동
            if(direction ==4){
                horse = new Horse(row, col, 1, 0, i);
                horses.add(horse);
            }
            chessPlaces[row][col].horse_list.add(horse);

        }

        int k = 0;
        while(true){
            boolean out_flag = move_Horses();
            k++;
            if(out_flag==true){
                System.out.println(k);
                break;
            }
            if(k>1000){
                System.out.println(-1);
                break;
            }

        }



    }
}