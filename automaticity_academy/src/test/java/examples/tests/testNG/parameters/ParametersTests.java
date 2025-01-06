package examples.tests.testNG.parameters;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParametersTests {

    @Test
    @Parameters({"param1", "param2", "param3"})
    public void test(String param1, String param2, String param3){
        System.out.println("Parameter 1: "+param1);
        System.out.println("Parameter 2: "+param2);
        System.out.println("Parameter 3: "+param3);
    }
}
