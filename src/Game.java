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
        System.out.print("Masukkan nama sim: ");
        Home home1 = Home.newHome(world, 1, 1);
        Sim sim1 = new Sim(input.next(),home1, world);
        home1.setOwner(sim1);
        world.addSimList(sim1);
        world.addNewHome(home1);
        world.setPlayedSim(sim1);
        sim1.setCurrentRoom(home1.getRoomList().get(0));
        world.updateMap();
        while(playing) {
            command = CLI.playing();
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
                    Menu.upgradeHouse(world.getPlayedSim(), "blabla", world.getPlayedSim().getMyHome());
                    break;

                case "8", "move room":
                    Menu.moveRoom();;
                    break;

                case "9", "edit room":
                    System.out.println("Silahkan pilih aksi berikut :");
                    System.out.println("1. Membeli barang");
                    System.out.println("2. Install barang");
                    int idx = input.nextInt();
                    Menu.editRoom(idx, world.getPlayedSim());
                    break;
                case "10", "add sim":
                    System.out.print("Masukkan nama Sim :");
                    String name = input.next();
                    System.out.print("Masukkan lokasi rumah (X) untuk dibangun : ");
                    int xHome = input.nextInt();
                    System.out.print("Masukkan lokasi rumah (Y) untuk dibangun : ");
                    int yHome = input.nextInt();
                    Menu.addSim(world, name, xHome, yHome);
                    break;

                case "11", "change sim":
                    if(world.getSimList().size() > 0){
                        System.out.println("Daftar Sim yang tersedia :");
                        world.printSimList();
                        System.out.println("Silahkan pilih sim berdasarkan nomor di atas : ");
                        boolean flag = true;
                        while(flag){
                            int cmd11 = input.nextInt();
                            if(cmd11 > 0 && cmd11 <= world.getSimList().size()){
                                flag = false;
                                Menu.changeSim(world, cmd11);
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
                    Menu.listObject(world);
                    System.out.println("Silahkan ketikan nama object yang ingin dituju!");
                    String key13 = input.next();
                    boolean flag13 = true;
                    while(flag13){
                        if(!sim1.currentRoom.getItemList().containsKey(key13)){
                            System.out.println("Nama object salah/tidak ada! Silahkan ketikan nama object yang benar!");
                            key13 = input.next();
                        }
                        else{
                            flag13 = false;
                        }
                    }
                    Menu.goToObject(world, key13);
                    break;

                case "14","action" :
                    CLI.printActionList();
                    System.out.println("Silahkan pilih nomor untuk melakukan aksi yang ingin dilakukan : ");
                    int idxAction = input.nextInt();
                    Menu.action(world, idxAction);
                    break;

                case "15","change job" :
                    Menu.changeJob("Badut");
                    break;
                default :
            }
        }

    }
}
