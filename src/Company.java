public class Company { 
    private Employee[] emplist;
    private int numEmployee;

    public static final int ADDED_LENGTH = 4;
    public static final int NOT_FOUND = -1;


    public Company() {
        this.emplist = new Employee[4];
        this.numEmployee = 0;
    }

    /**
     * A helper method to check to see and find a employee in the employee list.
     * @param employee the Employee we are looking for.
     * @return index of the employee if found, otherwise return -1 if the employee is not in the employee list.
     */
    private int find(Employee employee) {
        Employee[] emplist = this.emplist;
        for(int i = 0; i < emplist.length; i++){
            if(emplist[i] != null && employee.equals(emplist[i])){
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
        int newLength = emplist.length + ADDED_LENGTH;
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
        int existingEmployee = this.find(employee);
        if(existingEmployee != NOT_FOUND){
            return false;
        }
        for (int i = 0; i < emplist.length; i++) {
            if (emplist[i] == null) {
                emplist[i] = employee;
                numEmployee++;
                return true;
            }
        }
        //If it gets to this point, that means the Employee wasn't added yet
        this.grow();
        return this.add(employee);
    }

    //maintain the original sequence
    public boolean remove(Employee employee) {
        Employee[] emplist = this.emplist;
        for (int i = 0; i < emplist.length; i++){
            if(emplist[i] != null && employee.equals(emplist[i])){
                emplist[i] = null;
                numEmployee--;
                return true;
            }
        }
        return false;
    }

    //set working hours for a part time
    public boolean setHours(Employee employee) {
        Employee[] emplist = this.emplist;
        int indexEmployee = this.find(employee);
        if(indexEmployee == NOT_FOUND){
            return false;
        }
        if (emplist[indexEmployee] instanceof PartTime){
            PartTime partTimeEmployee = (PartTime) employee;
            PartTime target = (PartTime) emplist[indexEmployee];
            target.setHoursWorked(partTimeEmployee.getHoursWorked());
            return true;
        }
        return false;

    }

    public int getNumEmployee() {
        return numEmployee;
    }

    //process payments for all employees
    public void processPayments() {
        Employee[] emplist = this.emplist;
        for (Employee value : emplist) {
            if (value != null) {
                value.calculatePayment();
            }
        }
    }

    //print earning statements for all employees
    public void print() {
        Employee[] emplist = this.emplist;
        for (Employee employee : emplist) {
            if (employee != null)
                System.out.println(employee);
        }
    }
    //print earning statements by department
    public void printByDepartment() {
        this.sortByDepartment();
        this.print();
    }
    //print earning statements by date hired
    public void printByDate() {
        this.sortByDate();
        this.print();
    }

    private void sortByDate(){
        Employee[] el = this.emplist;
        int len = el.length;;

        for (int i = 0; i < len-1; i++) {
            int currentMin = i;
            for (int j = i+1; j < len; j++) {
                if (el[j] != null && el[currentMin] != null) {
                    if (el[j].compareDates(el[currentMin])){
                        currentMin = j;
                    }

                }
            }
            Employee temp = el[currentMin];
            el[currentMin] = el[i];
            el[i] = temp;
        }
    }

    private void sortByDepartment(){
        Employee[] el = this.emplist;
        int len = el.length;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if(el[i] != null && el[j] != null) {
                    if (el[i].getProfile().getDepartment().compareTo(el[j].getProfile().getDepartment())>0) {
                        Employee temp = el[i];
                        el[i] = el[j];
                        el[j] = temp;
                    }
                }
            }
        }
    }

}
