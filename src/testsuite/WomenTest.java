package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WomenTest extends Utility {

    By womenMenu = By.cssSelector("#ui-id-4");
    By topsMenu = By.cssSelector("#ui-id-9");
    By jackets = By.cssSelector("#ui-id-11");

    @Before
    public void setUp() {
        String baseUrl = "https://magento.softwaretestingboard.com/";
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    /**
     * verifyTheSortByProductNameFilter
     * Mouse Hover on Women Menu
     * Mouse Hover on Tops
     * Click on Jackets
     * Select Sort By filter “Product Name”
     * Verify the products name display in
     * alphabetical order
     */
    @Test
    public void verifyTheSortByProductNameFilter() {
        //Mouse Hover on Women Menu
        mouseHoverOnElement(womenMenu);
        //Mouse Hover on Tops
        mouseHoverOnElement(topsMenu);
        //Click on Jackets
        mouseHoverAndClick(jackets);
        //Select Sort By filter “Product Name”
        WebElement dropDownList = driver.findElement(By.xpath("//select[@id='sorter']"));
        Select select = new Select(dropDownList);
        select.selectByIndex(1);
        //Verify the products name display in alphabetical order

        List<WebElement> listOfElements = driver.findElements(By.xpath("//div[@class='product details product-item-details']//strong[@class='product name product-item-name']"));

        ArrayList<String> actualList = new ArrayList<>();
        for (WebElement element : listOfElements) {
            actualList.add(element.getText());
        }
        System.out.println(actualList);

        ArrayList<String> expectedList = new ArrayList<>();
        for (WebElement element : listOfElements) {
            expectedList.add(element.getText());
        }

        Collections.sort(expectedList);
        System.out.println(expectedList);
        Assert.assertEquals("Not matching", expectedList, actualList);

    }

    /**
     * verifyTheSortByPriceFilter
     * Mouse Hover on Women Menu
     * Mouse Hover on Tops
     * Click on Jackets
     * Select Sort By filter “Price”
     * Verify the products price display in
     * Low to High
     */
    @Test
    public void verifyTheSortByPriceFilter() throws ParseException, InterruptedException {
        //Mouse Hover on Women Menu
        mouseHoverOnElement(womenMenu);
        //Mouse Hover on Tops
        mouseHoverOnElement(topsMenu);
        //Click on Jackets
        mouseHoverAndClick(jackets);
        //Select Sort By filter “Price”
        WebElement dropDownList = driver.findElement(By.xpath("//select[@id='sorter']"));
        Select select = new Select(dropDownList);
        select.selectByIndex(2);

        //Verify the products price display in Low to High
        Thread.sleep(2000);
        List<WebElement> listOfElements = driver.findElements(By.xpath("//div[@class='product details product-item-details']//div[@class='price-box price-final_price']//span[@class='price']"));
        Thread.sleep(1000);
        ArrayList<String> actualList = new ArrayList<>();
        for (WebElement element : listOfElements) {
            String s = element.getText();
            s = s.replaceAll("\\$", "");
            actualList.add(s);
        }
        System.out.println(actualList);

        ArrayList<String> expectedList = new ArrayList<>();
        for (WebElement element : listOfElements) {
            String s = element.getText();
            s = s.replaceAll("\\$", "");
            expectedList.add(s);
        }
        Collections.sort(expectedList);
        System.out.println(expectedList);
        Assert.assertEquals("Not matching", expectedList, actualList);

    }

}
