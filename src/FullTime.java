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
        return super.toString() + "::FULL TIME::Annual Salary $" + this.salary;
    }

    @Override
    public boolean equals(Object obj) {
        FullTime number1 = this;
        FullTime number2 = (FullTime) obj;

        boolean equalProfile = super.equals(number2);
        boolean equalSalary = number1.salary == number2.salary;

        return equalProfile && equalSalary;
    }
}
