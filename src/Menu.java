import java.util.Scanner;

public class Menu {
    public static void help() {
        System.out.println("⋅˚₊‧ ୨ Selamat datang di Sim-Plicity ୧ ‧₊˚ ⋅");
        System.out.println("Sim-Plicity adalah game berbasis Command Line Interface. Pada game ini kamu akan berperan sebagai karakter virtual yaitu sim.");
        System.out.println(" ");
        System.out.println("Untuk memainkan game ini, kamu bisa memasukan command sebagai berikut :");
        System.out.println("1 : untuk memulai game");
        System.out.println("2 : untuk melihat panduan game");
        System.out.println("3 : untuk keluar");
        System.out.println("4 : untuk melihat info yang dimiliki sim");
        System.out.println("5 : untuk melihat lokasi sim saat ini");
        System.out.println("6 : untuk melihat barang yang dimiliki sim");
        System.out.println("7 : untuk melakukan proses upgrade house");
        System.out.println("8 : untuk berpindah ruangan");
        System.out.println("9 : untuk mengedit rumah");
        System.out.println("10 : untuk menambah sim baru");
        System.out.println("11 : untuk mengganti karakter sim");
        System.out.println("12 : untuk melihat daftar objek di dalam ruangan");
        System.out.println("13 : untuk membuat sim jalan menuju sebuah objek");
        System.out.println("14 : untuk melakukan aksi pada suatu objek");
    }

    public static void exit() {
        System.out.println("Terima kasih telah bermain, sampai jumpa!");
    }

    public static void viewSimInfo(World world) {
        Sim sim = world.getPlayedSim(); 
        System.out.println(sim.getSimInfo());
    }

    public static void viewCurrentLocation(Sim sim) {
        String owner = sim.currentHome.getOwner().getSimName();
        System.out.println(sim.getSimName() + " berada di rumah milik " + owner + " di dalam " + sim.currentRoom.getRoomName());
    }

    public static void viewInventory(World world) {
        Sim sim = world.getPlayedSim();
        sim.seeInventory();
    }

    public static void upgradeHouse(Sim sim, String roomName) {
        System.out.println("rumah sedang diupgrade");

        Room room = new Room(roomName, sim.getMyHome());
        Scanner input = new Scanner(System.in);
        System.out.println("Pilih letak ruangan dari " + sim.currentRoom.getRoomName());
        // Roomloc roomLoc input.nextLine(); (input lokasi ruangan akan berada)
        input.close(); 
    }

    public static void moveRoom() {
        System.out.println("pindah ruang");
    }

    public static void editRoom(int idx, Sim sim) {
        if(idx == 1){
            System.out.println("Silahkan pilih barang yang ingin dibeli : ");
            CLI.printPurchaseAble();
            Scanner input = new Scanner(System.in);
            System.out.println("Silahkan pilih 1 untuk Non Food");
            System.out.println("Silahkan pilih 2 untuk Food Ingredients");
            int idxBeli =Integer.parseInt(input.nextLine());
            if(idxBeli == 1){
                System.out.println("Silahkan masukan nama barang : ");
                String name = input.nextLine();
                PurchaseAble item = new NonFoodItem(name);
                sim.buyItem(item);
            }
            else if(idxBeli == 2){
                System.out.println("Silahkan masukan nama barang : ");
                String name = input.nextLine();
                PurchaseAble item = new FoodIngredients(name);
                sim.buyItem(item);
            }
            else{
                System.out.println("Masukkan input yang benar!");
            }
            input.close();
        }
        else if(idx == 2){
            if( sim.getSimInventory().getNonFoodCount() > 0){
                sim.currentRoom.getRoomTile().printTile();
                System.out.println("Silahkan pilih nomor barang yang ingin dipasang dari inventory kamu : ");
                sim.getSimInventory().printInventory(sim.getSimInventory().getNonFoodItem());
                Scanner input = new Scanner(System.in);
                int idxItem = input.nextInt();
                boolean flagEditRoom = false;
                while(!flagEditRoom){
                    if(idxItem > 0 && idxItem <= sim.getSimInventory().getNonFoodCount()){
                        flagEditRoom = true;
                    }
                    else{
                        System.out.println("Silahkan pilih nomor yang benar!");
                        idxItem = input.nextInt();
                    }
                }
                System.out.println("Silahkan pilih posisi upperLeft (X) dari barang yang ingin di pasang : ");
                int wantedX = input.nextInt();
                System.out.println("Silahkan pilih posisi upperLeft (Y) dari barang yang ingin di pasang : ");
                int wantedY = input.nextInt();
                sim.installItem(sim.currentRoom, idxItem-1, wantedX, wantedY);
                sim.currentRoom.getRoomTile().printTile();
                input.close();
            }
            else{
                System.out.println("Tidak bisa memasang barang karena inventory kamu kosong!");
            }
            
        }
    }

    public static void addSim(World world, String name, int xHome, int yHome) {
        Home home = Home.newHome(world, xHome, yHome);
        Sim sim = new Sim(name,home, world);
        home.setOwner(sim);
        world.addSimList(sim);
        world.addNewHome(home);
        sim.setCurrentRoom(home.getRoomList().get(0));
        world.updateMap();
        System.out.println("Sim berhasil ditambahkan!");
    }

    public static void changeSim(World world, int idx) {
        world.setPlayedSim(world.getSimList().get(idx-1));
        System.out.println("Berhasil mengganti Sim ke " + world.getSimList().get(idx-1).getSimName());
    }

    public static void listObject(World world) {
        Sim sim = world.getPlayedSim();
        System.out.println("Berikut List Objek dalam ruangan");
        int idx = 1;
        for(Item i : sim.currentRoom.getItemList().values()){
            System.out.println(idx + ". " + i.getName());
            idx++;
        }
    }

