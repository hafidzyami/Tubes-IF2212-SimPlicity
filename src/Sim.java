import java.util.ArrayList;

public class Sim {
    private String fullName;
    private Job job;
    private int money;
    private Inventory<Item> inventory = new Inventory();
    private int hunger;
    private int mood;
    private int health;
    private String status;
    private final Home myHome;
    private int workTime; 

    // ini nyoba
    public Room currentRoom;
    public Home currentHome;
    public World currentWorld;
    
    public Clock clock;
    public String useItem;

    //konstruktor
    public Sim(String nama,Home home, World world) {
        this.fullName = nama;
        this.hunger = 80;
        this.mood = 80;
        this.health = 80;
        this.money = 100;
        this.job = Job.firstJob();
        this.myHome = home;
        this.currentHome = home;
        this.currentWorld = world;
        this.useItem = "";
        // pada awal di buat status bersifat "idle"
        this.status = "idle";
        
    }

    //getter 
    public Home getMyHome(){
        return this.myHome;
    }

    public String getSimName(){
        return this.fullName;
    }

    public String getSimInfo() {
        String rets = "Nama: " + fullName + " \n";
        rets += ("Pekerjaan: " + job.getJobName() + " \n");
        rets += ("Kesehatan: " + health + " \n");
        rets += ("Kekenyangan: " + hunger + " \n");
        rets += ("Mood: " + mood + " \n");
        rets += ("Uang: " + money);
        return rets;
    }

    public Job getSimJob() {
        return job;
    }

