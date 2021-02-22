import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CompanyTest {
    Profile profile1, profile2, profile3,profile4;
    Employee employee;
    Company company;

    PartTime partTime;
    FullTime fullTime;
    Management management;

    @BeforeEach
    void setUp() {
        company = new Company();

        profile1 = new Profile("Chang,Lily","CS", new Date());
        profile2 = new Profile("Chang,Flower","ECE", new Date());
        profile3 = new Profile("Chang,Petunia","IT", new Date());
        profile4 = new Profile("Chang,Rose","IT", new Date());


    }

    @org.junit.jupiter.api.Test
    void add() {
        employee = new Employee(profile1);
        assertTrue(company.add(employee));
        assertFalse(company.add(employee));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        employee = new Employee(profile1);
        company.add(employee);
        assertTrue(company.remove(employee));
        assertFalse(company.remove(employee));
    }

    @Test
    void setHours() {
        partTime = new PartTime(profile1,30);
        fullTime = new FullTime(profile2,55000);
        management = new Management(profile3,60000,1);

        company.add(partTime);
        company.add(fullTime);
        company.add(management);

        assertTrue(company.setHours(new PartTime(partTime.getProfile(),0)));
        assertFalse(company.setHours(new PartTime(fullTime.getProfile(),0)));
        assertFalse(company.setHours(new PartTime(management.getProfile(),0)));
        assertFalse(company.setHours(new PartTime(profile4,0)));
    }

}
