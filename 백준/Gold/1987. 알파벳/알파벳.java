
import java.util.*;

public class Main {

    public static int x_size;
    public static int y_size;
    public static LinkedList<Character> alphabet_list = new LinkedList<>();
    public static boolean[] alphabet_bool = new boolean[26];
    public static Character[][] values;
    public static int[][] visited;
    public static int[] dx = new int[]{0, 1, 0, -1};
    public static int[] dy = new int[]{-1, 0, 1, 0};
    public static int max = 0;


    public static void dfs(int count, int y, int x){
        //System.out.printf("[%d][%d] 호출되었음 count는 %d\n", y, x, count);
        if(count>max){
            max = count;
        }
        Character temp_c = values[y][x];


        for(int i=0; i<4; i++){
            int new_y = y + dy[i];
            int new_x = x + dx[i];


            //내가 순회하는 곳들이 중복 알파벳이 없으면 리스트에 추가하고 해보자. 그리고 다시 돌려놓는거야.
            if(new_x >=0 && new_y >= 0 && new_x < x_size && new_y < y_size && visited[new_y][new_x]==0){
                //중복인지 확인
                Character test_c = values[new_y][new_x];

                //알파벳 체크하는 것. 이것을 boolean으로 바꾸자
                int index = test_c - 'A';
                if(alphabet_bool[index] == false){
                    visited[new_y][new_x] = 1;
                    alphabet_bool[index] = true;
                    dfs(count+1, new_y, new_x);
                    visited[new_y][new_x]=0;
                    alphabet_bool[index] = false;
                }

            }
        }

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int x=  sc.nextInt();
        x_size = x;
        y_size = y;
        values = new Character[y_size][x_size];
        visited = new int[y_size][x_size];
        for(int i=0; i<26; i++){
            alphabet_bool[i] = false;
        }
        sc.nextLine();


        for(int i=0; i<y; i++){
            String str =  sc.nextLine();
            for(int j=0; j<x; j++){
                Character c = str.charAt(j);
                values[i][j] = c;
                visited[i][j] = 0;
            }
        }
        int index = values[0][0] - 'A';
        alphabet_bool[index] = true;
        dfs(0, 0, 0);
        System.out.println(max+1);
    }
}
