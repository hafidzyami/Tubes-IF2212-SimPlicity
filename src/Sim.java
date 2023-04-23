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

    }

    public void sport (int duration){

    }

    public void sleep (int duration){

    }

    public void eat (){

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

    }

    //not needed time action 
    public void moveToRoom() {

    }

    public void seeInventory() {

    }

    public void installItem() {

    }

    public void seeTime() {

    }


    //driver
    public static void main(String[] args) {
        Sim Bobi = new Sim("Bobi");
        System.out.println(Bobi.getSimInfo());
    }

}
