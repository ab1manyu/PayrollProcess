import java.text.DecimalFormat;
 
public class PartTime extends Employee {
    public static final int MAX_HOURS = 80;
    public static final double OVERTIME_RATE = 1.5;

    private double hourly;
    private int hoursWorked;

    public PartTime(Profile profile, double hourly) {
        super(profile);
        this.hourly = hourly;
        this.hoursWorked = 0;
    }

    public int getHoursWorked(){
        return this.hoursWorked;
    }

    public void setHoursWorked(int hours){
        this.hoursWorked = hours;
    }

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

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        return super.toString() + "::PART TIME:: Hourly Rate " + df.format(this.hourly)
               + ":: Hours Worked this period: " + this.hoursWorked;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PartTime){
            return super.equals((PartTime) obj);
        }
        return false;
    }
}
