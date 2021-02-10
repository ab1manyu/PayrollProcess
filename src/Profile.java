public class Profile {
    private String name; //employee’s name in the form “lastname,firstname”
    private String department; //department code: CS, ECE, IT
    private Date dateHired;

    @Override
    public String toString() {
        return this.name +"::"+ this.department + "::" + dateHired;
    }

    @Override
    //compare name, department and dateHired
    public boolean equals(Object obj) {
        Profile profile1 = this;
        Profile profile2 = (Profile) obj;

        boolean sameName = profile1.name.equals(profile2.name);
        boolean sameDepartment = profile1.department.equals(profile2.department);
        boolean sameDate = profile1.dateHired.compareTo(profile2.dateHired) == 0;

        return sameName && sameDepartment && sameDate;
    }
}
