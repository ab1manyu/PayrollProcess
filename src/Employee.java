public class Employee {
    private Profile profile;
    private String type;
    private double payment;

    public Employee(Profile profile, String type){
        this.profile = profile;
        this.type = type;
        this.payment = 0;
    }

    public void calculatePayment(){

    }

    @Override
    public String toString() {
        return this.profile.toString() + "::Payment "
                + this.payment + "::" + type;
    }

    @Override
    public boolean equals(Object obj) {
        Employee number1 = this;
        Employee number2 = (Employee) obj;

        boolean isEqual = number1.profile.equals(number2.profile);
        return isEqual;
    }
}
