
import java.util.*;



public class Main {


    static int[][] bombs;

    public static void main(String[] args) {
        Scanner sc=  new Scanner(System.in);
        int depth = sc.nextInt();
        int width = sc.nextInt();
        int second = sc.nextInt();
        Queue<int[]> queue = new LinkedList<>();
        sc.nextLine();


        bombs = new int[depth][width];

        for(int i=0; i<depth; i++){
            String line = sc.nextLine();

            for(int j=0; j<width; j++){
                char ch = line.charAt(j);

                if(ch=='.'){
                    bombs[i][j] = 0;
                }
                else if(ch=='O'){
                    bombs[i][j] = 1;
                    queue.add(new int[]{i,j});
                }
            }
        }


        for(int i=0; i<second; i++){
            if(i==0){
                //첫 0초~1초에서 하는 일
                //없음, 폭탄 추가는 맨 처음에 했음
            }
            else if(i%2==1){
                //첫 1초~2초에서 하는 일 // 폭탄 전체에 설치
                for(int j=0; j<depth; j++){
                    for(int k=0; k<width; k++){
                        bombs[j][k] = 1;
                    }
                }
            }
            else if(i%2==0){
                // 폭탄 터트리고 남은 곳 bomb에 add하기
                while(!queue.isEmpty()){
                    int[] cur = queue.poll();
                    //폭탄의 앞뒤 뭐 등등 다 가능하게 만들기
                    int y = cur[0];
                    int x = cur[1];
                    bombs[y][x] = 0;

                    if(y-1>=0){
                        bombs[y-1][x] = 0;
                    }
                    if(x-1>=0){
                        bombs[y][x-1] = 0;
                    }
                    if(y+1<depth){
                        bombs[y+1][x] = 0;
                    }
                    if(x+1<width){
                        bombs[y][x+1] = 0;
                    }

                }

                for(int j=0; j<depth; j++){
                    for(int k=0; k<width; k++){
                        if(bombs[j][k] == 1){
                            queue.add(new int[]{j,k});
                     //       System.out.printf("폭탄 설치 [%d,%d]\n", j, k);
                        }
                    }
                }

            }



        }


        for(int i=0; i<depth; i++){
            for(int j=0; j<width; j++){
                if(bombs[i][j] == 1){
                    System.out.print('O');
                }
                else{
                    System.out.print('.');
                }
            }
            System.out.println();
        }




    }
}
