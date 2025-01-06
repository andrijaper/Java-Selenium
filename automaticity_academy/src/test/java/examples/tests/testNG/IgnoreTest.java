package examples.tests.testNG;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class IgnoreTest {

    @Test
    public void testOne(){
        System.out.println("TEST NUMBER ONE");
    }

    @Ignore
    @Test
    public void testTwo(){
        System.out.println("TEST NUMBER TWO");
    }

    @Test
    public void testThree(){
        System.out.println("TEST NUMBER THREE");
    }

    @Test(enabled = false)
    public void testFour(){
        System.out.println("TEST NUMBER FOUR");
    }

    @Test
    public void testFive(){
        System.out.println("TEST NUMBER FIVE");
    }
}
