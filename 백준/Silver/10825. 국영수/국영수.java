

import java.io.*;
import java.util.*;



public class Main
{


    public static class Student implements Comparable<Student>{
        public int korean;
        public int english;
        public int math;
        public String name;
        public Student(int korean, int english, int math, String name){
            this.korean=korean;
            this.english=english;
            this.math=math;
            this.name= name;
        }

        @Override
        public int compareTo(Student o){
            if(this.korean!=o.korean){
                return Integer.compare(o.korean, this.korean);
            }

            if(this.english!=o.english){
                return Integer.compare(this.english, o.english);
            }

            if(this.math!=o.math){
                return Integer.compare(o.math, this.math);
            }

            return this.name.compareTo(o.name);

        }

    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ArrayList<Student> Students = new ArrayList<>();

        int size = Integer.parseInt(br.readLine());

        for(int i=0; i<size; i++){
            st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            Students.add(new Student(korean, english, math, name));
        }

        Collections.sort(Students);

        for(Student student : Students){
            System.out.println(student.name);
        }


    }
}
