import java.util.Scanner;

public class CLI {
    private static Scanner input = new Scanner(System.in);
    private static String retS;

    public static void space() {
        System.out.println();
    }
    public static String opening() {
        System.out.println("Selamat datang di Sim-Plicity");
        System.out.println("Silakan ketik startgame atau 1 untuk memulai permainan");
        retS = input.nextLine();
        return retS;
    }

    public static String wrongInput() {
        System.out.println("Input salah,silakan ulangi lagi");
        retS = input.nextLine();
        return retS;
    }

    public static String ListMenu() {
        System.out.println("Berikut adalah menu yang tersedia");
        System.out.println("Silakan pilih nama atau nomor menu yang diinginkan");
        System.out.println("1. startgame");
        System.out.println("2. help");
        System.out.println("3. exit");
        System.out.println("4. view sim info");
        System.out.println("5. view current location");
        System.out.println("6. view inventory");
        System.out.println("7. upgrade house");
        System.out.println("8. move room");
        System.out.println("9. edit room");
        System.out.println("10. add sim");
        System.out.println("11. change sim");
        System.out.println("12. list object");
        System.out.println("13. go to object");
        System.out.println("14. action");
        retS = input.nextLine();
        return retS;
    }
    public static String playing() {
        System.out.println("Masukkan command");
        System.out.println("Tekan 2 atau ketik help untuk memanggil bantuan");
        retS = input.nextLine();
        return retS;
    }

    public static void repeatedStart() {
        System.out.println("Game sudah dimulai");
        System.out.println("silahkan coba command lain");
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }

    public static void wait(int delayMillisec) {
        try {
            Thread.sleep(delayMillisec);
        }
        catch (Exception e) {
            
        }
    }

    public static void printPurchaseAble(){
        System.out.println("==== Non Food Item : ====");
        System.out.println("1. Single Bed - 50");
        System.out.println("2. Queen Size Bed - 100");
        System.out.println("3. King Size Bed - 150");
        System.out.println("4. Toilet - 50");
        System.out.println("5. Gas Stove - 100");
        System.out.println("6. Electric Stove - 200");
        System.out.println("7. Table And Chair - 50");
        System.out.println("==== Food Item : ====");
        System.out.println("8. Rice - 5");
        System.out.println("9. Potato - 3");
        System.out.println("10. Chicken - 10");
        System.out.println("11. Beef - 12");
        System.out.println("12. Carrot - 3");
        System.out.println("13. Spinach  - 3");
        System.out.println("14. Nut - 2");
        System.out.println("15. Milk - 2");


    }
}
