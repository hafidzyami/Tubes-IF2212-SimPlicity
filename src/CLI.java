import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CLI {
    private static Scanner input = new Scanner(System.in);
    private static String retS;
    private static final String HORIZONTAL_SEP = "-";
    private String verticalSep;
    private String joinSep;
    private String[] headers;
    private List<String[]> rows = new ArrayList<>();
    private boolean rightAlign;

    public CLI() {
        setShowVerticalLines(false);
    }

    public void setRightAlign(boolean rightAlign) {
        this.rightAlign = rightAlign;
    }

    public void setShowVerticalLines(boolean showVerticalLines) {
        verticalSep = showVerticalLines ? "|" : "";
        joinSep = showVerticalLines ? "+" : " ";
    }

    public void setHeaders(String... headers) {
        this.headers = headers;
    }

    public void addRow(String... cells) {
        rows.add(cells);
    }

    public void print() {
        int[] maxWidths = headers != null ?
                Arrays.stream(headers).mapToInt(String::length).toArray() : null;

        for (String[] cells : rows) {
            if (maxWidths == null) {
                maxWidths = new int[cells.length];
            }
            if (cells.length != maxWidths.length) {
                throw new IllegalArgumentException("Number of row-cells and headers should be consistent");
            }
            for (int i = 0; i < cells.length; i++) {
                maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
            }
        }

        if (headers != null) {
            printLine(maxWidths);
            printRow(headers, maxWidths);
            printLine(maxWidths);
        }
        for (String[] cells : rows) {
            printRow(cells, maxWidths);
        }
        if (headers != null) {
            printLine(maxWidths);
        }
    }

    private void printLine(int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            String line = String.join("", Collections.nCopies(columnWidths[i] +
                    verticalSep.length() + 1, HORIZONTAL_SEP));
            System.out.print(joinSep + line + (i == columnWidths.length - 1 ? joinSep : ""));
        }
        System.out.println();
    }

    private void printRow(String[] cells, int[] maxWidths) {
        for (int i = 0; i < cells.length; i++) {
            String s = cells[i];
            String verStrTemp = i == cells.length - 1 ? verticalSep : "";
            if (rightAlign) {
                System.out.printf("%s %" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            } else {
                System.out.printf("%s %-" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //test code
        CLI st = new CLI();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("one", "two", "three");//optional - if not used then there will be no header and horizontal lines
        st.addRow("super", "broccoli", "flexible");
        st.addRow("assumption", "announcement", "reflection");
        st.addRow("logic", "pleasant", "wild");
        st.print();
    }


    public static void space() {
        System.out.println();
    }
    public static String opening() {
        System.out.println("\u001B[35m" + "  ________   __  _______  _____________   ___  ______" );
        System.out.println(" / ___/ _ | /  |/  / __/ / __/_  __/ _ | / _ \\/_  __/");
        System.out.println("/ (_ / __ |/ /|_/ / _/  _\\ \\  / / / __ |/ , _/ / /   ");
        System.out.println("\\___/_/ |_/_/  /_/___/ /___/ /_/ /_/ |_/_/|_| /_/    \n"+ "\u001B[0m");
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
        CLI st = new CLI();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("No.", "Menu");

        st.addRow("1.", "startgame");
        st.addRow("2.", "help");
        st.addRow("3.", "exit");
        st.addRow("4.", "view sim info");
        st.addRow("5.", "view current location");
        st.addRow("6.", "view inventory");
        st.addRow("7.", "upgrade house");
        st.addRow("8.", "move room");
        st.addRow("9.", "edit room");
        st.addRow("10.", "add sim");
        st.addRow("11.", "change sim");
        st.addRow("12.", "list object");
        st.addRow("13.", "go to object");
        st.addRow("14.", "action");
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

    public static void printNonFoodItem(){
        CLI st = new CLI();

        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        System.out.println("+-----+-----------------+-------+");
        System.out.println("|           Furniture           |");
        st.setHeaders("No.", "Nama", "Price");
        st.addRow("1.","Single Bed", "50");
        st.addRow("2." ,"Queen Size Bed", "100");
        st.addRow("3." ,"King Size Bed", "150");
        st.addRow("4." ,"Toilet", "50");
        st.addRow("5." ,"Gas Stove", "100");
        st.addRow("6." ,"Electric Stove", "200");
        st.addRow("7." ,"Table And Chair", "50");
        st.addRow("8." ,"Clock", "10");
        st.print();
    }

    public static void printFoodIngredient() {
        CLI st = new CLI();
        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        System.out.println("+-----+----------+-------+");
        System.out.println("|     Food Ingredients   |");
        st.setHeaders("No.", "Nama", "Price");
        st.addRow("1.","Rice","5");
        st.addRow("2.","Potato","3");
        st.addRow("3.","Chicken","10");
        st.addRow("4.","Beef","12");
        st.addRow("5.","Carrot","3");
        st.addRow("6.","Spinach ","3");
        st.addRow("7.","Nut","2");
        st.addRow("8.","Milk","2");
        st.print();
    }

    public static void printHouseAndSim(World world){
        for(int i = 0; i < world.getHouseList().size(); i++){
            System.out.println((i+1) + ". " + world.getHouseList().get(i).getOwner().getSimName());
        }
    }

    public static void printFoodMenu(){
        CLI st = new CLI();

        //st.setRightAlign(true);//if true then cell text is right aligned
        st.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        st.setHeaders("No.", "Nama", "Recipe");
        System.out.println("Berikut daftar menu: ");
        st.addRow("1.", "Chicken Rice", "Rice + Chicken");
        st.addRow("2.", "Curry Rice", "Rice + Potato + Carrot + Beef");
        st.addRow("3.", "Nut Milk", "Milk + Nut");
        st.addRow("4.", "Stir Fry Vegetable", "Carrot + Spinach");
        st.addRow("5.", "Steak", "Beef + Potato");
        st.print();
    }

    public static void printActionList(){
        System.out.println("==== Daftar Action : ====");
        System.out.println("1. Kerja");
        System.out.println("2. Ganti Pekerjaan");
        System.out.println("3. Olahraga");
        System.out.println("4. Tidur - Bed");
        System.out.println("5. Makan - Table and Chair");
        System.out.println("6. Memasak - Stove");
        System.out.println("7. Berkunjung");
        System.out.println("8. Buang Air - Toilet");
        System.out.println("9. Melihat Waktu - Clock");
        System.out.println("10. Aksi Custom : Menangis - Bed");
        System.out.println("11. Aksi Custom : Mengaji - Table and Chair");
        System.out.println("12. Aksi Custom : Mencuri");
        System.out.println("13. Aksi Custom : Menulis - Table and Chair");
        System.out.println("14. Aksi Custom : Membaca - Table and Chair");
        System.out.println("15. Aksi Custom : Melamun - Table and Chair, Toilet, Bed");
        System.out.println("16. Aksi Custom : -");
    }
}
