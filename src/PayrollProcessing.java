import java.util.Scanner; 
import java.util.StringTokenizer;

/**
 * A class that drives the entire program
 * and takes in all the command line arguments
 * from the client
 *
 * @author Abimanyu Ananthu, Ashish Shenoy
 */
public class PayrollProcessing {

    public static final int EMP_ARGUMENTS = 4;
    public static final int ROLE_ARGUMENTS = 5;
    public static final int PROFILE_ARGUMENTS = 3;

    /**
     * Runs the program by taking in the user's
     * command line arguments/requests and responding
     * with the appropriate results. Will quit the session
     * once client enters 'Q' to safely exit.
     *
     */
    public void run() {
        boolean running = true;
        Company company = new Company();
        System.out.println("Payroll Processing starts.");

        while(running) {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine(); // Read user input
            try {
                StringTokenizer input = new StringTokenizer(s, " ");
                String command = input.nextToken();

                switch (command) {
                    // Add a part-time employee with the hourly pay rate
                    case "AP":
                        if (input.countTokens() == EMP_ARGUMENTS) {
                            try {
                                String strName = input.nextToken();
                                String strDepartment = input.nextToken();
                                String strDate = input.nextToken();
                                String strHourly = input.nextToken();

                                double hourlyRate = Double.parseDouble(strHourly);
                                Profile profile = profileValidator(strName, strDepartment, strDate);

                                if(profile == null){
                                    break;
                                }
                                if(hourlyRate < 0){
                                    System.out.println("Pay rate cannot be negative.");
                                    break;
                                }
                                Employee partTime = new PartTime(profile, hourlyRate);
                                boolean wasAdded = company.add(partTime);
                                if(!wasAdded){
                                    System.out.println("Employee is already in the list");
                                }else{
                                    System.out.println("Employee Added.");
                                }

                            } catch (Exception e) {
                                System.out.println("Invalid parameter values");
                            }
                        }else{
                            System.out.println("Invalid number of arguments");
                        }
                        break;
                    // Add a full-time employee with a yearly salary.
                    case "AF":
                        if (input.countTokens() == EMP_ARGUMENTS) {
                            try {
                                String strName = input.nextToken();
                                String strDepartment = input.nextToken();
                                String strDate = input.nextToken();
                                String strSalary = input.nextToken();

                                double salary = Double.parseDouble(strSalary);
                                Profile profile = profileValidator(strName, strDepartment, strDate);

                                if(profile == null){
                                    break;
                                }
                                if(salary < 0){
                                    System.out.println("Salary cannot be negative.");
                                    break;
                                }
                                Employee fullTime = new FullTime(profile, salary);
                                boolean wasAdded = company.add(fullTime);
                                if(!wasAdded){
                                    System.out.println("Employee is already in the list");
                                }else{
                                    System.out.println("Employee Added.");
                                }

                            } catch (Exception e) {
                                System.out.println("Invalid parameter values");
                            }
                        }else{
                            System.out.println("Invalid number of arguments");
                        }
                        break;
                    // Add a management employee with a yearly salary + compensation depending on role.
                    case "AM":
                        if (input.countTokens() == ROLE_ARGUMENTS) {
                            try {
                                String strName = input.nextToken();
                                String strDepartment = input.nextToken();
                                String strDate = input.nextToken();
                                String strSalary = input.nextToken();
                                String strRole = input.nextToken();

                                double salary = Double.parseDouble(strSalary);
                                int role = Integer.parseInt(strRole);
                                Profile profile = profileValidator(strName, strDepartment, strDate);

                                if(profile == null){
                                    break;
                                }
                                if(salary < 0){
                                    System.out.println("Salary cannot be negative.");
                                    break;
                                }
                                if(role <= 0 || role > 3){
                                    System.out.println("Invalid Management code.");
                                    break;
                                }
                                boolean wasAdded = company.add(new Management(profile, salary, role));
                                if(!wasAdded){
                                    System.out.println("Employee is already in the list");
                                }else{
                                    System.out.println("Employee Added.");
                                }
                            } catch (Exception e) {
                                System.out.println("Invalid parameter values");
                            }
                        }else{
                            System.out.println("Invalid number of arguments");
                        }
                        break;

                    // Default print
                    case "PA":
                        if (company.getNumEmployee() > 0) {
                            System.out.println("--Printing earning statements for all employees--");
                            company.print();
                        } else
                            System.out.println("Employee database is empty.");
                        break;

                    //  Printing by date hired
                    case "PH":
                        if (company.getNumEmployee() > 0) {
                            System.out.println("--Printing earning statements by date hired--");
                            company.printByDate();
                        } else
                            System.out.println("Employee database is empty.");
                        break;

                    // Printing by department
                    case "PD":
                        if (company.getNumEmployee() > 0) {
                            System.out.println("--Printing earning statements by department--");
                            company.printByDepartment();

                        } else
                            System.out.println("Employee database is empty.");
                        break;

                    // Calculates and prints out the total compensation of all employees currently in the company.
                    case "C":
                        if (company.getNumEmployee() > 0){
                            company.processPayments();
                            System.out.println("Calculation of employee payments is done.");
                        }else{
                            System.out.println("Employee database is empty.");
                        }
                        break;

                    // Remove an employee from the company (youre fired!! -benson)
                    case "R":
                        if (input.countTokens() == PROFILE_ARGUMENTS) {
                            if(company.getNumEmployee() > 0 ) {
                                try {
                                    String strName = input.nextToken();
                                    String strDepartment = input.nextToken();
                                    String strDate = input.nextToken();
                                    Profile profile = profileValidator(strName, strDepartment, strDate);
                                    if (profile == null) {
                                        break;
                                    }
                                    boolean wasRemoved = company.remove(new Employee(profile));
                                    if (!wasRemoved) {
                                        System.out.println("Employee doesn't exist.");
                                    } else {
                                        System.out.println("Employee removed.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid parameter values");
                                }
                            } else
                                System.out.println("Employee database is empty.");
                        }else
                            System.out.println("Invalid number of arguments");

                        break;

                    // Set hours for the parttime employee that has worked for _ hours
                    case "S":
                        if (input.countTokens() == EMP_ARGUMENTS && company.getNumEmployee() > 0) {
                            try {
                                String strName = input.nextToken();
                                String strDepartment = input.nextToken();
                                String strDate = input.nextToken();
                                String strHours = input.nextToken();

                                Profile profile = profileValidator(strName, strDepartment, strDate);
                                if(profile == null){
                                    break;
                                }
                                int hours = Integer.parseInt(strHours);
                                if(hours > 100){
                                    System.out.println("Invalid hours: over 100");
                                    break;
                                }else if(hours < 0){
                                    System.out.println("Working hours cannot be negative");
                                    break;
                                }
                                PartTime e = new PartTime(profile, 0);
                                e.setHoursWorked(hours);

                                boolean hoursSet = company.setHours(e);

                                if(!hoursSet){
                                    System.out.println("Employee does not exist.");
                                }else{
                                    System.out.println("Working hours set.");
                                }

                            } catch (Exception e) {
                                System.out.println("Invalid parameter values");
                            }

                        }else{
                            System.out.println("Employee database is empty.");
                        }
                        break;

                    // Quitting the program
                    case "Q":
                        System.out.println("Payroll Processing completed.");
                        System.exit(0);
                        break;

                    // Inputting a wrong command
                    default:
                        System.out.println("Command '"+command+"'"+" not supported!");
                        break;
                }

            } catch (Exception ignored){ }
        }
    }

    /**
     * Checks the validity of the date and department code passed in
     * @param name - String representing the name that's going to be on a profile
     * @param department - String representing the department code
     * @param strDate - Date object representing the date hired
     * @return A Profile object if the validations are passed, otherwise
     *         returns null.
     */
    private Profile profileValidator(String name, String department, String strDate) {

        boolean departmentChecked, dateChecked;
        Date date = new Date(strDate);

        departmentChecked = department.equals("ECE") || department.equals("IT") || department.equals("CS");
        dateChecked = date.isValid();

        if(!departmentChecked){
            System.out.println("'"+ department + "' is not a valid department code");
            return null;
        }
        if(!dateChecked){
            System.out.println(strDate + " is not a valid date!");
            return null;
        }

        return new Profile(name, department, date);


    }
}
