package examples.tests.testNG;

import org.testng.annotations.*;

public class TestNGAnnotations2 {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BEFORE SUITE");
    }

    @BeforeTest(groups = "group")
    public void beforeTest(){
        System.out.println("BEFORE TEST");
    }

    @BeforeGroups("group")
    public void beforeGroup(){
        System.out.println("BEFORE GROUP");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("BEFORE CLASS");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BEFORE METHOD");
    }

    @Test
    public void testOne(){
        System.out.println("TEST NUMBER ONE");
    }

    @Test(groups = "group")
    public void testTwo(){
        System.out.println("TEST NUMBER TWO");
    }

    @AfterMethod(groups = "group")
    public void afterMethod(){
        System.out.println("AFTER METHOD");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("AFTER CLASS");
    }

    @AfterGroups("group")
    public void afterGroup(){
        System.out.println("AFTER GROUP");
    }

    @AfterTest(groups = "group")
    public void afterTest(){
        System.out.println("AFTER TEST");
    }

    @AfterSuite(groups = "group")
    public void afterSuite(){
        System.out.println("AFTER SUITE");
    }
}
