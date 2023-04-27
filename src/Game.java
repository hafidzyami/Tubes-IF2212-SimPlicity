import java.util.Scanner;

public class Game {
    private static World world = World.getInstance();
    private static Scanner input = new Scanner(System.in);
    private static String command;
    private static Boolean playing = true;
    public static void start() {
        String command = CLI.opening();
        while (!(command.equals("startgame") || command.equals("1"))) {
            command = CLI.wrongInput();
        }
        while(playing) {
            command = CLI.ListMenu();
            switch(command.toLowerCase()) {
                case "1","startgame" :
                    CLI.repeatedStart();
                    break;
                case "2","help" :
                    Menu.help();
                    break;
                case "3","exit" :
                    playing = false;
                    Menu.exit();
                    break;
                case "4","view sim info" :
                    Menu.viewSimInfo(world);
                    break;
                case "5", "view current location":
                    Menu.viewCurrentLocation(world.getPlayedSim());
                    break;
                case "10", "add sim":
                    System.out.println("Masukkan nama Sim");
                    String name = input.nextLine();
                    Menu.addSim(world, name);
                    break;
                case "11", "change sim":
                    System.out.println("Daftar Sim yang tersedia :");
                    world.printSimList();
                    System.out.println("Silahkan pilih sim berdasarkan nomor di atas! : ");
                    int idx = input.nextInt();
                    Menu.changeSim(world, idx);
                default :
            }
        }

    }
}
