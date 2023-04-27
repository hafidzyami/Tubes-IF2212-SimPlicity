public class Job {
    private String name; 
    private int salary; 

    //konstruktor 
    public Job(String name){
        this.name = name;
        switch (name) {
            case "Badut Sulap":
                setDaySalary(15);
                break;
            case "Koki":
                setDaySalary(30);
                break;
            case "Polisi":
                setDaySalary(35);
                break;
            case "Programmer":
                setDaySalary(45);
                break;
            case "Dokter":
                setDaySalary(50);
                break;
            default:
                name = null;
                break;
        }
    }

    //getter 
    public String getJobName() {
        return this.name;
    }

    public int getDaySalary() {
        return this.salary;
    }

    //setter 
    public void setJobName(String name) {
        this.name = name; 
    }

    public void setDaySalary(int salary){
        this.salary = salary; 
    }

    public static Job firstJob() {
        int i =(int) (Math.random() * 5 * 1);
        switch (i) {
            case 1 :
                return new Job("Badut Sulap");
            case 2 :
                return new Job("Koki");
            case 3 :
                return new Job("Polisi");
            case 4 :
                return new Job("Programmer");
            case 0 :
                return new Job("Dokter");
            default :
                return new Job("kosong");
        }
    }

    public static void main(String[] args) {
        System.out.println((int) (Math.random() * 5 * 1));
    }
}
