import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Julia Wigenstedt
 * Date: 2020-10-08
 * Time: 19:26
 * Project: Ass2Database
 * Copyright: MIT
 */
public class UtilityTest {

    Utility utility = new Utility();

    public UtilityTest() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
    }

    @Test
    public final void createListFromFileTest(){
            assertEquals(utility.customersDatabase.size(), 14);
            assertEquals(utility.customersDatabase.get(0).getName(), "Alhambra Aromes");

    }
    @Test
    public final void deSerializeTest() throws ClassNotFoundException {

            utility.deSerialize();
            assertDoesNotThrow(utility::deSerialize);
            assertEquals(utility.customersDatabase.get(2).getName(), "Chamade Coriola");

    }
    @Test
    public final void getCustomerTest(){
        String name = "Julia Wigenstedt";
        assertNull(utility.getCustomer(name));
        name = "Mitsuko Mayotte";
        assertNotNull(utility.getCustomer(name));
    }
}




