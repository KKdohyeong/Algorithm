import java.util.Scanner;
import java.math.BigDecimal;
 
public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 입력 받기
        BigDecimal a = sc.nextBigDecimal();
        int b = sc.nextInt();
        
        // a의 b제곱 계산
        BigDecimal result = calculatePower(a, b);
        
        // 지수 표기법이 사용되지 않고 소수 부분을 모두 풀어서 출력
        System.out.println(result.toPlainString());
    }
    
    // a의 b제곱 계산 메서드
    private static BigDecimal calculatePower(BigDecimal base, int exponent) {
        return base.pow(exponent);
    }
    
}