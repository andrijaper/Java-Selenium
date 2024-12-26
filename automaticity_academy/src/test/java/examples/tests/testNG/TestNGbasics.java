package examples.tests;

import org.testng.Assert;

public class testNGbasics {

    public static void main(String[] args) {

        int a  = 2;
        int b = 3;
        int c = a + b;
        Assert.assertEquals(c,5);
    }
}
