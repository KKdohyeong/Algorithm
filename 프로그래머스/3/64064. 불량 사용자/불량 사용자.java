import java.util.*;

class Solution {

    public static boolean is_same(String user, String banned) {
        if (user.length() != banned.length()) {
            return false;
        }

        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) == '*') {
                continue;
            }
            if (user.charAt(i) != banned.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static int user_id_length;
    public static int[] visited; // banned_id의 방문 여부
    public static int total_check;
    public static int banned_length;
    public static int result = 0;
    
    public static HashMap<Integer, Integer> hashmap = new HashMap<>();

    public static void traverse(String[] user_id, String[] banned_id, int depth, int check, int bitmask) {
        if (check == total_check) {
            
            if(!hashmap.containsKey(bitmask)){
                result++;
                hashmap.put(bitmask, 1);
            }
            return;
        }
        if (depth == user_id_length) {
            return;
        }

        
        for (int j = 0; j < banned_length; j++) {
            //불량이 맞으면 이제 
                
            if (visited[j] == 0 && is_same(user_id[depth], banned_id[j])) {
                visited[j] = 1;
                
                //System.out.printf("현재 비트마스킹은 %d이고 %d번째가 일치함\n", bitmask, depth);
                int return_bitmask = bitmask;
                
                bitmask = (bitmask | (1 << depth));
                
                traverse(user_id, banned_id, depth + 1, check + 1, bitmask);
                
                bitmask = return_bitmask;
                visited[j] = 0; 
            }
        }

        traverse(user_id, banned_id, depth + 1, check, bitmask);
    }

    public int solution(String[] user_id, String[] banned_id) {
        result = 0;
        
        user_id_length = user_id.length;
        total_check = banned_id.length;
        banned_length = banned_id.length;

        visited = new int[banned_length];
        Arrays.fill(visited, 0);

        traverse(user_id, banned_id, 0, 0, 0);

        return result;
    }
}