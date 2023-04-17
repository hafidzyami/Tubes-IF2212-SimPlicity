import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class TestTimer {
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
    Instant start = Instant.now();
    //your code
    System.out.println("START OAKWOKAW");
    int angka = input.nextInt();
    
    Instant end = Instant.now();
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
    
    Duration timeElapsed = Duration.between(start, end);
    System.out.println("Time taken: "+ timeElapsed.toSeconds() +" seconds");
    System.out.println("Time taken: "+ timeElapsed.toSeconds());
    System.out.println(angka);
    input.close();
    }    
}
