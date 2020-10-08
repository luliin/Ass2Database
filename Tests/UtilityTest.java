import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Julia Wigenstedt
 * Date: 2020-10-08
 * Time: 19:30
 * Project: Ass2Database
 * Copyright: MIT
 */
public class UtilityTest {

    Utility utility = new Utility();

    public UtilityTest() throws ClassNotFoundException {
    }

    @Test
    public final void createListFromFileTest(){
            assertEquals(utility.customers.size(), 14);
            assertEquals(utility.customers.get(0).getName(), "Alhambra Aromes");

    }



}
