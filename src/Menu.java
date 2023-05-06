import java.util.Scanner;

public class Menu {
    private static Scanner input = new Scanner(System.in);
    public static void help() {
        CLI st = new CLI();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("No.", "Menu");
        System.out.println("⋅˚₊‧ ୨ Selamat datang di Sim-Plicity ୧ ‧₊˚ ⋅");
        System.out.println("Sim-Plicity adalah game berbasis Command Line Interface. Pada game ini kamu akan berperan sebagai karakter virtual yaitu sim.");
        System.out.println(" ");
        System.out.println("Untuk memainkan game ini, kamu bisa memasukan command sebagai berikut :");
        st.addRow("1.", "untuk memulai game");
        st.addRow("2.", "untuk melihat panduan game");
        st.addRow("3.", "untuk keluar");
        st.addRow("4.", "untuk melihat info yang dimiliki sim");
        st.addRow("5.", "untuk melihat lokasi sim saat ini");
        st.addRow("6.", "untuk melihat barang yang dimiliki sim");
        st.addRow("7.", "untuk melakukan proses upgrade house");
        st.addRow("8.", "untuk berpindah ruangan");
        st.addRow("9.", "untuk mengedit rumah");
        st.addRow("10.", "untuk menambah sim baru");
        st.addRow("11.", "untuk mengganti karakter sim");
        st.addRow("12.", "untuk melihat daftar objek di dalam ruangan");
        st.addRow("13.", "untuk membuat sim jalan menuju sebuah objek");
        st.addRow("14.", "untuk melakukan aksi pada suatu objek");
        st.print();
    }

    public static void exit() {
        System.out.println("Terima kasih telah bermain, sampai jumpa!");
    }

    public static void viewSimInfo(World world) {

        Sim sim = world.getPlayedSim(); 
        System.out.println(sim.getSimInfo());
    }

    public static void viewCurrentLocation(Sim sim) {
        System.out.println("-----BERIKUT PETA DUNIA------");
        sim.getCurrentWorld().printMap();
        System.out.println("-----BERIKUT DENAH RUMAH------");
        sim.getCurrentHouse().getHouseTile().printTile();
        System.out.println("-----BERIKUT DENAH RUANGAN------");
        sim.getCurrentRoom().getRoomTile().printTile();
        String owner = sim.getCurrentHouse().getOwner().getSimName();
        System.out.println(sim.getSimName() + " berada di rumah milik " + owner + " di dalam " + sim.getCurrentRoom().getRoomName());
    }

    public static void viewInventory(World world) {
        Sim sim = world.getPlayedSim();
        sim.seeInventory();
    }

    public static void upgradeHouse(int xNew, int yNew, House house, World world) {
        house.getHouseTile().changeTile("UC", xNew, yNew);
        System.out.println("Masukkan nama ruangan yang Anda inginkan : ");
        String name = input.nextLine();
        Thread t = new Thread(new Runnable(){
            public void run(){
                int start = world.getWorldClock().getTotalElapsed();
                int now;
                world.getPlayedSim().gainMoney(-1500);
                System.out.println("Ruangan " + name + " akan selesai dibangun dalam 18 menit");
                while (true) {
                    now = world.getWorldClock().getTotalElapsed();
                    if (now >= start + 30){
                        Room newRoom = new Room(name, house);
                        newRoom.setRoomCoordinate(xNew, yNew);
                        house.addRoom(newRoom);
                        house.getHouseTile().changeTile(newRoom.getRoomName(), xNew, yNew);
                        if(!world.getPlayedSim().canceled) {
                            System.out.println("Ruangan " + name + " sudah selesai dibangun!");
                        }
                        world.getPlayedSim().setOnUpgrade(false);
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    }
                    catch (Exception e) {

                    }
                }
            }
        });
        t.start();
    }

    public static void moveRoom(Sim sim, int idx) {
        sim.moveToRoom(idx);
    }

