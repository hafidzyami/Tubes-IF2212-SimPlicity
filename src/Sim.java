import java.util.ArrayList;

public class Sim {
    private String fullName;
    private Job job;
    private double money;
    private Inventory<Item> inventory = new Inventory<Item>();
    private int hunger;
    private int mood;
    private int health;
    private String status;
    private final House myHouse;
    private int workTime; 
    // Location
    private Room currentRoom;
    private House currentHouse;
    private static World currentWorld;
    private String useItem;

    //konstruktor
    public Sim(String nama,House house, World world) {
        this.fullName = nama;
        this.hunger = 80;
        this.mood = 80;
        this.health = 80;
        this.money = 10000;
        this.job = Job.firstJob();
        this.myHouse = house;
        this.currentHouse = house;
        Sim.currentWorld = world;
        this.useItem = "";
        // pada awal di buat status bersifat "idle"
        this.status = "idle";
        
    }

    //getter 
    public House getMyHouse(){
        return this.myHouse;
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

    public double getSimMoney(){
        return money; 
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public House getCurrentHouse() {
        return currentHouse;
    }

    public String getUseItem() {
        return useItem;
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

    public void setSimJob(Job newJob){
        this.job = newJob; 
    }

    public void setUseItem(String item) {
        this.useItem = item;
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

    public void gainMoney(double money){
        this.money += money; 
    }

 
    // active action 
    public void work (int duration){
        if (duration % 120 != 0){
            System.out.println("durasi bekerja yang dimasukan harus berupa kelipatan 120 detik!");
        } else {
            setSimStatus("work"); 
            System.out.println("Sim sedang bekerja sebagai " + getSimJob().getJobName());
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
                    System.out.println("uang sim menjadi : " + getSimMoney()); 
                } 
                else {
                    System.out.println("sim sudah bekerja selama "+ workTime + " detik.");
                }
                currentWorld.getWorldClock().updateTime(duration);
            }
        }
        setIdle();
    }

    public void changeJob(Job newJob){
        setSimStatus("changing job");
        if (newJob.getJobName().equals(getSimJob().getJobName())){
            System.out.println("Pekerjaan sim sudah sama!"); 
        } else {
            if (workTime >= 720){
                System.out.println("Sim akan mengubah pekerjaan " + getSimJob().getJobName() + " menjadi " + newJob.getJobName()); 
                setSimJob(newJob);
                System.out.println(getSimJob().getJobName()); 
                System.out.println(getSimJob().getDaySalary());
                double cost = 0.5 * getSimJob().getDaySalary();
                System.out.println(cost);
                gainMoney(-cost);
                System.out.println("..."); 
                System.out.println("Sim telah mengubah pekerjaanya menjadi " + getSimJob().getJobName() + " dan membayar sebesar " + cost); 
                System.out.println("Sisa uang sim sekarang : " + getSimMoney()); 
            } else {
                System.out.println("Sim baru bekerja selama " + workTime);
                System.out.println("Sim harus bekerja selama 12 menit terlebih dahulu sebelum mengganti pekerjaan!");
            }
        }
        setIdle();
    }

    public void sport (int duration){
        if(duration % 20 != 0){
            System.out.println("Durasi olahraga harus kelipatan 20 detik!");
        }

        else{
        setSimStatus("sport");
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
        currentWorld.getWorldClock().updateTime(duration); 
        setIdle();
    }


    public void sleep (int duration){
        setSimStatus("sleep");
        int sleepTime = 0;
        System.out.println("Sim mulai tidur");
        while(sleepTime != duration){
            currentWorld.getWorldClock().wait(60);
            sleepTime++;
            System.out.println("Sedang tidur selama " + sleepTime + " menit");
            currentWorld.getWorldClock().updateTime(60); 
        }
        setIdle();
        if(sleepTime >= 4){
            gainMood(30*(duration/4));
            gainHealth(20*(duration/4));
        }
        setIdle();
    }

    public void eat (Food food){
        setSimStatus("eating");
        System.out.println("Sim sedang makan " + food.getName());
        currentWorld.getWorldClock().wait(30); 
        gainHunger(food.getSatiation());
        System.out.println("Sim telah makan!");
        System.out.println("Kekenyagan sim saat ini : " + getSimHunger());
        currentWorld.getWorldClock().updateTime(30); 
        setIdle();
    }

    public void cook (String mealName) {
        setSimStatus("cooking");
        FoodCooked meal = new FoodCooked(mealName);
        ArrayList<FoodIngredients> ingredients = meal.getIngredientsList();
        if(ingredients.size() > 0){
            ArrayList<String> listCek = new ArrayList<>();
            ArrayList<String> listTemp = new ArrayList<>();
            for (int i = 0; i < ingredients.size(); i++) {
                listCek.add(ingredients.get(i).getName());
                listTemp.add(ingredients.get(i).getName());
            }
            for (int i = 0; i < inventory.getInventory().size(); i++) {
                if (listCek.contains(inventory.getItem(i).getName())) {
                    listCek.remove(inventory.getItem(i).getName());
                }
            }
        
            if (listCek.size() == 0 && ingredients.size() > 0) {
                for (int i = 0; i < inventory.getInventory().size(); i++) {
                    if (listTemp.contains(inventory.getItem(i).getName())) {
                        inventory.deleteInventory(inventory.getItem(i));
                    }
                }
                int duration = (int) Math.round(1.5*meal.getSatiation());
                System.out.println(this.fullName + " sedang memasak " + mealName + " dalam waktu " + duration + " detik");
                currentWorld.getWorldClock().wait(duration);
                inventory.addInventory(meal);
                System.out.println(meal.getName() + " ditambahkan ke inventory!");
                currentWorld.getWorldClock().updateTime((int) Math.round(1.5*meal.getSatiation())); 
            } else {
                System.out.println(meal.getName() + " tidak jadi karena bahan kurang!");
            }
        }else{
            System.out.println("Makanan tidak ada di menu. Silahkan pilih makanan yang ada di menu");
        }
        setIdle();
    }

    public void visit (int x2, int y2, int idxHouse){
        if(currentWorld.getHouseList().get(idxHouse).getOwner().getSimName().equals(currentHouse.getOwner().getSimName())){
            System.out.println("Tidak bisa berkunjung ke rumah yang sama!");
        }
        else{
            setSimStatus("on the way");
            int x1 = currentHouse.getLocX();
            int y1 = currentHouse.getLocY();
            double distance = Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
            int tick = 0;
            System.out.println("Sim berkunjung ke rumah " + currentWorld.getHouseList().get(idxHouse).getOwner().getSimName() + " dengan durasi : " + distance + " detik");
            while(getSimStatus().equals("onTheWay")) {
                    System.out.println("Sim sedang dalam perjalanan!");
                    currentWorld.getWorldClock().wait(1);
                    tick++;
                    if(tick >= distance){
                        System.out.println("Sim sudah sampai!");
                        setSimStatus("idle");
                        currentHouse = currentWorld.getHouseList().get(idxHouse);
                        currentRoom = currentHouse.getRoomList().get(0);
                        tick = 0;
                    }
                // ini gw masih bingung
                // gainMood(10*(duration/30));
                // gainHunger(-10*(duration/30));
            }
            setIdle();
        }
    }

    public void pee() {
        setSimStatus("pee");
        System.out.println("Sim sedang buang air");
        currentWorld.getWorldClock().wait(10);
        gainMood(10);
        System.out.println("Sim telah buang air selama 10 detik");
        currentWorld.getWorldClock().updateTime(10);
        setIdle();
    }

    // needed time action 

    public void buyItem(PurchaseAble item) {
        setSimStatus("buying item");
        Thread t = new Thread(new Runnable(){
            public void run(){
                int start = currentWorld.getWorldClock().getTotalElapsed();
                int now;
                if (item != null && item.getPrice() <= getSimMoney()) {
                    gainMoney(-item.getPrice());
                    System.out.println("sim membeli '" + item.getName() + "'' dengan harga : " + item.getPrice());
                    int deliveryTime = ((int) (Math.random() * 5 * 1) + 1) * 30;
                    System.out.println("barang akan tersedia dalam waktu "+ deliveryTime + " detik, silahkan menunggu");
                    while (true) {
                        now = currentWorld.getWorldClock().getTotalElapsed();
                        if (now >= start + deliveryTime){
                            System.out.println(item.getName() + " sudah masuk ke inventory");
                            inventory.addInventory((Item) item);
                            break;
                        }
                        try {
                            Thread.sleep(1000);
                        }
                        catch (Exception e) {

                        }
                    }
                } else {
                    System.out.println("uang sim tidak cukup!");
                }
            }
        });
        t.start();
        setIdle();
    }

    //not needed time action 
    public void moveToRoom(int idx) {
        if(this.currentHouse.getRoomList().get(idx-1).equals(currentRoom)){
            System.out.println("Tidak bisa berpindah ke room yang sama!");
        }
        else {
            setSimStatus("moving");
            this.currentRoom = this.currentHouse.getRoomList().get(idx-1);
            System.out.println("Anda diteleportasi ke " + this.currentRoom.getRoomName());
            setIdle();
        }
    }

    public void seeInventory() {
        setSimStatus("see invenroty");
        inventory.printInventory(inventory.getInventory());
        setIdle();
    }

    public void installItem(Room room, int idxItem, int wantedX, int wantedY){
        NonFoodItem item = this.inventory.getOneNFI(idxItem);
        
        if( (wantedX + item.getLength() - 1 > 6) || (wantedY + item.getWidth() - 1 > 6) ){
            System.out.println("Item tidak bisa diletakkan!");
        }
        else{
            setSimStatus("installing item");
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
                this.inventory.deleteInventory(item);
                currentRoom.addItem(item.getName(), item);
                item.setUpperLeft(wantedX, wantedY);
                for(int i = wantedX; i < item.getLength() + wantedX; i++){
                    for(int j = wantedY; j < item.getWidth() + wantedY; j++){
                        room.getRoomTile().changeTile(item.getName(), i, j);
                    }
                };
            }
            setIdle();
        }

    }

    public void seeTime() {
        setSimStatus("see time");
        System.out.println("Sim sedang melihat waktu");
        System.out.println("waktu hari ini tersisa " + currentWorld.getWorldClock().getSisaWaktu());
        setIdle();
    }

    //costum action 
    public void crying(int duration) {
        setSimStatus("crying");
        System.out.println("Sim akan menangis karena stress");
        currentWorld.getWorldClock().wait(duration);
        gainHunger(-10);
        gainMood(10);
        System.out.println("Sim sudah selesai menangis :(");
        currentWorld.getWorldClock().updateTime(duration);
        setIdle();
    }

    public void recitate(int duration){
        setSimStatus("recitate");
        int recitateTime = 0;
        int temp = duration/60;
        while(recitateTime != duration){
            currentWorld.getWorldClock().wait(1);
            recitateTime++;
            System.out.println("Sedang mengaji selama " + recitateTime + " detik");
        }
        setIdle();
        gainMood(3*temp);
        currentWorld.getWorldClock().updateTime(duration);
    }

    public void steal(String itemName){
        setSimStatus("stealing");
        if(currentRoom.getItemList().containsKey(itemName)){
            this.inventory.addInventory((Item) currentRoom.getItemList().get(itemName));
            currentRoom.removeItem(itemName);
            System.out.println("Kamu berhasil mencuri barang " + itemName + ", silahkan lihat di inventory kamu!");
        }
        else{
            System.out.println("Tidak bisa mencuri barang tersebut karena barang tidak ada!");
        }
        setIdle();
    }

    public void daydream(int duration){
        setSimStatus("day dreaming");
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
        currentWorld.getWorldClock().updateTime(duration);
    }

    public void write() {
        setSimStatus("writting");
        currentWorld.getWorldClock().wait(10);
        System.out.println(this.fullName + " sedang menulis halaman baru di diary");
        currentWorld.getWorldClock().updateTime(10);
        setIdle();
        gainMood(5);
    }

    public void read() {
        setSimStatus("reading");
        currentWorld.getWorldClock().wait(5);
        System.out.println(this.fullName + " sedang membaca diary");
        currentWorld.getWorldClock().updateTime(5);
        setIdle();
        gainMood(5);
    }

    public void bath() {
        setSimStatus("bath");
        System.out.println("Sim sedang mandi");
        currentWorld.getWorldClock().wait(20);
        gainMood(10);
        gainHealth(15);
        System.out.println("Sim telah wangi selesai mandi");
        currentWorld.getWorldClock().updateTime(20);
        setIdle();
    }

    

    //driver
    public static void main(String[] args) {
        // World world = World.getInstance();
        // House house = House.newHouse(world);
        // Sim Bobi = new Sim("Bobi",house);
        // System.out.println(Bobi.getSimInfo());
        // Bobi.currentHouse = new House().newHouse(world);
        // Bobi.currentRoom = Bobi.currentHouse.getRoomList().get("ruang01");
        // System.out.println(Bobi.currentRoom.getRoomName());
        // System.out.println(Bobi.currentRoom.getItemList());

        // // buat ruangan baru di rumah bobi
        // Room ruang02 = new Room("dapur",Bobi.getCurrentHouse());
        // Bobi.currentHouse.addRoom("ruang02", ruang02);

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
