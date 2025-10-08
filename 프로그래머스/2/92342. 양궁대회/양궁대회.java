import java.util.*;

class Solution {

    public int[] rion_shoots = {0,0,0,0,0,0,0,0,0,0,0}; // 현재 선택
    public int[] best = {-1};                           // 정답 후보
    public int maxDiff = Integer.MIN_VALUE;             // 최고 점수 차이

    // 라이언 − 어피치 점수 차이
    public int calculate(int[] info){
        int r = 0, a = 0;
        for(int i=0; i<=10; i++){
            int point = 10 - i;
            if(rion_shoots[i]==0 && info[i]==0) continue;
            if(rion_shoots[i] > info[i]) r += point;
            else a += point; // 동점은 어피치
        }
        return r - a;
    }

    // 타이브레이커: 낮은 점수칸부터 많이 맞힌 분배가 더 좋음
    public boolean better(int[] A, int[] B){
        if (B.length==1 && B[0]==-1) return true;
        for(int i=10;i>=0;i--){
            if(A[i]!=B[i]) return A[i] > B[i];
        }
        return false;
    }

    public void traverse(int depth, int left_shoot, int[] info){
        // 기저: 11칸 다 보거나 화살이 다 떨어짐
        if(depth==11 || left_shoot==0){
            // 남은 화살은 0점 칸에 몰아주기
            if(left_shoot>0){
                rion_shoots[10] += left_shoot;
            }

            int diff = calculate(info);
            if(diff > 0){
                if(diff > maxDiff || (diff == maxDiff && better(rion_shoots, best))){
                    maxDiff = diff;
                    best = rion_shoots.clone();
                }
            }

            if(left_shoot>0){
                rion_shoots[10] -= left_shoot; // 복구
            }
            return;
        }

        // 현재 depth부터 끝까지 보면서 "이 칸을 이길지" 고르기
        for(int i=depth; i<=10; i++){
            if(rion_shoots[i] > 0) continue; // 이미 선택한 칸은 패스

            // 이 칸을 이길 수 있으면 선택
            int need = info[i] + 1;
            if(left_shoot >= need){
                rion_shoots[i] = need;
                traverse(i+1, left_shoot - need, info);
                rion_shoots[i] = 0; // 복구
            }
            // 이 칸을 건너뛰는 경우는 for가 자연스럽게 커버
            // 다음 칸으로 넘어가며 조합을 계속 탐색
        }

        // 아무 칸도 더 고르지 않고 종료하는 경우도 고려
        // 위 for에서 아무것도 선택하지 않으면 아래 호출로 종료 분기 처리
        // 즉, 남은 화살 전부 0점 칸에 몰아 넣는 케이스 계산
        if (true) {
            // depth를 11로 점프시켜 기저 처리
            traverse(11, left_shoot, info);
        }
    }

    public int[] solution(int n, int[] info) {
        traverse(0, n, info);
        return best;
    }
}
