public class Job {
    private String name; 
    private int salary; 

    //konstruktor 
    public Job(String name, int salary){
        this.name = name; 
        this.salary = salary; 
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
