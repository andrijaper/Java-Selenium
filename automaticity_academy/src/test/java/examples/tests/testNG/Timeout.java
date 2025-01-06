package examples.tests.testNG;

import org.testng.annotations.Test;

public class Timeout {

     @Test(timeOut = 5000)
     public void testOne() throws InterruptedException {
          Thread.sleep(4000);
          System.out.println("Test completed within the time limit");
     }

     @Test(expectedExceptions = ExpectedException.class)
     public void testTwo() throws InterruptedException {
          Thread.sleep(6000);
          System.out.println("This message will not be printed as the test will timeout");
     }
}