    public static void editRoom(int idx, Sim sim) {
        if(idx == 1){
            if(sim.getOnDelivery()){
                System.out.println("Anda sedang memiliki pesanan lain yang belum sampai");
            }else {
                System.out.println("Silahkan pilih barang yang ingin dibeli : ");
                //Scanner input = new Scanner(System.in);
                System.out.println("1.Non Food");
                System.out.println("2.Food Ingredients");
                int idxBeli = Integer.parseInt(input.nextLine());
                if(idxBeli == 1){
                    CLI.printNonFoodItem();
                    System.out.println("Silahkan masukan nomor barang : ");
                    int idxNonFood = Integer.parseInt(input.nextLine());
                    PurchaseAble item;
                    switch (idxNonFood) {
                        case 1 :
                            item = new NonFood("Single Bed");
                            break;
                        case 2 :
                            item = new NonFood("Queen Size Bed");
                            break;
                        case 3 :
                            item = new NonFood("King Size Bed");
                            break;
                        case 4 :
                            item = new NonFood("Toilet");
                            break;
                        case 5 :
                            item = new NonFood("Gas Stove");
                            break;
                        case 6 :
                            item = new NonFood("Electric Stove");
                            break;
                        case 7 :
                            item = new NonFood("Table and Chair");
                            break;
                        case 8 :
                            item = new Clock();
                            break;
                        default :
                            item = null;
                    }
                    System.out.println(item.getName());
                    sim.buyItem(item);
                }
                else if(idxBeli == 2){
                    CLI.printFoodIngredient();
                    System.out.println("Silahkan masukan nomor barang : ");
                    int idxIngredient = Integer.parseInt(input.nextLine());
                    PurchaseAble item;
                    switch (idxIngredient) {
                        case 1 :
                            item = new FoodIngredients("Rice");
                            break;
                        case 2 :
                            item = new FoodIngredients("Potato");
                            break;
                        case 3 :
                            item = new FoodIngredients("Chicken");
                            break;
                        case 4 :
                            item = new FoodIngredients("Beef");
                            break;
                        case 5 :
                            item = new FoodIngredients("Carrot");
                            break;
                        case 6 :
                            item = new FoodIngredients("Spinach");
                            break;
                        case 7 :
                            item = new FoodIngredients("Nut");
                            break;
                        case 8 :
                            item = new FoodIngredients("Milk");
                            break;
                        default :
                            item = null;
                    }
                    sim.buyItem(item);
                }
                else{
                    System.out.println("Masukkan input yang benar!");
                }
            }
        }
        else if(idx == 2){
            if( sim.getSimInventory().getNonFoodCount() > 0){
                System.out.println("Berikut merupakan denah " + sim.getCurrentRoom().getRoomName() + " di rumah " + sim.getCurrentHouse().getOwner().getSimName());
                sim.getCurrentRoom().getRoomTile().printTile();
                sim.getSimInventory().printInventory(sim.getSimInventory().getNonFoodItem());
                System.out.println("Silahkan pilih nomor barang yang ingin dipasang dari inventory kamu : ");
                //Scanner input = new Scanner(System.in);
                int idxItem = Integer.parseInt(input.nextLine());
                boolean flagEditRoom = false;
                while(!flagEditRoom){
                    if(idxItem > 0 && idxItem <= sim.getSimInventory().getNonFoodCount()){
                        flagEditRoom = true;
                    }
                    else{
                        System.out.println("Silahkan pilih nomor yang benar!");
                        idxItem = Integer.parseInt(input.nextLine());
                    }
                }
                System.out.println("Silahkan pilih posisi pojok kiri (X) dari barang yang ingin di pasang : ");
                int wantedX = Integer.parseInt(input.nextLine());
                System.out.println("Silahkan pilih posisi pojok kiri (Y) dari barang yang ingin di pasang : ");
                int wantedY = Integer.parseInt(input.nextLine());
                sim.installItem(sim.getCurrentRoom(), idxItem-1, wantedX, wantedY);
                sim.getCurrentRoom().getRoomTile().printTile();
            }
            else{
                System.out.println("Tidak bisa memasang barang karena inventory kamu kosong!");
            }  
        }
        else if(idx == 3){
            if(sim.getCurrentRoom().getItemList().size() > 0){
                sim.getCurrentRoom().getRoomTile().printTile();
                listObject(sim.getCurrentWorld());
                System.out.println("Silahkan pilih nomor barang untuk dipindahkan : ");
                int wantedIdx = Integer.parseInt(input.nextLine());
                if(wantedIdx < 0 || wantedIdx > sim.getCurrentRoom().getItemList().size()){
                    System.out.println("Input salah!");
                }
                else{
                    int flagCount = 0;
                    NonFood item = null;
                    for(Item i : sim.getCurrentRoom().getItemList().values()){
                        if(flagCount == wantedIdx -1){
                            item = (NonFood) i;
                        }
                        flagCount++;
                    }
                    System.out.println("Silahkan pilih posisi pojok kiri (X) dari barang yang ingin di pasang : ");
                    int wantedX = Integer.parseInt(input.nextLine());
                    System.out.println("Silahkan pilih posisi pojok kiri (Y) dari barang yang ingin di pasang : ");
                    int wantedY = Integer.parseInt(input.nextLine());
                    if( (wantedX + item.getLength() - 1 > 6) || (wantedY + item.getWidth() - 1 > 6) ){
                        System.out.println("Item tidak bisa diletakkan karena melebihi dimensi ruangan!");
                    }
                    else{
                        boolean flag = false;
                        for(int i = wantedX; i < item.getLength() + wantedX; i++){
                            for(int j = wantedY; j < item.getWidth() + wantedY; j++){
                                if(sim.getCurrentRoom().getRoomTile().getTile(i, j).equals("E") || sim.getCurrentRoom().getRoomTile().getTile(i, j).equals(item.getName())){
                                    flag = true;
                                }
                            }
                        }
                        if(!flag){
                            System.out.println("Terdapat item lain!");
                        }
                        else{
                            sim.getCurrentRoom().removeItem(item.getName());
                            sim.getCurrentRoom().addItem(item.getName(), item);
                            item.setUpperLeft(wantedX, wantedY);
                            for(int i = wantedX; i < item.getLength() + wantedX; i++){
                                for(int j = wantedY; j < item.getWidth() + wantedY; j++){
                                    sim.getCurrentRoom().getRoomTile().changeTile(item.getName(), i, j);
                                }
                            };
                            sim.getCurrentRoom().getRoomTile().printTile();
                        }
                    }
                }
                
            }
            else{
                System.out.println("Tidak bisa memindahkan barang karena tidak ada barang di ruangan ini!");
            }
        }
        else{
            System.out.println("Input salah!");
        }
    }

