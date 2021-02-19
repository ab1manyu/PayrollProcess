public class Management extends FullTime {
    private int role;

    public static final int MANAGER = 1;
    public static final int DEPARTMENT_HEAD = 2;
    public static final int DIRECTOR = 3;

    public static final double MANAGER_COMPENSATION = 5000;
    public static final double DEPT_HEAD_COMPENSATION = 9500;
    public static final double DIRECTOR_COMPENSATION = 12000;

    public Management(Profile profile, double salary, int role) {
        super(profile, salary);
        this.role = role;
    }

    @Override
    public void calculatePayment() {
        double compensation = this.getCompensation();
        double salary = super.getSalary();
        super.setPayment(salary + compensation);
    }

    @Override
    public String toString() {
        return super.toString() + "::" + this.getRoleAsString() + " Compensation " + this.getCompensation() ;
    }

    @Override
    public boolean equals(Object obj) {
        Management number1 = this;
        Management number2 = (Management) obj;
        boolean equalStats = super.equals(obj);
        boolean equalRole = number1.role == number2.role;
        return equalRole && equalRole;
    }

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
