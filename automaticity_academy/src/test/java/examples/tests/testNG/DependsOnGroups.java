package examples.tests.testNG;

import org.testng.annotations.*;

public class DependsOnGroups {

    @Test(groups = "group1",dependsOnGroups = "group3")
    public void testOne(){
        System.out.println("TEST NUMBER ONE");
    }

    @Test(groups = "group2", dependsOnGroups = "group1")
    public void testTwo(){
        System.out.println("TEST NUMBER TWO");
    }

    @Test(groups = "group3")
    public void testThree(){
        System.out.println("TEST NUMBER THREE");
    }

}

