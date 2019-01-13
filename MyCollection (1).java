public class MyCollection {
    Employee[] employees = new Employee[300];
    Integer writeCounter=0;
    Integer readCounter=0;
    public void addEmployee(Employee employee){
        this.employees[writeCounter++] = employee;
    }

    public Employee readEmployee(){
        return employees[readCounter++];
    }

}
