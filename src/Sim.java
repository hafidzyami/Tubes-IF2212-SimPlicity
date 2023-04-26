import java.awt.Point;

public class Sim implements Runnable {
    private String fullName;
    private String job;
    private int money;
    private Inventory inventory = new Inventory();
    private int hunger;
    private int mood;
    private int health;
    private String status;

    // ini nyoba
    public Room currentRoom;
    public Home currentHome;
    public Job simJob;
    public Clock clock;

    //konstruktor
    public Sim(String nama,Item ... items) {
        this.fullName = nama;
        this.hunger = 80;
        this.mood = 80;
        this.health = 80;
        this.money = 100;
        this.job = "sopir";
        
        // pada awal di buat status bersifat "idle"
        this.status = "idle";
        
    }
    public void run() {
        try {
            Thread.sleep(5000);
        }
        catch (Exception e){

        }
        System.out.println(fullName + " Sedang beraktivitas");
    }

    //getter 
    public String getSimInfo() {
        String rets = "Nama: " + fullName + " \n";
        rets += ("Pekerjaan: " + job + " \n");
        rets += ("Kesehatan: " + health + " \n");
        rets += ("Kekenyangan: " + hunger + " \n");
        rets += ("Mood: " + mood + " \n");
        rets += ("Uang: " + money);
        return rets;
    }

    public String getSimJob() {
        return job;
    }

    public Inventory getSimInventory() {
        return inventory;
    }

    public int getSimHunger() {
        return hunger;
    }

    public int getSimMood() {
        return mood;
    }

    public int getSimHealth() {
        return health;
    }

    public String getSimStatus() {
        return status;
    }

    public int getMoney(){
        return money; 
    }

    //setter 
    public void setIdle(){
        this.status = "idle";
    }

    public void gainMood(int mood){
        this.mood += mood;
    }

    public void gainHealth(int health){
        this.health += health;
    }

    public void gainHunger( int hunger){
        this.hunger += hunger;
    }

    public void gainMoney(int money){
        this.money += money; 
    }

