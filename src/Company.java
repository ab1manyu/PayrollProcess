public class Company {
    private Employee[] emplist;
    private int numEmployee;

    /**
     * A helper method to check to see and find a employee in the employee list.
     * @param employee the Employee we are looking for.
     * @return index of the employee if found, otherwise return -1 if the employee is not in the employee list.
     */
    private int find(Employee employee) {
        Employee[] emplist = this.emplist;
        for(int i = 0; i < emplist.length; i++){
            if(employee.equals(emplist[i])){
                return i;
            }
        }
        return -1;
    }

    /**
     * A helper method to grow the employee list by 4 indexes once the employee list is full.
     * An employee list always starts with space to store 4 employees and expands as more
     * employees are added to the list.
     */
    private void grow() {
        Employee[] emplist = this.emplist;
        int newLength = emplist.length + 4;
        Employee[] newBag = new Employee[newLength];
        for(int i = 0; i < emplist.length; i++){
            newBag[i] = emplist[i];
        }
        this.emplist = newBag;
    }

    /**
     * Method to add a specified employee to the employee list.
     * @param employee the Employee we are adding to the employee list.
     */
    public boolean add(Employee employee) { //check the profile before adding
        Employee[] emplist = this.emplist;
        boolean placed = false;
        for (int i = 0; i < emplist.length; i++){
            if(emplist[i] == null){
                emplist[i] = employee;
                numEmployee++;
                return true;
            }
        }
        if(!placed){
            this.grow();
            this.add(employee);
            return true;
        }

        return false;
    }


    public boolean remove(Employee employee) {
        return false;
    } //maintain the original sequence
    public boolean setHours(Employee employee) {
        return false;
    } //set working hours for a part time
    public void processPayments() { } //process payments for all employees
    public void print() { } //print earning statements for all employees
    public void printByDepartment() { } //print earning statements by department
    public void printByDate() { } //print earning statements by date hired
}