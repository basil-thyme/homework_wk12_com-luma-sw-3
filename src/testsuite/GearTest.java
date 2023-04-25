package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

public class GearTest extends Utility {

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
     * Write down the following test into ‘GearTest’ class
     * 1. userShouldAddProductSuccessFullyTo
     * ShoppingCart()
     * Mouse Hover on Gear Menu
     * Click on Bags
     * Click on Product Name ‘Overnight Duffle’
     * Verify the text ‘Overnight Duffle’
     * Change Qty 3
     * Click on ‘Add to Cart’ Button.
     * Verify the text
     * ‘You added Overnight Duffle to your shopping cart.’
     * Click on ‘shopping cart’ Link into
     * message
     * Verify the product name ‘Cronus Yoga Pant’
     * Verify the Qty is ‘3’
     * Verify the product price ‘$135.00’
     * Change Qty to ‘5’
     * Click on ‘Update Shopping Cart’ button
     * Verify the product price ‘$225.00’
     */
    By gearMenu = By.cssSelector("#ui-id-6");
    By bags = By.cssSelector("#ui-id-25");
    By overNightDuffle = By.xpath("//img[@alt='Overnight Duffle']");
    By enterQuantity = By.xpath("//input[@id='qty']");
    By addToCart = By.xpath("//span[normalize-space()='Add to Cart']");
    By shoppingCart = By.xpath("//a[normalize-space()='shopping cart']");
    By updateShoppingCart = By.xpath("//span[normalize-space()='Update Shopping Cart']");
    By qtyInShoppingCart = By.cssSelector(".input-text.qty");

    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() throws InterruptedException {
        mouseHoverOnElement(gearMenu);
        mouseHoverAndClick(bags);

        WebElement dropDownList = driver.findElement(By.xpath("(//select[@id='sorter'])[1]"));
        Select select = new Select(dropDownList);
        select.selectByIndex(1);

        clickOnElement(overNightDuffle);
        WebElement qtyElement = driver.findElement(enterQuantity);
        qtyElement.clear();
        sendTextToElement(enterQuantity, "3");

        clickOnElement(addToCart);
        String expectedMessage = "You added Overnight Duffle to your shopping cart.";
        WebElement verifyText = driver.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
        String actualMessage = verifyText.getText();
        Assert.assertEquals("Not matching", expectedMessage, actualMessage);

        clickOnElement(shoppingCart);
        String expectedMessageShoppingCart = "Shopping Cart";
        WebElement actualText1 = driver.findElement(By.xpath("//span[@class='base' and text()='Shopping Cart']/parent::h1"));

        //System.out.println(actualText1);
        String actualMessageShoppingCart = actualText1.getText();
        Assert.assertEquals("Not matching", expectedMessageShoppingCart, actualMessageShoppingCart);

        String expectedProductName = "Overnight Duffle";
        WebElement actualNameTextElement = driver.findElement(By.xpath("//td[@class='col item']//a[normalize-space()='Overnight Duffle']"));
        String actualProductName = actualNameTextElement.getText();
        Assert.assertEquals("Not matching", expectedProductName, actualProductName);

        String expectedQuantity = "3";
        WebElement actualQtyTextElement = driver.findElement(By.cssSelector(".input-text.qty"));
        String actualQuantity = actualQtyTextElement.getAttribute("value");
        Assert.assertEquals("Not matching", expectedQuantity, actualQuantity);

        String expectedPrice = "$135.00";
        WebElement actualPriceElement = driver.findElement(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$135.00']"));

        String actualPrice = actualPriceElement.getText();
        String actual = "$" +actualPrice.replaceAll("\\$", "");
        Assert.assertEquals("Not matching", expectedPrice, actual);

        Thread.sleep(1000);
        WebElement qtyElement1 = driver.findElement(qtyInShoppingCart);
        qtyElement1.clear();
        Thread.sleep(1000);
        sendTextToElement(qtyInShoppingCart, "5");

        clickOnElement(updateShoppingCart);
        Thread.sleep(2000);

        String expectedUpdatedPrice = "$225.00";
        WebElement UpdatedPriceElement = driver.findElement(By.xpath("//span[@class='cart-price']//span[@class='price'][normalize-space()='$225.00']"));

        String price = UpdatedPriceElement.getText();
        String actualUpdatedPrice = "$" +price.replaceAll("\\$", "");
        Assert.assertEquals("Not matching", expectedUpdatedPrice, actualUpdatedPrice);

    }

}
