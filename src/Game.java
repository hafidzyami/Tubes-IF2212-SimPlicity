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

                case "6", "view inventory":
                    Menu.viewInventory(world);;
                    break;

                case "7", "upgrade house":
                    Menu.upgradeHouse();
                    break;

                case "8", "move room":
                    Menu.moveRoom();;
                    break;

                case "9", "edit room":
                    Menu.editRoom();;
                    break;
                case "10", "add sim":
                    System.out.println("Masukkan nama Sim");
                    String name = input.nextLine();
                    Menu.addSim(world, name);
                    break;

                case "11", "change sim":
                    if(world.getSimList().size() > 0){
                        System.out.println("Daftar Sim yang tersedia :");
                        world.printSimList();
                        System.out.println("Silahkan pilih sim berdasarkan nomor di atas : ");
                        boolean flag = true;
                        while(flag){
                            int idx = input.nextInt();
                            if(idx > 0 && idx <= world.getSimList().size()){
                                flag = false;
                                Menu.changeSim(world, idx);
                            }
                            else{
                                System.out.println("Index yang Kamu pilih di luar jangkauan!");
                            }
                        }
                    }
                    else{
                        System.out.println("Tidak ada Sim untuk dipilih! Silahkan add Sim!");
                    }
                    break;

                case "12","list object" :
                    Menu.listObject(world);
                    break;

                case "13","go to object" :
                    Menu.goToObject(world, "barang");
                    break;

                case "14","action" :
                    Menu.action();
                    break;

                case "15","change job" :
                    Menu.changeJob("Badut");
                    break;
                default :
            }
        }

    }
}
