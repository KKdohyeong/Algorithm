import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    public static double bronze = 0;
    public static double silver = 0;
    public static double gold = 0;
    public static double platinum = 0;
    public static double diamond = 0;
    public static double a;
    public static double b;
    public static double c;
    public static double[][] array = new double[21][3610];

    public static void daik(int depth, double hwak, int point){
        if(depth==20){
            if(point>=1000 && point<=1499){
                bronze += hwak;
            }
            else if(point >=1500 && point <=1999){
                silver += hwak;
            }
            else if(point>=2000 && point<=2499){
                gold += hwak;
            }
            else if(point >=2500 && point <=2999){
                platinum += hwak;
            }
            else if(point>=300 && point <=3499){
                diamond += hwak;
            }
            return;
        }
        //승리확률
        daik(depth+1, hwak*a, point+50);
        daik(depth+1, hwak*b, point);
        daik(depth+1, hwak*c, point-50);
        
    }

    public static class Where{
        public int point;
        public double hwak;

        public Where(int point, double hwak){
            this.point=point;
            this.hwak = hwak;
        }
    }
    

    
    public static void bfs(){

        int depth = 0;
        while(depth <20){
            for(int i=0; i<=3500; i++){
                if(array[depth][i] >0){
                    //System.out.printf("%d위치의 값은 %f\n", i, array[depth][i]);
                    double hwak = array[depth][i];

                    //이길경우
                    array[depth+1][i+50] += hwak*a;
                    
                    //비길경우
                    array[depth+1][i] += hwak * c;

                    //질경우
                    if(i-50>=0){
                        array[depth+1][i-50] += hwak*b;                    
                    }
                }
            }
            depth++;
            
        }
        
    }


    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextDouble();
        b = sc.nextDouble();
        c = sc.nextDouble();

        for(int i=0; i<=20; i++){
            for(int j=0; j<3600; j++){
                array[i][j] = 0;
            }
        }

        array[0][2000] = 1;

        bfs();
        for(int point=0; point<3501; point++){
            if(array[20][point]>0){
                     
                if(point>=1000 && point<=1499){
                    bronze += array[20][point];
                }
                else if(point >=1500 && point <=1999){
                    silver += array[20][point];
                }
                else if(point>=2000 && point<=2499){
                    gold += array[20][point];
                }
                else if(point >=2500 && point <=2999){
                    platinum += array[20][point];
                }
                else if(point>=300 && point <=3499){
                    diamond += array[20][point];
                }   
            }
        }
        
        System.out.println(String.format("%.8f", bronze));
        
        System.out.println(String.format("%.8f", silver));
        
        System.out.println(String.format("%.8f", gold));
        
        System.out.println(String.format("%.8f", platinum));
        
        System.out.println(String.format("%.8f", diamond));
        
    }
}