    public static void addSim(World world, String name, int xHouse, int yHouse) {
        House house = House.newHouse(world, xHouse, yHouse);
        Sim sim = new Sim(name,house, world);
        house.setOwner(sim);
        world.addSimList(sim);
        world.addNewHouse(house);
        sim.setCurrentRoom(house.getRoomList().get(0));
        world.updateMap();
        System.out.println("Sim berhasil ditambahkan!"); 
    }

    public static void changeSim(World world, int idx) {
        if(world.getPlayedSim().equals(world.getSimList().get(idx-1))){
            System.out.println("Anda saat ini sedang memainkan sim " + world.getPlayedSim().getSimName());
        }else{
            world.setPlayedSim(world.getSimList().get(idx-1));
            System.out.println("Berhasil mengganti Sim ke " + world.getSimList().get(idx-1).getSimName());
        }
    }
        

    public static void listObject(World world) {
        Sim sim = world.getPlayedSim();
        System.out.println("Berikut List Objek dalam ruangan : ");
        CLI st = new CLI();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("No.", "Nama Object");
        int idx = 1;
        for(Item i : sim.getCurrentRoom().getItemList().values()){
            st.addRow(String.valueOf(idx), i.getName());
            idx++;
        }
        st.print();
    }

    public static void goToObject(World world, int itemKey) {
        Sim sim = world.getPlayedSim();
        int idx = 0;
        for(Item i : sim.getCurrentRoom().getItemList().values()){
            if(idx == itemKey-1){
                sim.setUseItem(i.getName());
            }
            idx++;
        }
        System.out.println("Sim sedang menggunakan " + sim.getUseItem());
    }

