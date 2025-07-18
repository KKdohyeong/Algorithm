import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();                 // 학생 수
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        /* 무향 그래프 구성 : i가 j를 싫어하면 i‑j 양쪽에 간선 추가 */
        for (int i = 1; i <= n; i++) {
            int cnt = sc.nextInt();
            for (int k = 0; k < cnt; k++) {
                int j = sc.nextInt();
                adj[i].add(j);
                adj[j].add(i);
            }
        }

        int[] team = new int[n + 1];          // 0:미정, 1:청팀, -1:백팀

        for (int i = 1; i <= n; i++) {
            if (team[i] != 0) continue;       // 이미 색칠된 컴포넌트는 건너뜀
            team[i] = 1;
            Deque<Integer> dq = new ArrayDeque<>();
            dq.add(i);

            /* BFS 로 두 색(팀) 칠하기 */
            while (!dq.isEmpty()) {
                int cur = dq.poll();
                for (int nxt : adj[cur]) {
                    if (team[nxt] == 0) {             // 아직 팀이 정해지지 않은 경우
                        team[nxt] = -team[cur];        // 반대 팀 배정
                        dq.add(nxt);
                    } else if (team[nxt] == team[cur]) {  // 같은 팀끼리 싫어한다면 불가능
                        System.out.println("0");       // 문제 조건상 항상 가능하지만 안전판
                        return;
                    }
                }
            }
        }

        /* 결과 출력 (오름차순) */
        StringBuilder blue = new StringBuilder();
        StringBuilder white = new StringBuilder();
        int blueCnt = 0, whiteCnt = 0;
        for (int i = 1; i <= n; i++) {
            if (team[i] == 1) {
                blue.append(i).append(' ');
                blueCnt++;
            } else {
                white.append(i).append(' ');
                whiteCnt++;
            }
        }

        System.out.println(blueCnt);
        System.out.println(blue.toString().trim());
        System.out.println(whiteCnt);
        System.out.println(white.toString().trim());
    }
}
