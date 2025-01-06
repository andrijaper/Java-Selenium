package examples.tests.testNG.dataProvider;

import org.testng.annotations.Test;

public class DataProviderTest {

    @Test(dataProvider = "providerName", dataProviderClass = DataProvderClass.class)
    public void test(int a, double b, String text){
        System.out.println(a+" "+b+" "+text);
    }
}
