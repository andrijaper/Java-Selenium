package examples.tests.testNG;

import org.testng.annotations.Test;

public class ExpectedException {

    @Test(expectedExceptions = ArithmeticException.class)
    public void testOne(){
        System.out.println(5/0);
    }

    @Test(expectedExceptions = ExpectedException.class)
    public void testTwo(){
        System.out.println("TEST WITH EXPECTED EXCEPTION CLASS");
        System.out.println(5/0);
    }

    @Test
    public void testThree(){
        System.out.println("TEST WITHOUT EXPECTED EXPECTION");
        System.out.println(5/0);
    }
}
