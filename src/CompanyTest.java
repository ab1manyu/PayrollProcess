import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    Profile profile1, profile2, profile3,profile4;
    Employee employee1, employee2;
    Company company;

    PartTime partTime;
    FullTime fullTime;
    Management management;

    /**
     * This is a setup method that runs the following code
     * before running the test methods.
     *
     * Creates a new company object and 4 distinct profiles
     * that will be used accordingly in each test method
     */
    @BeforeEach
    void setUp() {
        company = new Company();
        profile1 = new Profile("Chang,Lily","CS", new Date());
        profile2 = new Profile("Chang,Flower","ECE", new Date());
        profile3 = new Profile("Chang,Petunia","IT", new Date());
        profile4 = new Profile("Chang,Rose","IT", new Date());
    }

    @Test
    void add() {
        employee1 = new Employee(profile1);
        employee2 = new PartTime(profile1, 10);
        assertTrue(company.add(employee1));//Adding employee not in the list always true
        assertFalse(company.add(employee1));//Adding employee already in the list always false
        assertFalse(company.add(employee2));//Adding employee with the same profile in the list always false
    }

    @Test
    void remove() {
        employee1 = new Employee(profile1);
        company.add(employee1);
        assertTrue(company.remove(employee1));//Removing employee already in list always true
        assertFalse(company.remove(employee1));//Removing non-existent employee always false
    }

    @Test
    void setHours() {
        partTime = new PartTime(profile1,30);
        fullTime = new FullTime(profile2,55000);
        management = new Management(profile3,60000,1);

        company.add(partTime);
        company.add(fullTime);
        company.add(management);

        assertTrue(company.setHours(new PartTime(profile1,0))); //Sets hours of a PARTTIME employee always true
        assertFalse(company.setHours(new PartTime(profile2,0))); //Sets hours of a FULLTIME employee always false
        assertFalse(company.setHours(new PartTime(profile3,0))); //Sets hours of a MANAGEMENT employee always false
        assertFalse(company.setHours(new PartTime(profile4,0))); //Sets hours of a nonexistant employee always false
    }

}
