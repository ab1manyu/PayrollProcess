public class Company {
    private Employee[] emplist;
    private int numEmployee;

    private final int ADDED_LENGTH = 4;



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

        return false;
    }

    //set working hours for a part time
    public boolean setHours(Employee employee) {
        if (employee instanceof PartTime){
            PartTime targetEmployee = (PartTime) employee;
            return true;
        }

        return false;

    }
    //process payments for all employees
    public void processPayments() { }

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
        int arrLength = this.emplist.length;
        for (int i = 0; i < arrLength-1; i++) {
            int currentMin = i;
            for (int j = i+1; j < arrLength; j++) {
                if (this.emplist[j] != null && this.emplist[currentMin] != null) {
                    if (this.emplist[j].compareDates(this.emplist[currentMin])){
                        currentMin = j;
                    }

                }
            }
            Employee temp = this.emplist[currentMin];
            this.emplist[currentMin] = this.emplist[i];
            this.emplist[i] = temp;
        }
    }

    private void sortByDepartment(){
        int arrLength = this.emplist.length;

        //move all employees under the CS department to the front
        for (int i = arrLength-1; i > -1; i--) {
            int currentSwap = i;
            for (int j = arrLength-1; j > -1; j--) {
                if (this.emplist[j] != null && this.emplist[currentSwap] != null) {
                    if (this.emplist[i].getProfile().getDepartment().equals("CS")) {
                        currentSwap = j;
                    }
                }
            }

            Employee temp = this.emplist[currentSwap];
            this.emplist[currentSwap] = this.emplist[i];
            this.emplist[i] = temp;
        }

        //move all employees under the IT department to the back
        for (int i = 0; i < arrLength; i++) {
            int currentSwap = i;
            for (int j = 0; j < arrLength; j++) {
                if (this.emplist[j] != null && this.emplist[currentSwap] != null) {
                    if (this.emplist[i].getProfile().getDepartment().equals("IT")) {
                        currentSwap = j;
                    }
                }
            }

            Employee temp = this.emplist[currentSwap];
            this.emplist[currentSwap] = this.emplist[i];
            this.emplist[i] = temp;
        }
    }



}