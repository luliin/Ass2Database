import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Julia Wigenstedt
 * Date: 2020-10-08
 * Time: 19:06
 * Project: Ass2Database
 * Copyright: MIT
 */
public class CustomerTest {

    Customer customer = new Customer("Julia Wigenstedt", "9003111234",
            LocalDate.of(2019,10,22));


    @Test
    public final void isActiveMemberTest(){
        assertTrue(customer.isActiveMember());

    }

    @Test
    public final void constructorTest() {

        assertEquals(customer.getName(), "Julia Wigenstedt");
        assertTrue(customer.listOfTrainingSessions.isEmpty());
    }

    @Test
    public final void printListOfTrainingSessionsTest(){
        assertNull(customer.listOfTrainingSessions());
        customer.listOfTrainingSessions.add(LocalDate.now().toString());
        assertEquals(customer.listOfTrainingSessions(), LocalDate.now().toString() +",");
    }
}
