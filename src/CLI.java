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
        System.out.println("Silakan pilih nama atau nomor menu yang diiinginkan");
        System.out.println("1. help");
        System.out.println("2. exit");
        System.out.println("3. view sim info");
        System.out.println("4. view current location");
        System.out.println("5. view inventory");
        System.out.println("6. upgrade house");
        System.out.println("7. move room");
        System.out.println("8. edit room");
        System.out.println("9. add sim");
        System.out.println("10. change sim");
        System.out.println("11. list object");
        System.out.println("12. go to object");
        System.out.println("13. action");
        retS = input.nextLine();
        return retS;
    }
}
