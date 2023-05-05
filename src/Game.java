import java.util.Scanner;

public class Game {
    private static World world = World.getInstance();
    private static Scanner input = new Scanner(System.in);
    //private static String command;
    private static Boolean playing = true;
    public static void start() {
        String command = CLI.opening();
        while (!(command.equals("startgame") || command.equals("1"))) {
            command = CLI.wrongInput();
        }
        System.out.print("Masukkan nama sim: ");
        House house1 = House.newHouse(world, 1, 1);
        String simName;
        while(true){
            simName = input.nextLine();
            if(simName.split(" ").length > 0 && simName.length() > 0){
                break;
            }else {
                System.out.println("Silahkan masukkan nama sim dengan benar");
                System.out.print("Masukkan nama sim: ");
            }
        }
        Sim sim1 = new Sim(simName,house1, world);
        house1.setOwner(sim1);
        world.addSimList(sim1);
        world.addNewHouse(house1);
        world.setPlayedSim(sim1);
        sim1.setCurrentRoom(house1.getRoomList().get(0));
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
                    Menu.viewInventory(world);
                    break;

                case "7", "upgrade house":
                    if(world.getPlayedSim().getSimMoney() >= 1500){
                        try{
                            System.out.println("Berikut merupakan denah rumah Anda : ");
                            House house = world.getPlayedSim().getMyHouse();
                            house.getHouseTile().printTile();
                            int xNew = house.getRoomList().get(0).getRoomCoordinate().getX();
                            int yNew = house.getRoomList().get(0).getRoomCoordinate().getY();
                            if(house.getHouseTile().getCountNonEmpty() > 1){
                                System.out.println("Daftar ruangan : ");
                                house.printRoomList();
                                System.out.println("Pilih acuan ruangan untuk penambahan ruangan : ");
                                int idxRuangan = input.nextInt();
                                xNew = house.getRoomList().get(idxRuangan-1).getRoomCoordinate().getX();
                                yNew = house.getRoomList().get(idxRuangan-1).getRoomCoordinate().getY();
                            }
                            System.out.println("Ketikan posisi dari ruangan acuan (Kiri/Kanan/Atas/Bawah) : ");
                            String posisiRuangan = input.next();
                            if(posisiRuangan.equals("Kiri")|| posisiRuangan.equals("kiri")){
                                if(xNew - 1 < 0 || house.getHouseTile().getTile(xNew-1, yNew) != "E" || house.getHouseTile().getTile(xNew-1, yNew).equals("UC")){
                                    System.out.println("Tidak bisa menambahkan ruangan di sana!");
                                }
                                else{
                                    xNew--;
                                    Menu.upgradeHouse(xNew, yNew, house, world);
                                }
                            }
                            else if(posisiRuangan.equals("Kanan") || posisiRuangan.equals("kanan")){
                                if(xNew + 1 > 5 || house.getHouseTile().getTile(xNew+1, yNew) != "E" || house.getHouseTile().getTile(xNew+1, yNew).equals("UC")){
                                    System.out.println("Tidak bisa menambahkan ruangan di sana!");
                                }
                                else{
                                    xNew++;
                                    Menu.upgradeHouse(xNew, yNew, house, world);
                                }
                                
                            }
                            else if(posisiRuangan.equals("Atas") || posisiRuangan.equals("atas")){
                                if(yNew - 1 < 0 || house.getHouseTile().getTile(xNew, yNew-1) != "E" || house.getHouseTile().getTile(xNew, yNew-1).equals("UC")){
                                    System.out.println("Tidak bisa menambahkan ruangan di sana!");
                                }
                                else{
                                    yNew--;
                                    Menu.upgradeHouse(xNew, yNew, house, world);
                                }
                            }
                            else if(posisiRuangan.equals("Bawah") || posisiRuangan.equals("bawah")){
                                if(yNew + 1 > 5 || house.getHouseTile().getTile(xNew, yNew+1) != "E" || house.getHouseTile().getTile(xNew, yNew+1).equals("UC")){
                                    System.out.println("Tidak bisa menambahkan ruangan di sana!");
                                }
                                else{
                                    yNew++;
                                    Menu.upgradeHouse(xNew, yNew, house, world);
                                }
                            }
                            else{
                                System.out.println("Input salah!");
                            }
                        }catch (Exception e){
                            System.out.println("Masukkan acuan ruangan berupa bilangan");
                        }
                    }
                    else{
                        System.out.println("Sim harus memiliki setidaknya uang sebesar 1500 untuk upgrade rumah!");
                    }
                    break;

                case "8", "move room":
                    System.out.println("Anda sekarang berada di ruangan " + world.getPlayedSim().getCurrentRoom().getRoomName() + " di rumah " + world.getPlayedSim().getCurrentHouse().getOwner().getSimName());
                    System.out.println("Daftar ruangan yang bisa Anda kunjungi : ");
                    world.getPlayedSim().getCurrentHouse().printRoomList();
                    try{
                        System.out.println("Silahkan pilih nomor ruangan yang ingin dituju : ");
                        int idxMoveRoom = input.nextInt();
                        if(idxMoveRoom <= 0 || idxMoveRoom > world.getPlayedSim().getCurrentHouse().getRoomList().size()){
                            System.out.println("Input salah!");
                        }
                        else{
                            Menu.moveRoom(world.getPlayedSim(), idxMoveRoom);
                        }
                    }catch(Exception e){
                        System.out.println("Masukkan nomor ruangan berupa bilangan");
                    }
                    break;
                case "9", "edit room":
                    System.out.println("Silahkan pilih aksi berikut :");
                    System.out.println("1. Membeli barang");
                    System.out.println("2. Install barang");
                    System.out.println("3. Pindah barang");
                    try{
                        int idx = input.nextInt();
                        Menu.editRoom(idx, world.getPlayedSim());
                    }catch(Exception e){
                        System.out.println("Masukkan input berupa bilangan");
                    }
                    
                    break;
                case "10", "add sim":
                    if(!world.getWorldClock().getHaveAddSim()){
                        System.out.print("Masukkan nama Sim :");
                        String name = input.next();
                        try{
                            System.out.print("Masukkan lokasi rumah (X) untuk dibangun : ");
                            int xHouse = input.nextInt();
                            System.out.print("Masukkan lokasi rumah (Y) untuk dibangun : ");
                            int yHouse = input.nextInt();
                            if(!world.getWorldTile().getTile(xHouse, yHouse).equals("E")){
                                System.out.println("Tidak bisa membangun rumah karena lokasi sudah ditempati rumah lain!");
                            }
                            else if(xHouse >= 1 && xHouse <= world.getWorldLength() && yHouse >= 1 && yHouse <= world.getWorldWidth()){
                                Menu.addSim(world, name, xHouse, yHouse);
                                world.getWorldClock().setHaveAddSim(true);
                            }
                            else{
                                System.out.println("Tidak bisa membangun rumah karena input lokasi di luar jangkauan World!");
                            }
                        }catch(Exception e){
                            System.out.println("Masukkan lokasi rumah berupa bilangan");
                        }
                    }else{
                        System.out.println("Anda telah melakukan Add Sim di hari ini. Tunggu esok untuk add sim lagi.");
                    }
                    break;

                case "11", "change sim":
                    if(world.getSimList().size() > 0){
                        System.out.println("Daftar Sim yang tersedia :");
                        world.printSimList();
                        System.out.println("Silahkan pilih sim berdasarkan nomor di atas : ");
                        boolean flag = true;
                        while(flag){
                            try{
                                int cmd11 = input.nextInt();
                                if(cmd11 > 0 && cmd11 <= world.getSimList().size()){
                                    flag = false;
                                    Menu.changeSim(world, cmd11);
                                }
                                else{
                                    System.out.println("Index yang Kamu pilih di luar jangkauan!");
                                }
                            }catch(Exception e){
                                System.out.println("Masukkan input sim berupa bilangan");
                                break;
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
                    if(world.getPlayedSim().getCurrentRoom().getItemList().size() > 0){
                        Menu.listObject(world);
                        System.out.println("Silahkan pilih nomor object yang ingin dituju!");
                        int key13 = input.nextInt();
                        if(key13 < 0 || key13 > sim1.getCurrentRoom().getItemList().size()){
                            System.out.println("Input salah!");
                        }
                        else{
                            Menu.goToObject(world, key13);
                        }
                    }
                    else{
                        System.out.println("Tidak ada object yang bisa dituju karena current room kosong!");
                    }
                    break;

                case "14","action" :
                    CLI.printActionList();
                    try{
                        System.out.println("Silahkan pilih nomor untuk melakukan aksi yang ingin dilakukan : ");
                        int idxAction = input.nextInt();
                        Menu.action(world, idxAction);
                    }catch(Exception e){
                        System.out.println("Masukkan input nomor berupa bilangan");
                    }
                    break;
                default :
            }
        }

    }
}
