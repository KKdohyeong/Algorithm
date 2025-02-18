
import java.io.*;
import java.util.*;



public class Main
{


    public static class location implements Comparable<location>{
        public int x;
        public int y;

        public location(int x, int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public int compareTo(location o){
            if(this.x==o.x){
                return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.x, o.x);
        }

    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<location> locations = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());

        for(int i=0; i<size; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            locations.add(new location(x,y));
        }
        Collections.sort(locations);

        for(location loc : locations){
            sb.append(loc.x+" "+ loc.y).append("\n");
        }
        System.out.println(sb);

    }
}