    // active action 
    public void work (int duration){
        if (duration % 120 != 0){
            System.out.println("durasi bekerja yang dimasukan harus berupa kelipatan 120!");
        } else {
            this.status = "work"; 
            System.out.println("Sim sedang bekerja sebagai " + getSimJob());
            Thread t = new Thread(new Runnable(){
                int timeWork = 0;
                public void run(){
                    int temp = duration/30; 
                    for (int i = 0; i < temp; i++){
                        try{
                            System.out.println("work work work");
                            Thread.sleep(30 * 1000);
                            gainHunger(-10); 
                            gainMood(-10); 

                            timeWork += 30; 
                            if (timeWork == 240){
                                gainMoney(simJob.getDaySalary()); 
                                System.out.println("sim telah selesai bekerja dan mendapatkan "+ simJob.getDaySalary()); 
                                System.out.println("uang sim menjadi : " + getMoney()); 
                            } else {
                                System.out.println("sim sudah bekerja selama "+ (float)timeWork/60 + "menit");
                            }
                        }
                        catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
            t.start(); 
        }

    }

    public void sport (int duration){
        this.status = "sport";
        Thread t = new Thread(new Runnable(){
            public void run(){
                int sportTime = 0;
                int temp = duration/20;
                while(sportTime != duration){
                    try{
                        Thread.sleep(1000); 
                        sportTime++;
                        System.out.println("Sedang olahraga selama " + sportTime + " detik");
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                setIdle();
                gainHealth(5*temp);
                gainHunger(-5*temp);
                gainMood(10*temp);
            } 
        });
        t.start();
    }


    public void sleep (int duration){
        this.status = "sleep";
        Thread t = new Thread(new Runnable(){
            public void run(){
                int sleepTime = 0;
                while(sleepTime != duration){
                    try{
                        Thread.sleep(1000); 
                        sleepTime++;
                        System.out.println("Sedang tidur selama " + sleepTime + " detik");
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }

                setIdle();
                
                if(sleepTime >= 3){
                    gainMood(30*(duration/4));
                    gainHealth(20*(duration/4));
                }
            } 
        });

        t.start();
    }

    public void eat (FoodCooked food){
        this.status = "makan";
        Thread t = new Thread(new Runnable(){
            public void run(){
                
            } 
        });

        t.start();
    }

    public void cook () {

    }

    public void visit (int duration){
       
    }

    public void pee() {
        this.status = "pee";
        System.out.println("Sim sedang buang air");
        Thread t = new Thread(new Runnable(){
            public void run(){
                try{
                    Thread.sleep(10000); 
                    gainHunger(-20);
                    gainMood(10);
                    System.out.println("Sim telah buang air selama 10 detik");
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            } 
        });

        t.start();
    }

    // needed time action 
    public void upgradeHome() {

    }

    public void buyItem(PurchaseAble item) {
        this.status = "buying item"; 
        Thread t = new Thread(new Runnable(){
            public void run(){
                try {
                    if (item != null && item.getPrice() <= getMoney()) {
                        Thread.sleep(10000); 
                        gainMoney(-item.getPrice());
                        System.out.println("sim membeli" + item.getName() + "dengan harga" + item.getPrice());
                        int deliveryTime = (int) (Math.random() * 5 * 1) * 30;
                        System.out.println("barang akan tersedia dalam waktu "+ deliveryTime + "detik, silahkan menunggu");
                        try{
                            Thread.sleep(deliveryTime);
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        inventory.addInventory(item);
                    } else {
                        System.out.println("uang sim tidak cukup!");
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }

    //not needed time action 
    public void moveToRoom(String roomName) {
        if(this.currentRoom.getRoomName().equals(roomName)){
            System.out.println("Tidak bisa berpindah ke room yang sama!");
        }
        else if(this.currentHome.getRoomList().containsKey(roomName)){
            this.currentRoom = this.currentHome.getRoomList().get(roomName);
            System.out.println("Anda diteleportasi ke " + roomName);
        }
        else{
            System.out.println("Tidak ada ruang dengan nama tersebut!");
        }
    }

    public void seeInventory() {
        inventory.printInventory();
    }

    public void installItem(Tile tile, String name, Point upperLeft, int width, int length){
        //jujur gw gatau ini pake thread atau engga
        if( ((int) upperLeft.getX() + length - 1 > 6) || ((int) upperLeft.getY() + width - 1 > 6) ){
            System.out.println("Item tidak bisa diletakkan!");
        }
        else{
            boolean flag = false;
            for(int i = (int) upperLeft.getX(); i < length + (int) upperLeft.getX(); i++){
                for(int j = (int) upperLeft.getY(); j < width + (int) upperLeft.getY(); j++){
                    if(!tile.getTile(i, j).equals("E")){
                        flag = true;
                    }
                }
            }
            if(flag){
                System.out.println("Terdapat item lain!");
            }
            else{
                for(int i = (int) upperLeft.getX(); i < length + (int) upperLeft.getX(); i++){
                    for(int j = (int) upperLeft.getY(); j < width + (int) upperLeft.getY(); j++){
                        tile.changeTile(name, i, j);
                    }
                };
            }
        }

    }

    public void seeTime() {
        this.status = "see time";
        System.out.println("Sim sedang melihat waktu");
        Menu.goToObject(); //ke clock
        /* menunjukkan sisa waktu pada hari tersebut 
        beserta sisa waktu yang masih ada untuk seluruh tindakan yang bisa ditinggal 
        (Contoh: Upgrade rumah tapi sudah berlalu waktu 10 menit, 
        maka seharusnya waktu yang masih ada adalah 8 menit).*/ 
        Thread t = new Thread(new Runnable(){
            public void run(){
                try{
                    Thread.sleep(10000); 
                    System.out.println("Harini hanya tersisa waktu" + clock.getTime());
                    //gimana cara tau sisa waktu untuk aksi yang bisa ditinggal ya allah :(
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            } 
        });

        t.start();

    }

    //costum action 
    public void crying() {
        this.status = "crying"; 
        Thread t = new Thread(new Runnable(){
            public void run(){
                try{
                    Thread.sleep(1000); 
                    gainHunger(-10);
                    gainMood(10);
                    System.out.println("Sim sedang menangis :(");
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

            } 
        });

        t.start();
    }

    public void recitate(int duration){
        this.status = "recitate";
        Thread t = new Thread(new Runnable(){
            public void run(){
                int recitateTime = 0;
                int temp = duration/60;
                while(recitateTime != duration){
                    try{
                        Thread.sleep(1000); 
                        recitateTime++;
                        System.out.println("Sedang mengaji selama " + recitateTime + " detik");
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                setIdle();
                gainMood(3*temp);
            } 
        });
        t.start();
    }

    public void steal(String itemName){
        if(currentRoom.getItemList().containsKey(itemName)){
            this.inventory.addInventory(currentRoom.getItemList().get(itemName));
            currentRoom.removeItem(itemName);
            System.out.println("Kamu berhasil mencuri barang " + itemName + ", silahkan lihat di inventory kamu!");
        }
        else{
            System.out.println("Tidak bisa mencuri barang tersebut karena barang tidak ada!");
        }
    }

    //driver
    public static void main(String[] args) {
        Sim Bobi = new Sim("Bobi");
        System.out.println(Bobi.getSimInfo());
        Bobi.currentHome = new Home().newHome();
        Bobi.currentRoom = Bobi.currentHome.getRoomList().get("ruang01");
        System.out.println(Bobi.currentRoom.getRoomName());
        System.out.println(Bobi.currentRoom.getItemList());

        // buat ruangan baru di rumah bobi
        Room ruang02 = new Room("dapur");
        Bobi.currentHome.addRoom("ruang02", ruang02);

        // coba pindah ke dapur atau room02
        Bobi.moveToRoom("ruang02");
        System.out.println(Bobi.currentRoom.getRoomName());
        System.out.println(Bobi.currentRoom.getItemList());

        // pindah ke main room
        Bobi.moveToRoom("ruang01");
        System.out.println(Bobi.currentRoom.getRoomName());
        System.out.println(Bobi.currentRoom.getItemList());


        // Bobi mencuri ih maling, ternyata bisa ges
        Bobi.steal("kursi01");
        System.out.println(Bobi.currentRoom.getItemList());
        System.out.println(Bobi.inventory.getInventory());

        // bobi coba mencuri yang gaada barangnya
        Bobi.steal("kursi01");

        
    }

}
