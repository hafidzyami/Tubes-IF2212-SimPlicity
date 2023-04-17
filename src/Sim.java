public class Sim implements Runnable {
    private String fullName;
    private String job;
    private int money;
    private Inventory inventory = new Inventory();
    private int hunger;
    private int mood;
    private int hygiene;
    private String status;

    public Sim(String nama,Item ... items) {
        this.hunger = 80;
        this.mood = 80;
        this.hygiene = 80;
        this.money = 100;
        this.job = "sopir";
        
    }
    public void run() {
        System.out.println(fullName + " Sedang beraktivitas");
    }

    
}
