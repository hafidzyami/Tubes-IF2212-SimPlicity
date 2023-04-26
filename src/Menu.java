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
        CLI.wait(5000);
    }

    public static void exit() {
        System.out.println("Terima kasih telah bermain, sampai jumpa!");
        exit();
    }

    public static void viewSimInfo(World world) {
        Sim sim = world.getPlayedSim(); 
        System.out.println(sim.getSimInfo());
    }

    public static void viewCurrentLocation(Sim sim) {
        System.out.println("Sim berada di rumah" + sim.currentHome + "di dalam ruangan" + sim.currentRoom);
    }

    public static void viewInventory() {
        System.out.println("ini inventory");
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

    public static void addSim() {
        System.out.println("tambah sim");
    }

    public static void changeSim() {
        System.out.println("ganti sim");
    }

    public static void listObject() {
        System.out.println("daftar objek");
    }

    public static void goToObject() {
        System.out.println("pergi ke objek ini");
    }

    public static void Action() {
        System.out.println("ini bantuan");
    }
}
