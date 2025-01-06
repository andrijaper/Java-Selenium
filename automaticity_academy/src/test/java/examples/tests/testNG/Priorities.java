package examples.tests.testNG;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class Priorities {

    @Test(priority = 3)
    public void testOne(){
        System.out.println("TEST NUMBER ONE");
    }

    @Test(priority = 1)
    public void testTwo(){
        System.out.println("TEST NUMBER TWO");
    }

    @Test(priority = 4)
    public void testThree(){
        System.out.println("TEST NUMBER THREE");
    }

    @Test(priority = 0)
    public void testFour(){
        System.out.println("TEST NUMBER FOUR");
    }

    @Test(priority = 2)
    public void testFive(){
        System.out.println("TEST NUMBER FIVE");
    }
}
