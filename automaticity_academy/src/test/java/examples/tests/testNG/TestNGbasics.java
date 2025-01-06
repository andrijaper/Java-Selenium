package examples.tests.testNG;

import org.testng.Assert;

public class TestNGbasics {

    public static void main(String[] args) {

        int a  = 2;
        int b = 3;
        int c = a + b;
        Assert.assertEquals(c,5);
    }
}
