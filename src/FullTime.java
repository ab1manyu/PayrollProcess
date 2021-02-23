import java.text.DecimalFormat;

/**
 * A sub-class of the Employee class that shares the same
 * information, such as profile and payment with the addition
 * of salary that is unique to FullTime and its subclass.
 *
 * We take in a Profile and salary that will be saved to the
 * respective instance variables
 *
 * @author Ashish Shenoy, Abimanyu Ananthu
 */
public class FullTime extends Employee{
    public static final int TOTAL_PAY_PERIODS = 26;
    private double salary;

    /**
     * Constructor that takes in a profile and a salary to
     * create a FullTime object.
     * @param profile - A profile object that represents the profile of the employee
     * @param salary - A double value that represents the annual salary of the employee
     */
    public FullTime(Profile profile, double salary) {
        super(profile);
        this.salary = salary;
    }

    /**
     * Getter method that returns the salary of a FullTime employee
     * that the method was called upon.
     *
     * @return double value representing the salary of FullTime employee
     */
    public double getSalary(){
        return this.salary;
    }

    /**
     * Overridden method from the Employee class that
     * calculates the payment of a FullTime employee.
     * Accomplishes this by setting the payment field
     * through a setter method in the super class.
     *
     * Accomplishes this by taking the salary and dividing it
     * by the total pay periods.
     */
    @Override
    public void calculatePayment() {
        double payment = salary/TOTAL_PAY_PERIODS;
        super.setPayment(payment);
    }

    /**
     * Overridden method from Employee class that adds onto
     * the description string by including the annual salary.
     *
     * @return A String value that contains profile, payment, and salary information
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return super.toString() + "::FULL TIME::Annual Salary " + df.format(this.salary);
    }

    /**
     * Overridden method from Employee class that specifically checks
     * if the instance passed in is a FullTime object before calling
     * the super method equals() in the Employee class
     *
     * @param obj - Arbitrary object that could be passed in regardless of type
     * @return boolean value determining whether the two instances are the same.
     *         true if they are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FullTime){
            return super.equals(obj);
        }
        return false;
    }
}
