import java.text.DecimalFormat;

/**
 * A sub-class of the Employee that shares the same
 * information, such as profile and payment with the addition
 * of hourly and hoursWorked that is unique to PartTime.
 *
 * We take in a Profile and hourly that will be saved to the
 * respective instance variables. hoursWorked will always
 * be set to 0 on the creation of a new instance.
 *
 * @author Ashish Shenoy, Abimanyu Ananthu
 */
public class PartTime extends Employee {

    private double hourly;
    private int hoursWorked;

    public static final int MAX_HOURS = 80;
    public static final double OVERTIME_RATE = 1.5;

    /**
     * Constructor that creates a new PartTime employee by
     * taking in a Profile object and hourly. Automatically
     * sets hoursWorked to 0 when creating a new instance
     *
     * @param profile - Profile obj representing the employee's profile
     * @param hourly - double value representing the hourly rate
     */
    public PartTime(Profile profile, double hourly) {
        super(profile);
        this.hourly = hourly;
        this.hoursWorked = 0;
    }

    /**
     * Getter method that returns the number of hours
     * worked by a PartTime employee.
     *
     * @return int value representing the hours worked by
     *         PartTime employee.
     */
    public int getHoursWorked(){
        return this.hoursWorked;
    }

    /**
     * Setter method that takes in an int value and sets the
     * hours worked for a PartTime employee.
     *
     * @param hours - int value representing the hours worked
     *              for PartTime employee.
     */
    public void setHoursWorked(int hours){
        this.hoursWorked = hours;
    }

    /**
     * Overridden method from the Employee class that
     * calculates the payment of a PartTime employee.
     * Accomplishes this by setting the payment field
     * through a setter method in the super class.
     *
     * Accomplishes this by taking the hourly and multiplying
     * it the number of hours worked. Additional payments for
     * each hour above 80 counts as overtime.
     */
    @Override
    public void calculatePayment() {
        int hoursWorked = this.hoursWorked;
        int overtimeHours = 0;
        if(hoursWorked > MAX_HOURS){
            overtimeHours = hoursWorked - MAX_HOURS;
        }
        double payment = (this.hourly)*(this.hoursWorked) + (overtimeHours)*(this.hourly)*(OVERTIME_RATE);
        super.setPayment(payment);
    }

    /**
     * Overridden method from Employee class that adds onto
     * the description string by including the hourly rate
     * and the hours worked.
     *
     * @return A String value that contains profile, payment, hourly,
     * and # of hours worked information.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return super.toString() + "::PART TIME:: Hourly Rate " + df.format(this.hourly)
               + ":: Hours Worked this period: " + this.hoursWorked;
    }

    /**
     * Overridden method from Employee class that specifically checks
     * if the instance passed in is a PartTime object before calling
     * the super method equals() in the Employee class.
     *
     * @param obj - Arbitrary object that could be passed in regardless of type
     * @return boolean value determining whether the two instances are the same.
     *         true if they are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PartTime){
            return super.equals(obj);
        }
        return false;
    }
}
