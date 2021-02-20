import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * A class that stores the date based off an input string.
 *
 * We break down the string and save the year, the month, and
 * the day within the respective instance variables.
 * @author Abimanyu Ananthu, Ashish Shenoy
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    //constants
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int FEBRUARY_LEAP = 29;
    public static final int MONTH_END = 31;
    public static final int OLD_EMPLOYEE = 1900;

    /**
     * Creates a Date object based off the given string.
     * Using StringTokenizer, it assigns the proper values
     * to the instance variables month, day, and year
     *
     * @param date - a string in the form mm/dd/yyyy
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date, "/");
        this.month = Integer.parseInt(st.nextToken());
        this.day = Integer.parseInt(st.nextToken());
        this.year = Integer.parseInt(st.nextToken());
    }

    /**
     * Creates a Date object that represents today's date
     * with the help of the Java Calender library.
     * In the event the user doesn't specify a string,
     * this constructor will run
     */
    public Date() {
        Calendar rightNow = Calendar.getInstance();
        this.year = rightNow.get(Calendar.YEAR);
        this.month = rightNow.get(Calendar.MONTH) + 1;
        this.day = rightNow.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Returns the year from the date object
     *
     * @return integer value representing the year of the date
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Returns the month from the date object
     *
     * @return integer value representing the month of the date
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Returns the day from the date object
     *
     * @return integer value representing the day of the date
     */
    public int getDay() {
        return this.day;
    }

    public int compareTo(Date date2){
        Date date1 = this;

        if(date1.year > date2.year) {
            return 1;
        }
        else if (date1.year == date2.year) {
            if (date1.month > date2.month) {
                return 1;
            } else if (date1.month == date2.month) {
                if (date1.day > date2.day) {
                    return 1;
                } else if (date1.day == date2.day) {
                    return 0;
                }
            }
        }
        return -1;
    }

    /**
     * Checks if the date is valid and greater than
     * 1900 while taking into account of leap years
     *
     * @return true if the date is valid and greater than
     * year 1900, false otherwise
     */
    public boolean isValid() {
        boolean lessThanCurrDate = !this.greaterThanCurrDate();
        boolean dayValidator = this.dayValidator();
        boolean bornAfter1900 = this.year > OLD_EMPLOYEE;

        return lessThanCurrDate && dayValidator && bornAfter1900;
    }

    /**
     * Checks whether or not the date's year is a leap year
     *
     * @return true if the date is in a leap year, false otherwise
     */
    private boolean isLeapYear() {
        if (this.year % QUADRENNIAL == 0) {
            if (this.year % CENTENNIAL == 0) {
                return this.year % QUATERCENTENNIAL == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if the day is valid based off the given month,
     * taking into account of leap years as well.
     *
     * @return true if the day is within the range of the given month,
     * false otherwise.
     */
    private boolean dayValidator() {
        int month = this.month;
        int day = this.day;
        boolean day31 = false;

        if (day > MONTH_END || day < 1)
            return false;

        switch (month) {
            case Calendar.JANUARY + 1:
            case Calendar.MARCH + 1:
            case Calendar.MAY + 1:
            case Calendar.OCTOBER + 1:
            case Calendar.JULY + 1:
            case Calendar.AUGUST + 1:
            case Calendar.DECEMBER + 1:
                day31 = true;
                break;

            case Calendar.FEBRUARY + 1:
                if (this.day >= MONTH_END - 1) //in the case of FEB the day cannot be 30 or greater
                    return false;
                if (this.isLeapYear() && this.day == FEBRUARY_LEAP)
                    return true;
                return this.day < FEBRUARY_LEAP;

            case Calendar.APRIL + 1:
            case Calendar.JUNE + 1:
            case Calendar.SEPTEMBER + 1:
            case Calendar.NOVEMBER + 1:
                break;
            //case that the month is just not one of the above (1-12)
            default:
                return false;
        }

        return day31 || this.day != MONTH_END;
    }

    /**
     * Helper method for isValid().
     * Checks whether the date is ahead of the current Date.
     *
     * @return true if the date is not greater than the current date,
     *         false otherwise.
     */
    private boolean greaterThanCurrDate() {
        Date currDate = new Date();
        if (this.year > currDate.year) {
            return true;
        } else {
            if (this.year == currDate.year) {
                if (this.month > currDate.month) {
                    return true;
                } else {
                    if (this.month == currDate.month) {
                        return this.day > currDate.day;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        }
    }

    /**
     * Returns a string of the date in the format mm/dd/yyyy
     *
     * @return String representing the date object
     */
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    /**
     * Testbed main to exercise the isValid() method in this class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Testing the isValid Method

        Date date1 = new Date("1/2/2001");
        Date date2 = new Date("1/2/2001");
        System.out.println(date1.compareTo(date2));

        /*
        // Test Case #1, checking invalid month.
        System.out.println("Running Test Case#1a");
        Date tCase1a = new Date("13/1/2000");
        if(!tCase1a.isValid()) System.out.println("Test Case#1a, checking a date with invalid month. Passed");
        else System.out.println("Test Case#1a, checking a date with invalid month. Failed");

        System.out.println("Running Test Case#1b");
        Date tCase1b = new Date("12/1/2000");
        if(tCase1b.isValid()) System.out.println("Test Case#1b, checking a date with valid month. Passed");
        else System.out.println("Test Case#1b, checking a date with valid month. Failed");

        // Test Case #2, checking 02/29 on a non-leap year.
        System.out.println("Running Test Case#2");
        Date tCase2a = new Date("2/29/2019");
        if(!tCase2a.isValid()) System.out.println("Test Case#2a, checking a date with 02/29 on a non-leap year. Passed");
        else System.out.println("Test Case#2a, checking a date 02/29 on a non-leap year. Failed");

        System.out.println("Running Test Case#2b");
        Date tCase2b = new Date("2/29/2020");
        if(tCase2b.isValid()) System.out.println("Test Case#2b, checking a date with 02/29 on a leap year. Passed");
        else System.out.println("Test Case#2b, checking a date with 02/29 on a leap year. Failed");

        // Test Case #3, checking a date before 1900.
        System.out.println("Running Test Case#3a");
        Date tCase3a = new Date("3/31/1800");
        if(!tCase3a.isValid()) System.out.println("Test Case#3a, checking a date before 1900. Passed");
        else System.out.println("Test Case#3a, checking a date before 1900. Failed");

        System.out.println("Running Test Case#3b");
        Date tCase3b = new Date("3/31/1950");
        if(tCase3b.isValid()) System.out.println("Test Case#3b, checking a date after 1900. Passed");
        else System.out.println("Test Case#3b, checking a date after 1900. Failed");

        // Test Case #4, checking invalid day.
        System.out.println("Running Test Case#4a");
        Date tCase4a = new Date("4/-15/2009");
        if(!tCase4a.isValid()) System.out.println("Test Case#4a, checking a date with invalid day. Passed");
        else System.out.println("Test Case#4a, checking a date with invalid day. Failed");

        System.out.println("Running Test Case#4b");
        Date tCase4b = new Date("4/15/2009");
        if(tCase4b.isValid()) System.out.println("Test Case#4b, checking a date with valid day. Passed");
        else System.out.println("Test Case#4b, checking a date with valid day. Failed");

        // Test Case #5, testing day=31 on a 30-day month.
        System.out.println("Running Test Case#5a");
        Date tCase5a = new Date("4/31/2009");
        if(!tCase5a.isValid()) System.out.println("Test Case#5a, testing day=31 on a 30-day month. Passed");
        else System.out.println("Test Case#5a,testing day=31 on a 30-day month. Failed");

        System.out.println("Running Test Case#5b");
        Date tCase5b = new Date("5/31/2009");
        if(tCase5b.isValid()) System.out.println("Test Case#5b, testing day=31 on a 31-day month. Passed");
        else System.out.println("Test Case#5b, testing day=31 on a 31-day month. Failed");

        // Test Case#6, checking a date in the future.
        System.out.println("Running Test Case#6a");
        Date tCase6a = new Date("4/30/2109");
        if(!tCase6a.isValid()) System.out.println("Test Case#6a, checking a date in the future. Passed");
        else System.out.println("Test Case#6a, checking a date in the future. Failed");

        System.out.println("Running Test Case#6b");
        Date tCase6b = new Date("4/30/1909");
        if(tCase6b.isValid()) System.out.println("Test Case#6b, checking a date that has occurred, but after 1900. Passed");
        else System.out.println("Test Case#6b, checking a date that has occurred, but after 1900. Failed");

        // Test Case#7, checking a correct date.
        System.out.println("Running Test Case#7");
        Date tCase7 = new Date("4/30/2019");
        if(tCase7.isValid()) System.out.println("Test Case#7, checking a normal date. Passed");
        else System.out.println("Test Case#7, checking a normal date. Failed");
        */
    }


}
