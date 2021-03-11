package homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static homepage.HomePageWebElement.*;

public class HomePage {

    //action method class

    //Find by Annotation:
    @FindBy(how = How.ID, using = searchBoxWebLocator)public WebElement searchBox;
    @FindBy(how = How.ID, using = searchButtonLocator)public WebElement searchButton;

//    //Find by Annotation: second approach
//    @FindBy(id=searchBoxWebLocator)public WebElement searchBox1;
//    //third approach
//    @FindBy(id=searchBoxWebLocator)private WebElement searchBox2;
//        //generate getters and setters to access
//    public WebElement getSearchBox2() {
//        return searchBox2;
//    }
//    public void setSearchBox2(WebElement searchBox2) {
//        this.searchBox2 = searchBox2;
//    }
//
//
//
//    public void demo(){
//        searchBox.sendKeys("Mask");
//        searchBox.click();
//    }

}


