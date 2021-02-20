import java.text.DecimalFormat;

public class FullTime extends Employee{
    private double salary;

    public static final int TOTAL_PAY_PERIODS = 26;

    public FullTime(Profile profile, double salary) {
        super(profile);
        this.salary = salary;
    }

    public double getSalary(){
        return this.salary;
    }

    @Override
    public void calculatePayment() {
        double payment = salary/TOTAL_PAY_PERIODS;
        super.setPayment(payment);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return super.toString() + "::FULL TIME::Annual Salary " + df.format(this.salary);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FullTime){
            return super.equals(obj);
        }
        return false;
    }
}
