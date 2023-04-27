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

    public static void upgradeHouse() {
        System.out.println("rumah sedang diupgrade");
    }

    public static void moveRoom() {
        System.out.println("pindah ruang");
    }

    public static void editRoom() {
        System.out.println("edir ruang");
    }

    public static void addSim(World world, String name) {
        Home home = Home.newHome(world);
        Sim sim = new Sim(name,home);
        home.setOwner(sim);
        world.addSimList(sim);
        sim.setCurrentRoom(home.getRoomList().get("ruang01"));
        System.out.println("Sim berhasil ditambahkan!");
    }

    public static void changeSim(World world, int idx) {
        world.setPlayedSim(world.getSimList().get(idx-1));
        System.out.println("Berhasil mengganti Sim ke " + world.getSimList().get(idx-1).getSimName());
    }

    public static void listObject(World world) {
        Sim sim = world.getPlayedSim();
        System.out.println("Berikut List Objek dalam ruangan");
        for(Item i : sim.currentRoom.getItemList().values()){
            System.out.println(i.getName());
        }
    }

    public static void goToObject(World world, String itemKey) {
        Sim sim = world.getPlayedSim();
        sim.useItem = sim.currentRoom.getItemList().get(itemKey);
        System.out.println("pergi ke objek ini " + sim.useItem.getName());
    }

    public static void action() {
        System.out.println("ini aksi");
    }

    public static void changeJob(String nama) {

    }
}