    public static void goToObject(World world, String itemKey) {
        Sim sim = world.getPlayedSim();
        sim.useItem = sim.currentRoom.getItemList().get(itemKey).getName();
        System.out.println("Sim sedang menggunakan " + sim.useItem);
    }

    public static void action(World world, int idxAction) {
        Sim sim = world.getPlayedSim();
        Scanner input = new Scanner(System.in);
        switch(idxAction){
            case 1 :
                System.out.println("Masukkan durasi bekerja :");
                int durasiKerja = input.nextInt();
                sim.work(durasiKerja);
                break;
            case 2 : 
                System.out.println("List pekerjaan dan biaya yang harus dibayarkan untuk mengganti pekerjaan : "); 
                System.out.println("1. Badut Sulap - 7.5"); 
                System.out.println("2. Koki - 15"); 
                System.out.println("3. Polisi - 17.5"); 
                System.out.println("4. Programmer - 22.5"); 
                System.out.println("5. Dokter - 25"); 
                System.out.println("Masukan nama pekerjaan baru :"); 
                String newJobString = input.nextLine();
                Job newJob = new Job(newJobString);
                if (newJob.getJobName().equals("not valid")) {
                    System.out.println("Nama job tidak valid!");
                } else {
                    sim.changeJob(newJob);
                }
                break;
            case 3 :
                System.out.println("Masukkan durasi olahraga :");
                int durasiOlahraga = input.nextInt();
                sim.sport(durasiOlahraga);
                break;
            case 4 :
                if(sim.useItem.equals("Single Bed") || sim.useItem.equals("Queen Size Bed") || sim.useItem.equals("King Size Bed")){
                    System.out.println("Masukkan durasi Tidur :");
                    int durasiTidur = input.nextInt();
                    sim.sleep(durasiTidur);
                }
                else{
                    System.out.println("Silahkan pergi ke object 'Bed' untuk tidur!");
                }
                break;
            case 5 :
                System.out.println("Daftar makanan :");
                // tunggu code jadi
                break;
            case 6 : 
                if(sim.useItem.equals("Gas Stove") || sim.useItem.equals("Electric Stove")){
                    CLI.printFoodMenu();
                    System.out.println("Silahkan pilih menu yang akan dimasak");
                    String meal = input.nextLine();
                    sim.cook(meal);
                    // tunggu code jadi
                }
                else{
                    System.out.println("Silahkan pergi ke object 'Stove' untuk masak!");
                }
                break;
            case 7 :
                System.out.println("Daftar rumah Sim untuk dikunjungi :");
                CLI.printHomeAndSim(world);
                world.printMap();
                System.out.println("Ketikan nomor Sim untuk dikunjungi :");
                int idxVisit = input.nextInt();
                sim.visit(world.getHomeList().get(idxVisit-1).getLocX(), world.getHomeList().get(idxVisit-1).getLocY(), idxVisit-1);
                break;
            case 8:
                if(sim.useItem.equals("Toilet")){
                    sim.pee();
                }
                else{
                    System.out.println("Silahkan pergi ke object 'Toilet' untuk buang air!");
                }
                break;
            case 9:
                if(sim.useItem.equals("Clock")){
                    sim.seeTime();
                }
                else{
                    System.out.println("Silahkan pergi ke object 'Clock' untuk lihat waktu!");
                }
                break;
            case 10:
                if(sim.useItem.equals("Single Bed") || sim.useItem.equals("Queen Size Bed") || sim.useItem.equals("King Size Bed")){
                    System.out.println("Seberapa lama anda ingin menangis");
                    int duration = Integer.parseInt(input.nextLine());
                    sim.crying(duration);
                }else{
                    System.out.println("Anda harus ke 'Bed' untuk menangis");
                }
                break;
            case 11:
                if(sim.useItem.equals("Table and Chair")){
                    System.out.println("Masukkan durasi untuk mengaji : ");
                    int durasiMengaji = input.nextInt();
                    sim.recitate(durasiMengaji);
                }else {
                    System.out.println("Silahkan pergi ke object 'Table and Chair' untuk mengaji");
                } 
                break;
            case 12:
                System.out.println("Daftar barang yang bisa dicuri di ruangan ini : ");
                listObject(world);
                System.out.println("Masukkan nama barang yang ingin dicuri");
                sim.steal(input.nextLine());
                break;

            case 13:
                if(sim.useItem.equals("Table and Chair")){
                    sim.write();
                }else {
                    System.out.println("Silahkan pergi ke object 'Table and Chair' untuk menulis");
                } 
                break;
            case 14:
                if(sim.useItem.equals("Table and Chair")){
                    sim.read();
                }else {
                    System.out.println("Silahkan pergi ke object 'Table and Chair' untuk membaca");
                } 
                // tunggu code
                break;
            case 15:
                if(sim.useItem.equals("Table and Chair") || sim.useItem.equals("Toilet") || sim.useItem.equals("Single Bed") || sim.useItem.equals("Queen Size Bed") || sim.useItem.equals("King Size Bed")){
                    System.out.println("Masukkan durasi untuk melamun : ");
                    int durasiMelamun = input.nextInt();
                    sim.daydream(durasiMelamun);
                }else {
                    System.out.println("Silahkan pergi ke object 'Table and Chair' atau 'Toilet' atau 'Bed' untuk melamun");
                } 
                break;
            case 16:
                if(sim.useItem.equals("Toilet")){
                    sim.bath();
                }
                else{
                    System.out.println("Silahkan pergi ke object 'Toilet' untuk mandi!");
                }
                break;
        }
        input.close();
    }
}
