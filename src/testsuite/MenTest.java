package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class MenTest extends Utility {


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
     * userShouldAddProductSuccessFullyTo
     * ShoppinCart()
     * Mouse Hover on Men Menu
     * Mouse Hover on Bottoms
     * Click on Pants
     * Mouse Hover on product name
     * ‘Cronus Yoga Pant’ and click on size
     * 32.
     * Mouse Hover on product name
     * ‘Cronus Yoga Pant’ and click on colour
     * Black.
     * Mouse Hover on product name
     * ‘Cronus Yoga Pant’ and click on colour
     * Black.
     * Mouse Hover on product name
     * ‘Cronus Yoga Pant’ and click on
     * ‘Add To Cart’ Button.
     * Verify the text
     * ‘You added Cronus Yoga Pant to your shopping cart.’
     * Click on ‘shopping cart’ Link into
     * message
     * Verify the text ‘Shopping Cart.’
     * Verify the product name ‘Cronus Yoga Pant’
     * Verify the product size ‘32’
     * Verify the product colour ‘Black’
     */

    By menMenu = By.cssSelector("#ui-id-5");
    By bottomMenu = By.cssSelector("#ui-id-18");
    By pants = By.cssSelector("#ui-id-23");
    By cronusYogaPant = By.cssSelector("img[alt='Cronus Yoga Pant ']");
    By size32 = By.xpath("//div[@class='swatch-opt-880']//div[@id='option-label-size-143-item-175']");
    By colourBlack = By.cssSelector("#option-label-color-93-item-49");
    By addToCart = By.xpath("//div[@class='actions-primary']//span[text()='Add to Cart'][1]");
    By shoppingCart = By.xpath("//a[normalize-space()='shopping cart']");


    @Test
    public void userShouldAddProductSuccessFullyToShoppingCart() {
        //Mouse Hover on Men Menu
        mouseHoverOnElement(menMenu);
        //Mouse Hover on Tops
        mouseHoverOnElement(bottomMenu);
        //Click on Jackets
        mouseHoverAndClick(pants);
        //Mouse Hover on product name Cronus Yoga Pant and click on size 32.

        mouseHoverOnElement(cronusYogaPant);
        clickOnElement(size32);

        //Mouse Hover on product name Cronus Yoga Pant and click on colour Black.

        mouseHoverOnElement(cronusYogaPant);
        clickOnElement(colourBlack);

        //Mouse Hover on product name 'Cronus Yoga Pant’ and click on ‘Add To Cart’ Button.
        mouseHoverOnElement(cronusYogaPant);
        clickOnElement(addToCart);

        //Verify the text ‘You added Cronus Yoga Pant to your shopping cart'.

        String expectedMessage = "You added Cronus Yoga Pant to your shopping cart.";
        WebElement verifyText = driver.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
        String actualMessage = verifyText.getText();
        Assert.assertEquals("Not matching", expectedMessage, actualMessage);

        clickOnElement(shoppingCart);

        String expectedMessageShoppingCart = "Shopping Cart";
        WebElement actualText1 = driver.findElement(By.xpath("//span[@class='base' and text()='Shopping Cart']"));
        String actualMessageShoppingCart = actualText1.getText();
        Assert.assertEquals("Not matching", expectedMessageShoppingCart,actualMessageShoppingCart);

        String expectedMessageName = "Cronus Yoga Pant";
        WebElement actualNameTextElement = driver.findElement(By.xpath("//td[@class='col item']//a[normalize-space()='Cronus Yoga Pant']"));
        String actualMessageName = actualNameTextElement.getText();
        Assert.assertEquals("Not matching", expectedMessageName,actualMessageName);

        String expectedSize = "32";
        WebElement actualSizeTextElement = driver.findElement(By.xpath("//dd[contains(text(),'32')]"));
        String actualSize = actualSizeTextElement.getText();
        Assert.assertEquals("Not matching", expectedSize,actualSize);

        String expectedColour = "Black";
        WebElement actualColourTextElement = driver.findElement(By.xpath("//dd[contains(text(),'Black')]"));
        String actualColour = actualColourTextElement.getText();
        Assert.assertEquals("Not matching", expectedColour,actualColour);

    }


}
