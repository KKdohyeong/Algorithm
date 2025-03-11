import java.util.*;
/*
순서를 만들어보자

(1) indegree(자료구조1 array) 배열에서 자신을 가리키는 숫자가 0인 노드를 꺼낸다
(2) 0인 노드들을 큐(자료구조2 queue)에 넣는다
(3) 큐에서 하나를 꺼내서 이 큐와 연결된 애들을(몇번 노드가 몇번 노드량 연결되어 있는지 자료구조3 linkedlist<linkedlist>)
1. indegree(자료구조1)값을 하나 뺌
2. indegree 값이 하나 빠진 애들의 results 값(자료구조4)을 설정함 이 때 계속 업데이트 하는데 큰 값으러 업데이트함
 */

public class Main {

    public static int[] indegree;
    public static int[] results;
    public static int[] values;
    public static List<List<Integer>> adjlist;
    public static Queue<Integer> queue;

    public static int tracking(int find, int node_num){
        for(int i=1; i<=node_num; i++){
            if(indegree[i]==0){
                queue.add(i);
            }
        }



        while(!queue.isEmpty()){
            int node = queue.poll();

            for(int i=0; i<adjlist.get(node).size(); i++){
                int arrive = adjlist.get(node).get(i);
                indegree[arrive]--;
                results[arrive] = Math.max(results[arrive], results[node]+values[node]);
                //System.out.printf("%d꺼내서 %d에접근 results : %d\n", node, arrive, results[arrive]);
                if(indegree[arrive]==0){
                    queue.add(arrive);
                }
            }


        }

        results[find]+=values[find];
        return results[find];
    }



    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        int how_many = sc.nextInt();
        for(int k=0; k<how_many; k++){
            int node_num = sc.nextInt();
            int rules = sc.nextInt();

            //초기화
            indegree = new int[node_num+1];
            results = new int[node_num+1];
            values = new int[node_num+1];
            queue = new LinkedList<>();
            adjlist = new ArrayList<>();

            //1~n번의 노드를 만들기위해 1개를 추가함
            adjlist.add(new ArrayList<>());
            for(int i=1; i<=node_num; i++){
                //초기화
                adjlist.add(new ArrayList<>());
                int value = sc.nextInt();
                values[i] = value;
                results[i] = 0;
                indegree[i] = 0;
            }

            for(int i=1; i<=rules; i++){
                int start = sc.nextInt();
                int finish= sc.nextInt();
                adjlist.get(start).add(finish);
                indegree[finish]++;
            }
            int find = sc.nextInt();
            int result = tracking(find, node_num);
            System.out.println(result);




        }

    }


}
