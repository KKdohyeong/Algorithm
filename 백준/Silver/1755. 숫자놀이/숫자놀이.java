import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".


class Main {

    public static int check(int a){
        if(a/10 == 0){
            return 1;
        }
        return 2;
    }
    
    public static String forOne(HashMap<Character, String> map, String str){
        char a = str.charAt(0);
        return map.get(a);
    }


    public static String forTwo(HashMap<Character, String> map, String str){
        char a = str.charAt(0);
        char b = str.charAt(1);
        StringBuilder strbuilder = new StringBuilder();
        strbuilder.append(map.get(a));
        strbuilder.append(" ");
        strbuilder.append(map.get(b));
        
        return strbuilder.toString();
    }

    public static String convert(HashMap<String, String> map, String str){
        String[] my_list = str.split(" ");
        StringBuilder builder = new StringBuilder();

        for(String sst : my_list){
            builder.append(map.get(sst));
        }

        return builder.toString();
        
    }
    
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int start = sc.nextInt();
        int finish = sc.nextInt();
        HashMap<Character, String> map = new HashMap<>();
        map.put('0', "zero");
        map.put('1', "one");
        map.put('2', "two");
        map.put('3', "three");
        map.put('4', "four");
        map.put('5', "five");
        map.put('6', "six");
        map.put('7', "seven");
        map.put('8', "eight");
        map.put('9', "nine");

        HashMap<String, String> map_two = new HashMap<>();
        map_two.put("zero", "0");
        map_two.put("one", "1");
        map_two.put("two", "2");
        map_two.put("three", "3");
        map_two.put("four", "4");
        map_two.put("five", "5");
        map_two.put("six", "6");
        map_two.put("seven", "7");
        map_two.put("eight", "8");
        map_two.put("nine", "9");

        
        LinkedList<String> list = new LinkedList<>();

        for(int i = start; i <=finish; i++){
            int where = check(i);
            if(where == 1){
                list.add(forOne(map, Integer.toString(i)));
            }
            else{
                list.add(forTwo(map, Integer.toString(i)));
            }
        }

        
        Collections.sort(list);
        int count = 0;
        for(String str : list){
            String result = convert(map_two, str);
            System.out.printf("%s ", result);

            count++;
            if(count == 10){
                count = 0;
                System.out.println();
            }
        }
        
        
        
        
    }
}