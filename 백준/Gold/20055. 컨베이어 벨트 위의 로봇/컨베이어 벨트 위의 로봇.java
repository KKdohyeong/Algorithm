import java.util.*;

public class Main {
    public static class Robot {
        public int location;
        public Robot(int location) {
            this.location = location;
        }
    }

    public static class Belt {
        public Robot robot;
        public int remain;
        public Belt(int remain){
            this.remain = remain;
            robot = null;
        }
        public boolean robot_exist(){
            return robot != null;
        }
    }

    public static int robot_on = 1;
    public static int robot_off;
    public static Belt[] Belts;
    public static int k;
    public static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int remain_zero = 0;
        int answer = 0;

        // N, K 입력
        robot_off = sc.nextInt();  // N
        n = robot_off;
        k = sc.nextInt();

        // 벨트 초기화
        Belts = new Belt[2*n+1];
        for(int i=1; i<=2*n; i++){
            int remain = sc.nextInt();
            Belts[i] = new Belt(remain);
        }

        // 로봇 리스트
        LinkedList<Robot> robots = new LinkedList<>();

        // 시뮬레이션
        while(remain_zero < k) {
            // ------------------------
            // 1) 벨트(혹은 on/off) 회전
            //    (여기는 본인 로직대로 유지)
            // ------------------------
            if (robot_on == 1) {
                robot_on = 2*n;
                robot_off--;
            } else if (robot_off == 1) {
                robot_on--;
                robot_off = 2*n;
            } else {
                robot_on--;
                robot_off--;
            }

            // 내리는 위치에 로봇 있으면 즉시 내리기
            if (Belts[robot_off].robot_exist()){
                robots.remove(Belts[robot_off].robot);
                Belts[robot_off].robot = null;
            }

            // ------------------------
            // 2) 로봇 이동(Iterator로 처리)
            // ------------------------
            Iterator<Robot> it = robots.iterator();
            while (it.hasNext()) {
                Robot robot = it.next();
                int currPos = robot.location;
                int nextPos = currPos + 1;
                if (currPos == 2*n) {
                    nextPos = 1;
                }

                // 다음 칸 확인
                Belt nextBelt = Belts[nextPos];
                if (nextBelt.robot == null && nextBelt.remain >= 1) {
                    // 1) 로봇 이동
                    Belts[currPos].robot = null; // 이전 칸에서 로봇 내려놓기
                    robot.location = nextPos;
                    nextBelt.robot = robot;

                    // 2) 내구도 감소
                    nextBelt.remain--;
                    if (nextBelt.remain == 0) {
                        remain_zero++;
                    }

                    // 3) 이동한 칸이 내리는 위치이면 즉시 로봇 제거
                    if (nextPos == robot_off) {
                        nextBelt.robot = null;
                        it.remove();        // ← 여기서 안전하게 제거
                    }
                }
            }

            // ------------------------
            // 3) 올리는 위치에 로봇 올리기
            // ------------------------
            if (Belts[robot_on].remain >= 1 && Belts[robot_on].robot == null) {
                Robot newRobot = new Robot(robot_on);
                Belts[robot_on].robot = newRobot;
                robots.add(newRobot);

                Belts[robot_on].remain--;
                if (Belts[robot_on].remain == 0) {
                    remain_zero++;
                }
            }

            answer++;
        }
        System.out.println(answer);
    }
}
