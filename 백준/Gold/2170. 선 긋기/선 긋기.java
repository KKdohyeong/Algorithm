import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    //선의 정보 저장하는 InnerClass
    static class Info implements Comparable<Info>{
        int x, y;	//x : 시작 위치, y : 끝 위치
        public Info(int x, int y){
            this.x = x;
            this.y = y;
        }

        //선의 정보 정렬 기준
        @Override
        public int compareTo( Info o) {
            if(this.x == o.x)	//시작 위치가 같을 때 끝 위치 내림차순
                return o.y - this.y;
            return this.x - o.x;	//시작 위치 다를 때 시작 위치 오름차순
        }
    }
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        //선의 정보 기준 정렬되어 사용할 PriorityQueue
        PriorityQueue<Info> pq = new PriorityQueue<>();
        //입력되는 선의 정보 PriorityQueue 저장
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            pq.offer(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Info first_line = pq.poll();
        //현재 선의 시작 위치
        int start = first_line.x;
        //현재 선의 끝 위치
        int end = first_line.y;
        int result = 0;
  
        while(!pq.isEmpty()){
            Info cur = pq.poll();
   
            if(cur.x > end){
                result += end - start;	
         
                start = cur.x;	
                end = cur.y;
                continue;
            }

            if(cur.y > end)
                end = cur.y;
        }
        result += end - start;	
        bw.write(String.valueOf(result));	
        bw.flush();	
        bw.close();
        br.close();
    }
}