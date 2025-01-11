

import java.util.*;

public class Main {
/*
DNA의 가능 원소는 A, T ,G, C

N개의 DNA가 주어질것이고 길이는 M이다.
이 모든 DNA들과의 hamming distance를 다 합해서 가장 작은 합이 되는 길이가 M인 DNA를 만드는 것이 목적이다.

(1) 모든 원소의 앞을 비교해서 가장 많은 놈을 선택한다.
(2) 만약 같다면 가장 사전순으로 앞에 놈을 선택한다.
 */


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int DNA_number = sc.nextInt();
        int DNA_length = sc.nextInt();
        List<String> DNA_list = new LinkedList<String>();
        for(int i=0; i<DNA_number; i++) {
            DNA_list.add(sc.next());
        }
        int[] alphabet = new int[100];
        List<Character> answer_DNA  = new LinkedList<Character>();

        for(int i=0; i<DNA_length; i++){
            //알파벳 초기화
            for(int j=0; j<100; j++){
                alphabet[j] = 0;
            }

            int max = 0;
            for(int j=0; j<DNA_number; j++){
                int alphabet_index = DNA_list.get(j).charAt(i) - 'A';
                //System.out.printf("list %s의 %d번째 char은 %c이다 그래서 alphabet idnex는 %d.\n", DNA_list.get(j), i, DNA_list.get(j).charAt(i), alphabet_index);
                alphabet[alphabet_index]++;

                if(max < alphabet[alphabet_index]){
                    max = alphabet[alphabet_index];
                    //System.out.printf("max is %d and char is %c \n", max, (char)('A' + alphabet_index));
                }
            }
            //System.out.println();
            //이제 가장 최대값의 최소
            for(int j=0; j<100; j++){
                if(alphabet[j] == max){
                    int answer = 'A' + j;
                    char charAnswer = (char) answer;
                    answer_DNA.add(charAnswer);
                    //System.out.printf("I add %c\n", charAnswer);
                    break;
                }
            }

        }

        int hash_distance = 0;
        for(int i=0; i<DNA_length; i++){
            for(int j=0; j<DNA_number; j++){
                if(DNA_list.get(j).charAt(i) != answer_DNA.get(i)){
                    hash_distance++;
                }

            }
        }

        StringBuilder sb = new StringBuilder();
        for(char c : answer_DNA) {
            sb.append(c);
        }
        System.out.println(sb.toString()); // 예: AABCDE
        System.out.printf("%d\n", hash_distance);
        return;

    }


}