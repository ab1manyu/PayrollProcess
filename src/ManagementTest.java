import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagementTest {
    Profile profile1, profile2, profile3,profile4;
    Employee employee;
    Company company;

    Management management1, management2, management3;

    @BeforeEach
    void setUp() {
        company = new Company();

        profile1 = new Profile("Chang,Lily","CS", new Date());
        profile2 = new Profile("Chang,Flower","ECE", new Date());
        profile3 = new Profile("Chang,Petunia","IT", new Date());
        profile4 = new Profile("Chang,Rose","IT", new Date());

        employee = new Employee(profile1);
    }

    @Test
    void calculatePayment() {
        management1 = new Management(profile1,26000, 1);
        management2 = new Management(profile2,26000, 2);
        management3 = new Management(profile3,26000,3);

        company.add(management1);
        company.add(management2);
        company.add(management3);

        management1.calculatePayment(); // Expected payment is (26000/26) + (5000/26) = 1192.31
        management2.calculatePayment(); // Expected payment is (26000/26) + (9500/26) = 1365.38
        management3.calculatePayment(); // Expected payment is (26000/26) + (12000/26) = 1461.54

        assertEquals("1192.31", management1.getFormattedPayment()); //Test case for Manager
        assertEquals("1365.38", management2.getFormattedPayment()); //Test case for Dept. Head
        assertEquals("1461.54", management3.getFormattedPayment()); //Test case for Director
    }
}
