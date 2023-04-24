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

    // active action 
    public void work (int duration){
        //nunggu Job
    }

    public void sport (int duration){
        int temp = duration/20;
        this.health += 5 * temp;
        this.hunger -= 5 * temp;
        this.mood += 10 * temp;

    }

    public void gainMood(int mood){
        this.mood += mood;
    }

    public void gainHealth(int health){
        this.health += health;
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
                        System.out.println("Sedang tidur selama " + sleepTime);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                
                if(sleepTime >= 3){
                    gainMood(30*(duration/4));
                    gainHealth(20*(duration/4));
                }
            } 
        });

        t.start();
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
        //nunggu clock
    }


    //driver
    public static void main(String[] args) {
        Sim Bobi = new Sim("Bobi");
        System.out.println(Bobi.getSimInfo());
        Bobi.sleep(240);
        System.out.println(Bobi.getSimInfo());
    }

}
