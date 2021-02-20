import java.util.Scanner;
import java.util.StringTokenizer;

public class PayrollProcessing {

    public static final int EMP_ARGUMENTS = 4;
    public static final int ROLE_ARGUMENTS = 5;
    public static final int PROFILE_ARGUMENTS = 3;

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
                    // add a part-time employee with the hourly pay rate
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

                                boolean wasAdded = company.add(new PartTime(profile, hourlyRate));
                                if(!wasAdded){
                                    System.out.println("Employee is already in the list");
                                }else{
                                    System.out.println("Employee Added.");
                                }

                            } catch (Exception e) {
                                System.out.println("Invalid Command!");
                            }
                        }
                        break;
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

                                boolean wasAdded = company.add(new FullTime(profile, salary));
                                if(!wasAdded){
                                    System.out.println("Employee is already in the list");
                                }else{
                                    System.out.println("Employee Added.");
                                }

                            } catch (Exception e) {
                                System.out.println("Invalid Command!");
                            }
                        }
                        break;

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
                                if(role <= 0 && role > 3){
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
                                System.out.println("Invalid Command!");
                            }
                        }
                        break;


                    // default print
                    case "PA":
                        if (company.getNumEmployee() > 0) {
                            System.out.println("--Printing earning statements for all employees--");
                            company.print();
                        } else
                            System.out.println("Employee database is empty.");
                        break;

                    //  printing by date hired
                    case "PH":
                        if (company.getNumEmployee() > 0) {
                            System.out.println("--Printing earning statements by date hired--");
                            company.printByDate();
                        } else
                            System.out.println("Employee database is empty.");
                        break;

                    // printing by department
                    case "PD":
                        if (company.getNumEmployee() > 0) {
                            System.out.println("--Printing earning statements by department--");
                            company.printByDepartment();

                        } else
                            System.out.println("Employee database is empty.");
                        break;
                    case "R":
                        if (input.countTokens() == PROFILE_ARGUMENTS) {
                            try {
                                String strName = input.nextToken();
                                String strDepartment = input.nextToken();
                                String strDate = input.nextToken();
                                Profile profile = profileValidator(strName, strDepartment, strDate);
                                if(profile == null){
                                    break;
                                }
                                boolean wasRemoved = company.remove(new Employee(profile));
                                if(!wasRemoved){
                                    System.out.println("Employee does not exist.");
                                }else{
                                    System.out.println("Employee removed.");
                                }
                            } catch (Exception e) {

                            }

                        }
                        break;
                    case "S":
                        if (input.countTokens() == EMP_ARGUMENTS) {
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
                                Employee partTime = e;

                                boolean hoursSet = company.setHours(partTime);

                                if(!hoursSet){
                                    System.out.println("Employee does not exist.");
                                }else{
                                    System.out.println("Working hours set.");
                                }

                            } catch (Exception e) {

                            }

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
     * to do:
     *
     * check if the name is valid -> run through tokenizer and see if there are two tokens between a comma
     * check if the department is valid -> exactly ECE / IT / CS
     * check date -> isValid method
     *
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
