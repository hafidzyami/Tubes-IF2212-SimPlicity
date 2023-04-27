public class Job {
    private String name; 
    private int salary; 

    //konstruktor 
    public Job(String name, int salary){
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
}
