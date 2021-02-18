public class PartTime extends Employee {
    private double hourly;
    private int hoursWorked;

    public static final int MAX_HOURS = 80;
    public static final double OVERTIME_RATE = 1.5;

    public PartTime(Profile profile, double hourly) {
        super(profile);
        this.hourly = hourly;
        this.hoursWorked = 0;
    }

    @Override
    public void calculatePayment() {
        int hoursWorked = this.hoursWorked;
        int overtimeHours = 0;
        if(hoursWorked > MAX_HOURS){
            overtimeHours = hoursWorked - MAX_HOURS;
        }
        double payment = (this.hourly)*(this.hoursWorked) + (overtimeHours)*(OVERTIME_RATE);
        super.setPayment(payment);
    }

    @Override
    public String toString() {
        return super.toString() + "::PART TIME:: Hourly Rate $" + this.hourly
               + ":: Hours Worked this period: " + this.hoursWorked;
    }

    @Override
    public boolean equals(Object obj) {
        PartTime number1 = this;
        PartTime number2 = (PartTime) obj;

        boolean equalProfile = super.equals(number2);
        boolean equalHourly = number1.hourly == number2.hourly;

        return equalProfile && equalHourly;
    }
}
