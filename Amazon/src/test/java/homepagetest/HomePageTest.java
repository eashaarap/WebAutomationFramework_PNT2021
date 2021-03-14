package homepagetest;

import common.WebAPI;
import homepage.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class HomePageTest extends WebAPI {
    // Test class
    HomePage homePage;
    // Registration registration;

    @BeforeMethod
    public void getInit(){
        homePage= PageFactory.initElements(driver,HomePage.class);
        // registration= PageFactory.initElements(driver,Registration.class);
    }

    //@Ignore
    @Test(enabled = true)
    public void testSearchBox() throws InterruptedException {
        //getInit();
        // Call Action method by reference variable
        homePage.searchBox();
        // Verify expected vs Actual
        String expectedText="\"Mask\"";
        String actualText=homePage.searchText.getText();
        Assert.assertEquals(actualText,expectedText,"Product does not match");
    }

    @Test
    public void testSearchBox1() throws InterruptedException {
        homePage.searchBox();
        sleepFor(3);
        String expectedText="\"Mask1\"";
        String actualText=homePage.searchText.getText();
        Assert.assertEquals(actualText,expectedText,"Product does not match");

    }


}