    public static void action(World world, int idxAction) {
        Sim sim = world.getPlayedSim();
        //Scanner input = new Scanner(System.in);
        switch(idxAction){
            case 1 :
                System.out.println("Masukkan durasi bekerja (dalam detik) :");
                int durasiKerja = Integer.parseInt(input.nextLine());
                sim.work(durasiKerja);
                world.updateNotPlayed(durasiKerja);
                break;
            case 2 : 
                System.out.println("List pekerjaan dan biaya yang harus dibayarkan untuk mengganti pekerjaan : "); 
                System.out.println("-- Badut Sulap - 7.5"); 
                System.out.println("-- Koki - 15"); 
                System.out.println("-- Polisi - 17.5"); 
                System.out.println("-- Programmer - 22.5"); 
                System.out.println("-- Dokter - 25"); 
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
                System.out.println("Masukkan durasi olahraga(dalam detik) :");
                int durasiOlahraga = Integer.parseInt(input.nextLine());
                sim.sport(durasiOlahraga);
                world.updateNotPlayed(durasiOlahraga);
                break;
            case 4 :
                if(sim.getUseItem().equals("Single Bed") || sim.getUseItem().equals("Queen Size Bed") || sim.getUseItem().equals("King Size Bed")){
                    int durasiTidur;
                    while (true){
                        System.out.print("Masukkan durasi Tidur (minimal 3 menit): ");
                        durasiTidur = Integer.parseInt(input.nextLine());
                        if(durasiTidur >= 180) {
                            break;
                        }else {
                            System.out.println("Tidur minimal 3 menit tolong masukkan durasi ulang");
                        }
                    }
                    sim.sleep(durasiTidur);
                    world.updateNotPlayed(durasiTidur);
                }
                else{
                    System.out.println("Silahkan pergi ke object 'Bed' untuk tidur!");
                }
                break;
            case 5 :
                if(sim.getUseItem().equals("Table and Chair")){
                    sim.getSimInventory().printInventory(sim.getSimInventory().getFoodItem());
                    while (true){
                        try{
                            System.out.println("Silahkan pilih makanan yang akan dimakan");
                            int idxFood = Integer.parseInt(input.nextLine());
                            if(idxFood > sim.getSimInventory().getFoodItem().size()){
                                System.out.println("Indeks tersebut tidak ada, masukkan indeks dengan benar");
                            }else{
                                int idx = 0;
                                for (Item foodCooked : sim.getSimInventory().getFoodItem().keySet()){
                                    if (idx != idxFood - 1){
                                        idx++;
                                    }else{
                                        sim.eat((Food) foodCooked);
                                        world.updateNotPlayed(30);
                                        sim.getSimInventory().deleteInventory(foodCooked);
                                        break;
                                    }
                                }
                                break;
                            }
                        }catch(Exception e){
                            System.out.println("Masukkan input berupa angka ya bang");
                            break;
                        }
                    }
                }else {
                    System.out.println("Silahkan ke 'Table and Chair' untuk makan");
                }
                break;
            case 6 : 
                if(sim.getUseItem().equals("Gas Stove") || sim.getUseItem().equals("Electric Stove")){
                    CLI.printFoodMenu();
                    System.out.println("Silahkan ketik nomor menu yang akan dimasak");
                    try {
                        int idxMeal = input.nextInt();
                        switch (idxMeal) {
                            case 1:
                                sim.cook("Chicken Rice");
                                break;
                            case 2:
                                sim.cook("Curry Rice");
                                break;
                            case 3: 
                                sim.cook("Nut Milk");
                                break;
                            case 4: 
                                sim.cook("Stir Fry Vegetable");
                                break; 
                            case 5:
                                sim.cook("Steak");
                                break;
                            default:
                                System.out.println("Tolong masukkan nomor sesuai yang tertera pada menu");
                                break;
                        }
                    }catch (Exception e){
                        System.out.println("Tolong masukkan input berupa nomor menu");
                    }
                }
                else{
                    System.out.println("Silahkan pergi ke object 'Stove' untuk masak!");
                }
                break;
            case 7 :
                System.out.println("Daftar rumah Sim untuk dikunjungi :");
                CLI.printHouseAndSim(world);
                world.printMap();
                System.out.println("Ketikan nomor Sim untuk dikunjungi :");
                int idxVisit = Integer.parseInt(input.nextLine());
                if(idxVisit > world.getHouseList().size()){
                    System.out.println("Tidak ada sim di nomor tersebut");
                }else{
                    sim.visit(world.getHouseList().get(idxVisit-1).getLocX(), world.getHouseList().get(idxVisit-1).getLocY(), idxVisit-1);
                }
                break;
            case 8:
                if(sim.getUseItem().equals("Toilet")){
                    sim.pee();
                }
                else{
                    System.out.println("Silahkan pergi ke object 'Toilet' untuk buang air!");
                }
                break;
            case 9:
                if(sim.getUseItem().equals("Clock")){
                    sim.seeTime();
                }
                else{
                    System.out.println("Silahkan pergi ke object 'Clock' untuk lihat waktu!");
                }
                break;
            case 10:
                if(sim.getUseItem().equals("Single Bed") || sim.getUseItem().equals("Queen Size Bed") || sim.getUseItem().equals("King Size Bed")){
                    System.out.println("Seberapa lama anda ingin menangis");
                    int duration = Integer.parseInt(input.nextLine());
                    sim.crying(duration);
                    world.updateNotPlayed(duration);
                }else{
                    System.out.println("Anda harus ke 'Bed' untuk menangis");
                }
                break;
            case 11:
                if(sim.getUseItem().equals("Table and Chair")){
                    System.out.println("Masukkan durasi untuk mengaji : ");
                    int durasiMengaji = Integer.parseInt(input.nextLine());
                    sim.recitate(durasiMengaji);
                    world.updateNotPlayed(durasiMengaji);
                }else {
                    System.out.println("Silahkan pergi ke object 'Table and Chair' untuk mengaji");
                } 
                break;
            case 12:
                if(sim.getCurrentHouse().getOwner().getSimName().equals(sim.getSimName())){
                    System.out.println("Anda sekarang berada di rumah sendiri, jika ingin mencuri silahkan kunjungi rumah Sim lain!");
                    System.out.println("Jika tetap ingin melakukan ini, silahkan ketik '1'. Ketik '2' untuk batalkan");
                    int flag = Integer.parseInt(input.nextLine());
                    if(flag == 1){
                        System.out.println("Daftar barang yang bisa dicuri di ruangan ini : ");
                        listObject(world);
                        System.out.println("Masukkan nomor barang yang ingin dicuri");
                        sim.steal(Integer.parseInt(input.nextLine()));
                    }
                }
                else{
                    System.out.println("Daftar barang yang bisa dicuri di ruangan ini : ");
                    listObject(world);
                    System.out.println("Masukkan nama barang yang ingin dicuri");
                    sim.steal(Integer.parseInt(input.nextLine()));
                }
                break;

            case 13:
                if(sim.getUseItem().equals("Table and Chair")){
                    sim.write();
                    world.updateNotPlayed(10);
                }else {
                    System.out.println("Silahkan pergi ke object 'Table and Chair' untuk menulis");
                } 
                break;
            case 14:
                if(sim.getUseItem().equals("Table and Chair")){
                    sim.read();
                    world.updateNotPlayed(5);
                }else {
                    System.out.println("Silahkan pergi ke object 'Table and Chair' untuk membaca");
                } 
                break;
            case 15:
                if(sim.getUseItem().equals("Table and Chair") || sim.getUseItem().equals("Toilet") || sim.getUseItem().equals("Single Bed") || sim.getUseItem().equals("Queen Size Bed") || sim.getUseItem().equals("King Size Bed")){
                    System.out.println("Masukkan durasi untuk melamun : ");
                    int durasiMelamun = Integer.parseInt(input.nextLine());
                    sim.daydream(durasiMelamun);
                    world.updateNotPlayed(durasiMelamun);
                }else {
                    System.out.println("Silahkan pergi ke object 'Table and Chair' atau 'Toilet' atau 'Bed' untuk melamun");
                } 
                break;
            case 16:
                if(sim.getUseItem().equals("Toilet")){
                    sim.bath();
                    world.updateNotPlayed(20);
                }
                else{
                    System.out.println("Silahkan pergi ke object 'Toilet' untuk mandi!");
                }
                break;
            default: 
                System.out.println("Silahkan pilih nomor dengan benar");
                break;
        }
    }
}
