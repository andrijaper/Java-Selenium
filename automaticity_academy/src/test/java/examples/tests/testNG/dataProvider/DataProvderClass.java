package examples.tests.testNG.dataProvider;

import org.testng.annotations.DataProvider;

public class DataProvderClass {

    @DataProvider(name="providerName")
    public Object[][]provide(){
        return new Object[][]{
                {1,2.3, "abc"},
                {44, 50.99, "efg"},
                {11,22,"true"}
                };
    }
}
