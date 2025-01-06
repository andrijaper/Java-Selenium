package examples.tests.testNG.annotations;

import org.testng.annotations.*;

public class TestNGAnnotations3 {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("BEFORE SUITE");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("BEFORE METHOD");
    }

    @BeforeGroups("group")
    public void beforeGroup(){
        System.out.println("BEFORE GROUP");
    }

    @AfterGroups("group")
    public void afterGroup(){
        System.out.println("AFTER GROUP");
    }

    @BeforeClass(groups = "group")
    public void beforeClass(){
        System.out.println("BEFORE CLASS");
    }

    @Test
    public void testOne(){
        System.out.println("TEST NUMBER ONE");
    }

    @AfterSuite(groups = "group")
    public void afterSuite(){
        System.out.println("AFTER SUITE");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("AFTER CLASS");
    }

    @AfterTest(groups = "group")
    public void afterTest(){
        System.out.println("AFTER TEST");
    }

    @Test(groups = "group")
    public void testTwo(){
        System.out.println("TEST NUMBER TWO");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("BEFORE TEST");
    }

    @AfterMethod(groups = "group")
    public void afterMethod(){
        System.out.println("AFTER METHOD");
    }
}