    public Inventory<Item> getSimInventory() {
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

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Home getCurrentHome() {
        return currentHome;
    }
        

    //setter 
    public void setIdle(){
        this.status = "idle";
    }

    public void setSimStatus(String status){
        this.status = status;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    //gainer
    public void gainMood(int mood){
        this.mood += mood;
        if (this.mood >= 100){
            this.mood = 100; 
        }
    }

    public void gainHealth(int health){
        this.health += health;
        if (this.health >= 100){
            this.health = 100; 
        }
    }

    public void gainHunger( int hunger){
        this.hunger += hunger;
        if (this.hunger >= 100){
            this.hunger = 100; 
        }
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
            int temp = duration/30; 
            for (int i = 0; i < temp; i++){
            System.out.println("work work work");
            currentWorld.getWorldClock().wait(30);
            gainHunger(-10); 
            gainMood(-10); 
            workTime += 30; 
            if (workTime % 240 == 0){
                gainMoney(getSimJob().getDaySalary()); 
                System.out.println("sim telah selesai bekerja dan mendapatkan "+ getSimJob().getDaySalary()); 
                System.out.println("uang sim menjadi : " + getMoney()); 
            } 
            else {
            System.out.println("sim sudah bekerja selama "+ (float)workTime/60 + " menit.");
            }
            currentWorld.getWorldClock().updateTime(duration);
            }
        }

    }

    public void changeJob(Job newJob){
        this.status = "changing job";
        if (workTime >= 12 * 60){
            System.out.println("Sim akan mengubah pekerjaan " + getSimJob() + " menjadi " + newJob.getJobName()); 
            //sleep 1 detik 
            gainMoney(- 1/2 * newJob.getDaySalary());
            System.out.println("..."); 
            System.out.println("Sim telah mengubah pekerjaanya menjadi " + newJob.getJobName() + "dan membayar sebesar" + 1/2 * newJob.getDaySalary()); 
            System.out.println("Sisa uang sim sekarang : " + getMoney()); 
        } else {
            System.out.println("Sim baru bekerja selama " + workTime);
            System.out.println("Sim harus bekerja selama 12 menit terlebih dahulu sebelum mengganti pekerjaan!");
        }
    }

    public void sport (int duration){
        if(duration % 20 != 0){
            System.out.println("Durasi olahraga harus kelipatan 20 detik!");
        }

        else{
        this.status = "sport";
        int sportTime = 0;
        int temp = duration/20;
        while(sportTime != duration){
            currentWorld.getWorldClock().wait(1);
            sportTime++;
            System.out.println("Sedang olahraga selama " + sportTime + " detik");
        }
        setIdle();
        gainHealth(5*temp);
        gainHunger(-5*temp);
        gainMood(10*temp);     
        }
    }


    public void sleep (int duration){
        this.status = "sleep";
        int sleepTime = 0;
        while(sleepTime != duration){
            currentWorld.getWorldClock().wait(1);
            sleepTime++;
            System.out.println("Sedang tidur selama " + sleepTime + " detik");
        }
        setIdle();
        if(sleepTime >= 4){
            gainMood(30*(duration/4));
            gainHealth(20*(duration/4));
        }
    }

    public void eat (Food food){
        this.status = "eat";
        System.out.println("Sim sedang makan " + food.getName());
        currentWorld.getWorldClock().wait(30); 
        gainHunger(food.getSatiation());
        System.out.println("Sim telah makan!");
        System.out.println("Kekenyagan sim saat ini : " + getSimHunger());
    }

    public void cook (String mealName) {
        System.out.println(this.fullName + " sedang memasak " + mealName);
        this.status = "cooking";
        FoodCooked meal = new FoodCooked(mealName);
        ArrayList<FoodIngredients> ingredients = meal.getIngredientsList();
        currentWorld.getWorldClock().wait((int) 1.5*meal.getSatiation());
        for (Item ingredient : ingredients) {
            for (Item item : inventory.getInventory().keySet()) {
                if (item.getClass().getName().equals("FoodIngredients")) {
                    if (item.equals(ingredient)) {
                        inventory.deleteInventory(item);
                        ingredients.remove(ingredient);
                    }
                }
            }
        };
        
        if (ingredients.isEmpty()) {
            inventory.addInventory(meal);
            System.out.println(meal.getName() + " ditambahkan ke inventory!");
        } else {
            System.out.println(meal.getName() + " tidak jadi karena bahan kurang!");
        }
    }

    public void visit (int x2, int y2, int idxHome){
        if(currentWorld.getHomeList().get(idxHome).getOwner().getSimName().equals(currentHome.getOwner().getSimName())){
            System.out.println("Tidak bisa berkunjung ke rumah yang sama!");
        }
        else{
            this.status = "onTheWay";
            int x1 = currentHome.getLocX();
            int y1 = currentHome.getLocY();
            double distance = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
            int tick = 0;
            System.out.println("Sim berkunjung ke rumah " + currentWorld.getHomeList().get(idxHome).getOwner() + "dengan durasi : " + distance + " detik");
            while(getSimStatus().equals("onTheWay")) {
                    System.out.println("Sim sedang dalam perjalanan!");
                    currentWorld.getWorldClock().wait(1);
                    tick++;
                    if(tick >= distance){
                        System.out.println("Sim sudah sampai!");
                        setSimStatus("idle");
                        currentHome = currentWorld.getHomeList().get(idxHome-1);
                        tick = 0;
                    }
                // ini gw masih bingung
                // gainMood(10*(duration/30));
                // gainHunger(-10*(duration/30));
            }
        }
    }

    public void pee() {
        this.status = "pee";
        System.out.println("Sim sedang buang air");
        currentWorld.getWorldClock().wait(10);
        gainMood(10);
        System.out.println("Sim telah buang air selama 10 detik");
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
                        Thread.sleep(0);
                        gainMoney(-item.getPrice());
                        System.out.println("sim membeli" + item.getName() + "dengan harga" + item.getPrice());
                        int deliveryTime = (int) (Math.random() * 5 * 1) * 30;
                        System.out.println("barang akan tersedia dalam waktu "+ deliveryTime + " detik, silahkan menunggu");
                        try{
                            Thread.sleep(deliveryTime);
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                        inventory.addInventory((Item) item);
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
    public void moveToRoom(int idx) {
        if(this.currentHome.getRoomList().get(idx-1).equals(currentRoom)){
            System.out.println("Tidak bisa berpindah ke room yang sama!");
        }
        else {
            this.currentRoom = this.currentHome.getRoomList().get(idx-1);
            System.out.println("Anda diteleportasi ke " + this.currentRoom.getRoomName());
        }
    }

    public void seeInventory() {
        inventory.printInventory(inventory.getInventory());
    }

    public void installItem(Room room, int idxItem, int wantedX, int wantedY){
        NonFoodItem item = this.inventory.getOneNFI(idxItem);
        
        if( (wantedX + item.getLength() - 1 > 6) || (wantedY + item.getWidth() - 1 > 6) ){
            System.out.println("Item tidak bisa diletakkan!");
        }
        else{
            boolean flag = false;
            for(int i = wantedX; i < item.getLength() + wantedX; i++){
                for(int j = wantedY; j < item.getWidth() + wantedY; j++){
                    if(!room.getRoomTile().getTile(i, j).equals("E")){
                        flag = true;
                    }
                }
            }
            if(flag){
                System.out.println("Terdapat item lain!");
            }
            else{
                // ini harus dihapus item dari inventory tapi error
                this.inventory.deleteInventory(item);
                currentRoom.addItem(item.getName(), item);
                item.setUpperLeft(wantedX, wantedY);
                for(int i = wantedX; i < item.getLength() + wantedX; i++){
                    for(int j = wantedY; j < item.getWidth() + wantedY; j++){
                        room.getRoomTile().changeTile(item.getName(), i, j);
                    }
                };
            }
        }

    }

    public void seeTime() {
        this.status = "see time";
        System.out.println("Sim sedang melihat waktu");
        //Menu.goToObject(); //ke clock, nungguin alta our superhero
        System.out.println("waktu hari ini tersisa " + currentWorld.getWorldClock().getSisaWaktu());
    }

    //costum action 
    public void crying() {
        this.status = "crying"; 
        System.out.println("Sim akan menangis karena stress");
        currentWorld.getWorldClock().wait(1);
        gainHunger(-10);
        gainMood(10);
        System.out.println("Sim sudah selesai menangis :(");
    }

    public void recitate(int duration){
        this.status = "recitate";
        int recitateTime = 0;
        int temp = duration/60;
        while(recitateTime != duration){
            currentWorld.getWorldClock().wait(1);
            recitateTime++;
            System.out.println("Sedang mengaji selama " + recitateTime + " detik");
        }
        setIdle();
        gainMood(3*temp);
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

    public void daydream(int duration){
        this.status = "dayDream";
        int dayDreamTime = 0;
        int temp = duration/30;
        while(dayDreamTime != duration){
            currentWorld.getWorldClock().wait(1);
            dayDreamTime++;
            System.out.println("Sedang melamun selama " + dayDreamTime + " detik");
        }
        setIdle();
        gainMood(3*temp);
        gainHunger(-3*temp);

    }

    public void write() {
        if (this.status.equals("sitting")) {
            currentWorld.getWorldClock().wait(10);
            System.out.println(this.fullName + " sedang menulis halaman baru di diary");
        } else {
            System.out.println(this.status + "sedang tidak duduk");
        }
    }

    public void read() {
        if (this.status.equals("sitting")) {
            currentWorld.getWorldClock().wait(5);
            System.out.println(this.fullName + " sedang membaca diary");
        } else {
            System.out.println(this.status + "sedang tidak duduk");
        }
    }

    //driver
    public static void main(String[] args) {
        // World world = World.getInstance();
        // Home home = Home.newHome(world);
        // Sim Bobi = new Sim("Bobi",home);
        // System.out.println(Bobi.getSimInfo());
        // Bobi.currentHome = new Home().newHome(world);
        // Bobi.currentRoom = Bobi.currentHome.getRoomList().get("ruang01");
        // System.out.println(Bobi.currentRoom.getRoomName());
        // System.out.println(Bobi.currentRoom.getItemList());

        // // buat ruangan baru di rumah bobi
        // Room ruang02 = new Room("dapur",Bobi.getCurrentHome());
        // Bobi.currentHome.addRoom("ruang02", ruang02);

        // // coba pindah ke dapur atau room02
        // Bobi.moveToRoom("ruang02");
        // System.out.println(Bobi.currentRoom.getRoomName());
        // System.out.println(Bobi.currentRoom.getItemList());

        // // pindah ke main room
        // Bobi.moveToRoom("ruang01");
        // System.out.println(Bobi.currentRoom.getRoomName());
        // System.out.println(Bobi.currentRoom.getItemList());


        // // Bobi mencuri ih maling, ternyata bisa ges
        // Bobi.steal("kursi01");
        // System.out.println(Bobi.currentRoom.getItemList());
        // System.out.println(Bobi.inventory.getInventory());

        // // bobi coba mencuri yang gaada barangnya
        // Bobi.steal("kursi01");

        // // bobi coba nangis 
        // Bobi.crying();

        // // print tile dapur
        // Bobi.currentRoom.getRoomTile().printTile();

        // // pasang barang di dapur
        // NonFoodItem nfi = (NonFoodItem) Bobi.inventory.getItem(0);
        // // Bobi.installItem(Bobi.currentRoom, nfi, 1, 1);
        // Bobi.currentRoom.getRoomTile().printTile();
        
        // //bobi makan
        // FoodCooked makan = new FoodCooked("Nut Milk");
        // Bobi.eat(makan);
        
    }

}
