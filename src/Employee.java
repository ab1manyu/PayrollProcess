import java.text.DecimalFormat;

/**
 * Employee is a class that creates a new Employee object.
 *
 * We take in a Profile, and contains the fields to store the profile as well as their payment.
 * This class is used as the super class so that it works with FullTime and PartTime and Management.
 *
 * @author Ashish Shenoy, Abimanyu Ananthu
 */
public class Employee {
    private Profile profile;
    private double payment;

    /**
     * Instantiates an Employee object by taking in a profile and setting it to this.profile.
     * Also by default, makes the payment 0.
     *
     * @param profile the profile we use to create an employee object.
     */
    public Employee(Profile profile){
        this.profile = profile;
        this.payment = 0;
    }

    /**
     * Skeleton method that will be overridden by the subclasses of Employee.
     */
    public void calculatePayment(){ }

    /**
     * Prints the employee's profile as a string and
     * the total payment. This method is overwritten in other subclasses.
     *
     * @return A String value that contains profile and payment.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return this.profile.toString() + "::Payment " + df.format(this.payment);
    }

    /**
     * Checks if the instance passed in is a Employee object before calling
     * the method equals() to compare profiles between the current profile and the parameterized profile.
     *
     * @param obj - Arbitrary object that could be passed in regardless of type
     * @return boolean value determining whether the profiles of the employees are the same.
     *         true if they are the same, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Employee number1 = this;
        Employee number2 = (Employee) obj;

        return number1.profile.equals(number2.profile);

    }

    /**
     * Helper method that helps return
     * the profile of the employee.
     *
     * @return returns profile of the employee.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Helper method to return the salary as a properly formatted String with decimal format.
     * Used to test calculatePayments in the tester classes.
     *
     * @return salary as a properly formatted String with decimal format.
     */
    public String getFormattedPayment() {
        DecimalFormat df = new DecimalFormat("##0.00");
        return df.format(payment);
    }

    /**
     * Helper method that allows the user to set the value for payment.
     *
     *
     * @param payment modifies the payment variable for the specified employee.
     */
    public void setPayment(double payment){
        this.payment = payment;
    }

    /**
     * Compares the dateHired for two employees. This calls the compareTo method from the Date class.
     *
     * @param employee the employee we are checking the date agaisnt.
     * @return  boolean value determining whether the first date is older than the second date.
     *          returns true if the first employee was hired before the second employee, false otherwise.
     */
    public boolean compareDates(Employee employee) {
        Date date1 = this.getProfile().getDateHired();
        Date date2 = employee.getProfile().getDateHired();

        return date1.compareTo(date2)==1;
    }


}
