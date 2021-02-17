public class FullTime extends Employee{
    private double salary;

    private static final int TOTAL_PAY_PERIODS = 26;

    public FullTime(Profile profile, double salary) {
        super(profile, "FULL TIME");
        this.salary = salary;
    }

    @Override
    public void calculatePayment() {
        double payment = salary/TOTAL_PAY_PERIODS;

    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
