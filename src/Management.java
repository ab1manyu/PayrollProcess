import java.text.DecimalFormat;

/**
 * A sub-class of the FullTime class that shares the same
 * information, such as profile, payment, and salary with
 * the addition of role, representing 3 possible management
 * positions.
 *
 * We take in a Profile,salary, and role that will be saved to the
 * respective instance variables
 *
 * @author Ashish Shenoy, Abimanyu Ananthu
 */
public class Management extends FullTime {
    private int role;

    public static final int MANAGER = 1;
    public static final int DEPARTMENT_HEAD = 2;
    public static final int DIRECTOR = 3;

    public static final double MANAGER_COMPENSATION = 5000;
    public static final double DEPT_HEAD_COMPENSATION = 9500;
    public static final double DIRECTOR_COMPENSATION = 12000;


    /**
     * Constructor that creates Management objects. Takes
     * in a Profile object, a value for salary, and a number
     * representing the role.
     * @param profile - Profile object that represents the profile of an employee
     * @param salary - A double that represents the annual salary of the employee
     * @param role - An int between 1 and 3 representing the role the employee has
     */
    public Management(Profile profile, double salary, int role) {
        super(profile, salary);
        this.role = role;
    }

    /**
     * Overridden method from the FullTime class that
     * calculates the payment of a Management employee.
     * Accomplishes this by setting the payment field
     * through a setter method in the super class.
     *
     * Accomplishes this by taking the salary and dividing it
     * by the total pay periods. Depending on the role, there
     * is additional compensation added to the total.
     */
    @Override
    public void calculatePayment() {
        double compensation = this.getCompensation();
        double salary = super.getSalary()/FullTime.TOTAL_PAY_PERIODS;
        super.setPayment(salary + compensation);
    }

    /**
     * Overridden method from FullTime class that adds onto
     * the description string by including the role of the
     * employee and the total compensation depending on the role.
     *
     * @return A String value that contains profile, payment, salary
     * , role, and compensation information
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return super.toString() + "::" + this.getRoleAsString() + " Compensation " + df.format(this.getCompensation());
    }

    /**
     * Overridden method from FullTime class that specifically checks
     * if the instance passed in is a Management object before calling
     * the super method equals() in the FullTime class
     *
     * @param obj - Arbitrary object that could be passed in regardless of type
     * @return boolean value determining whether the two instances are the same.
     *         true if they are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Management){
            return super.equals(obj);
        }
        return false;
    }

    /**
     * Private helper method that helps translate
     * the int values of the role as an actual
     * string.
     * @return A role in String form depending on what
     * the value of role is.
     */
    private String getRoleAsString(){
        int role = this.role;
        if(role == MANAGER){
            return "Manager";
        }else if(role == DEPARTMENT_HEAD){
            return "Department Head";
        }else {
            return "Director";
        }
    }

    /**
     * Private helper method that calculates the compensation
     * depending on each role.
     *
     * @return A double that represents the value of compensation
     * depending on the role of the employee
     */
    private double getCompensation(){
        int role = this.role;
        double compensation = 0;
        if(role == MANAGER){
            compensation = MANAGER_COMPENSATION/FullTime.TOTAL_PAY_PERIODS;
        }else if(role == DEPARTMENT_HEAD){
            compensation = DEPT_HEAD_COMPENSATION/FullTime.TOTAL_PAY_PERIODS;
        }else if(role == DIRECTOR){
            compensation = DIRECTOR_COMPENSATION/FullTime.TOTAL_PAY_PERIODS;
        }
        return compensation;
    }
}
