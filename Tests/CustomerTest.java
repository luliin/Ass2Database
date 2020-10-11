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
            LocalDate.of(2019,10,22), "2020-03-11");


    @Test
    public final void constructorTest(){
        assertTrue(customer.isActiveMember);
        assertEquals(customer.getName(), "Julia Wigenstedt");
    }

    @Test
    public final void printListOfTrainingSessionsTest(){
        assertEquals(customer.printListOfTrainingSessions(), "Julia Wigenstedt har inga loggade träningspass.");
        customer.listOfTrainingSessions.add(LocalDate.now().toString());
        assertEquals(customer.printListOfTrainingSessions(), "2020-10-08");
    }
}
