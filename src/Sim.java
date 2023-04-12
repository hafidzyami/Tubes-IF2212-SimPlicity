public class Sim {
    private String namaLengkap;
    private String pekerjaan;
    private int uang;
    private String inventory;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private Aksi status;

    public Sim(String nama) {
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.uang = 100;
        this.pekerjaan = "sopir";
    }
    
}
