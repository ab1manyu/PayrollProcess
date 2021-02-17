public class Management extends FullTime {
    private int role;

    public Management(Profile profile, double salary, int role) {
        super(profile, salary);
        this.role = role;
    }
}
