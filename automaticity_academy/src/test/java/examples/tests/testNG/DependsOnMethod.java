package examples.tests.testNG;

import org.testng.annotations.Test;

public class DependsOnMethod {

    @Test(dependsOnMethods = "testThree")
    public void testOne(){
        System.out.println("TEST NUMBER ONE");
    }

    @Test
    public void testTwo(){
        System.out.println("TEST NUMBER TWO");
    }

    @Test(dependsOnMethods = "testTwo")
    public void testThree(){
        System.out.println("TEST NUMBER THREE");
    }

}

