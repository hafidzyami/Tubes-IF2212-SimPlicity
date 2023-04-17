import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner input = new Scanner(System.in);
        World world = World.getInstance();
        String command = CLI.opening();
        while (!(command.equals("startgame") || command.equals("1"))) {
            command = CLI.wrongInput();
        }
        while(true) {
            Sim Bobi = new Sim("Bobi Orange Milk");
            world.addNewHome();
            world.printMap();
            break;
        }

        //world.printMap();
        
        //command = input.nextLine();
        
    }
}
