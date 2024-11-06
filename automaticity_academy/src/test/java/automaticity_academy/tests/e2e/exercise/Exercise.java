package automaticity_academy.tests.e2e.exercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Exercise {

    WebDriver driver;

    @BeforeClass
    public void initialize(){
        driver = new ChromeDriver();
    }

    @Test(enabled = false)
    public void streamFilter(){
        ArrayList<String> names = new ArrayList<>();
        names.add("Adam");
        names.add("Milos");
        names.add("Aleksandar");
        names.add("Jovan");
        names.add("Aleksa");

        Long c = names.stream().filter(s->s.startsWith("A")).count();
        System.out.println(c);
//        names.stream().

        long d = Stream.of("Adam","Milos","Aleksandar","Jovan","Aleksa").filter(s->{
            s.startsWith("A");
            return true;
        }).count();
        System.out.println(d);
        names.stream().filter(s->s.length()>4).forEach(s->System.out.println(s));
        names.stream().filter(s->s.length()>4).limit(1).forEach(s->System.out.println(s));
    }

    @Test
    public void streamMap(){
        ArrayList<String> names = new ArrayList<>();
        names.add("Anja");
        names.add("Milica");
        names.add("Ena");
        names.add("Jovana");
        names.add("Ana");

        // print names that starts with a with uppercase
       Stream.of("Adam","Milos","Aleksandra","Jovan","Aleksa").filter(s -> s.endsWith("a")).map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
        // print names which have first letter as a with uppercase and sorted
        List<String>names1= Arrays.asList("Adam","Milos","Aleksandra","Jovan","Aleksa");
    names.stream().filter(s -> s.startsWith("A")).sorted().map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));

    // mergin 2 different lists
    Stream<String> newStream = Stream.concat(names.stream(),names1.stream ());
//    newStream.sorted().forEach(s -> System.out.println(s));
    boolean flag = newStream.anyMatch(s -> s.equalsIgnoreCase("Adam"));
    System.out.println(flag);
        Assert.assertTrue(flag);
    }

    @Test
    public void streamCollect(){
        List<String> ls = Stream.of("Adam","Milos","Aleksandra","Jovan","Aleksa").filter(s-> s.endsWith("a")).map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(ls.get(0));
        List<Integer> values = Arrays.asList(3,2,2,7,5,1,9,7);

//        values.stream().distinct().forEach(s->System.out.println(s));
        List<Integer> li = values.stream().distinct().sorted().collect(Collectors.toList());
            System.out.println(li);

        }

    @Test
    public void tablesStream(){
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
        driver.findElement(By.id("search-field")).sendKeys("Rice");
        List<WebElement> veggies = driver.findElements(By.xpath("//tr/td[1]"));
        List<WebElement> filteredList = veggies.stream().filter(veggie -> veggie.getText().contains("Rice")).collect(Collectors.toList());
        Assert.assertEquals(veggies.size(),filteredList.size());
    }


}
