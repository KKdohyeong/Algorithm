
import java.io.*;
import java.util.*;



public class Main
{


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
        String a = st.nextToken();
        String b = st.nextToken();
        int min = 1000;
        for(int i=0; i<=b.length()-a.length(); i++){
            int count = 0;
            for(int j=0; j<a.length(); j++ ){
                //System.out.printf("비교 %c %c\n", a.charAt(j), b.charAt(j+i));

                if(a.charAt(j) != b.charAt(j+i)){
                    count++;
                }
            }
            //System.out.println();
            if(count<min){
                min = count;
            }
        }

        System.out.print(min);
    }
}
