import java.util.Scanner;
import java.util.StringTokenizer;

public class PayrollProcessing {

    public static final int ADD_EMP_ARGUMENTS = 4;
    public static final int ADD_ROLE_ARGUMENTS = 5;
    public static final int ALLOWED_NAME_ARGUMENTS = 2;

    public void run() {
        boolean running = true;
        Company company = new Company();
        System.out.println("Payroll Processing starts.");

        while(running) {
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine(); // Read user input
            try {
                StringTokenizer input = new StringTokenizer(s);
                String command = input.nextToken();


                switch (command) {

                    // add a part-time employee with the hourly pay rate
                    case "AP":
                        if (input.countTokens() == ADD_EMP_ARGUMENTS)
                            try {
                                String name = input.nextToken();
                                String department = input.nextToken();
                                String strDate  = input.nextToken();
                                String salary = input.nextToken();

                                company.add(new Employee(new Profile(name, department, new Date(strDate))));

                            } catch (Exception e) {
                                System.out.println("Invalid Command!");
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
     *
     *
     */
    private Profile profileValidator(String name, String department, String strDate, String salary) {

        boolean nameChecked, departmentChecked, dateChecked, salaryChecked;
        StringTokenizer nameCheck = new StringTokenizer(name, ",");

        nameChecked = nameCheck.countTokens()==ALLOWED_NAME_ARGUMENTS;
        departmentChecked = department.equals("ECE") || department.equals("IT") || department.equals("CS");

        Date date = new Date(strDate);
        dateChecked = date.isValid();




        if (nameChecked){
            return new Profile(name,department,date)
        }
        return null;
    }
}
