public class Sim implements Runnable {
    private String fullName;
    private String job;
    private int money;
    private Inventory inventory = new Inventory();
    private int hunger;
    private int mood;
    private int health;
    private String status;

    public Sim(String nama,Item ... items) {
        this.fullName = nama;
        this.hunger = 80;
        this.mood = 80;
        this.health = 80;
        this.money = 100;
        this.job = "sopir";
        
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

    // active action 
    public void work (int duration){
        //nunggu Job
    }

    public void sport (int duration){

    }

    public void sleep (int duration){

    }

    public void eat (){
    /** Makan berarti Sim mengambil makanan yang ada di Inventory untuk kemudian dikonsumsi. 
     * Konsumsi makanan akan mengurangi jumlah makanan terkait pada inventory sejumlah 1 buah 
     * dan meningkatkan tingkat kekenyangan Sim sejumlah satuan kekenyangan makanan terkait. */  
    
     // nunggu food
    }

    public void cook () {

    }

    public void visit (int duration){

    }

    public void pee() {

    }

    // needed time action 
    public void upgradeHome() {

    }

    public void buyItem() {
        //tabel bahan makanan dimana
    }

    //not needed time action 
    public void moveToRoom() {

    }

    public void seeInventory() {

    }

    public void installItem() {

    }

    public void seeTime() {
        //nunggu clock
    }


    //driver
    public static void main(String[] args) {
        Sim Bobi = new Sim("Bobi");
        System.out.println(Bobi.getSimInfo());
    }

}
