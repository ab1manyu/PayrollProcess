/**
 * A class that stores the name, the department, and the
 * date hired.All this information together forms an employee
 * profile
 *
 * @author Abimanyu Ananthu, Ashish Shenoy
 */

public class Profile {
    private String name;
    private String department;
    private Date dateHired;

    /**
     * Constructor that creates a Profile object, taking in
     * a name, a department code, and a date.
     * @param name - A String value in the form last,first representing the name
     * @param department - A String value representing 3 possible codes: CS, ECE, IT
     * @param dateHired - A Date object that represents the date hired
     */
    public Profile(String name, String department, Date dateHired){
        this.name = name;
        this.department = department;
        this.dateHired = dateHired;
    }

    /**
     * Formats the profile into a readable string that contains all the
     * information in the profile, such as name, department, and date hired
     *
     * @return A String that is formatted to represent the information of a Profile
     */
    @Override
    public String toString() {
        return this.name +"::"+ this.department + "::" + dateHired;
    }


    /**
     * Checks whether 2 profiles are the same or not. For 2 profiles
     * to be the same, their name, department, and date hired must match
     *
     * @param obj - An object (most likely a Profile) that is being compared to the
     *            first profile (the one the method was called on)
     * @return A boolean value determining whether or not they are equal. True if
     *         they are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        Profile profile1 = this;
        Profile profile2 = (Profile) obj;

        boolean sameName = profile1.name.equals(profile2.name);
        boolean sameDepartment = profile1.department.equals(profile2.department);
        boolean sameDate = profile1.dateHired.compareTo(profile2.dateHired) == 0;

        return sameName && sameDepartment && sameDate;
    }

    /**
     * Getter method that returns the department code
     *
     * @return A String representing the department code
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Getter method that returns the date hired
     *
     * @return A Date object representing the date hired
     */
    public Date getDateHired() {
        return dateHired;
    }
}
