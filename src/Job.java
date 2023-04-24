public class Job {
    private String name; 
    private int salary; 

    //konstruktor 
    public Job(String name, int salary){
        this.name = name; 
        this.salary = salary; 
    }

    //getter 
    public String getName() {
        return this.name;
    }

    public int getSalary() {
        return this.salary;
    }

    //setter 
    public void setName(String name) {
        this.name = name; 
    }

    public void setSalary(int salary){
        this.salary = salary; 
    }
}
