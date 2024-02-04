package lt.techin.actions.julijav;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import static java.time.Duration.ofSeconds;

public class ActionWebUniversity {
    WebDriver driver;
    String givenUrl = "https://webdriveruniversity.com/Actions/index.html";

    @BeforeEach
//    @Timeout(5)

    void beforeEachTest() {
        driver = new ChromeDriver();
        driver.get(givenUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(ofSeconds(10));
    }

    @AfterEach
    void teardown() {

        driver.quit();
    }


    @Test
//1.Drag & Drop

    void dragDrop() {
        WebElement dropThisItem = driver.findElement(By.xpath("//b[.='DRAG ME TO MY TARGET!']"));
        WebElement dropOnThisItem = driver.findElement(By.xpath("//div[@id='droppable']/p"));

        Actions action = new Actions(driver);

        action.dragAndDrop(dropThisItem, dropOnThisItem).build().perform();
        WebElement afterDropTittle = driver.findElement(By.xpath("//div[@id='droppable']/p"));
    Assertions.assertEquals("Dropped!", afterDropTittle.getText());
    Assertions.assertNotEquals("DROP HERE!", afterDropTittle);
        String changedColor = dropOnThisItem.getCssValue("background-color");
    Assertions.assertEquals("rgba(244, 89, 80, 1)", changedColor);
    }

//2.Double Click
    @Test
    void doubleClick() {
        Actions action = new Actions(driver);

        WebElement doubleClickElement = driver.findElement(By.xpath("//div[@id='double-click']"));
        action.doubleClick(doubleClickElement).build().perform();
        String changedColor = doubleClickElement.getCssValue("background-color");
    Assertions.assertEquals("rgba(147, 203, 90, 1)",changedColor );
    }

//3. Peles uzvedimas ant 1, 2 , 3
        @Test
        void hoverFirstSecondThird()  {
    Actions action = new Actions(driver);

        WebElement hoverFirst = driver.findElement(By.xpath("//button[text()='Hover Over Me First!']"));
        action.moveToElement(hoverFirst).perform();
        String firstHoverColor = hoverFirst.getCssValue("background-color");
    Assertions.assertEquals("rgba(74, 73, 85, 1)", firstHoverColor );
        WebElement firstText = driver.findElement(By.xpath("//div[@id='div-hover']/div[1]//a"));
        String firstHoverText = firstText.getText();
            System.out.println("First hover: " + firstHoverText);
    Assertions.assertEquals("Link 1", firstHoverText);


        WebElement hoverSecond = driver.findElement(By.xpath("//button[text()='Hover Over Me Second!']"));
        action.moveToElement(hoverSecond).perform();
        WebElement hoverSecondLink = driver.findElement(By.xpath("//div[2]//a[@href='#']"));
        action.moveToElement(hoverSecondLink).perform();
        String hoverSecondLinkTextSize = hoverSecondLink.getCssValue("font-size");
    Assertions.assertEquals("14px", hoverSecondLinkTextSize);
         WebElement secondText = driver.findElement(By.xpath("//div[2]//a[@href='#']"));
         String secondHoverText = secondText.getText();
            System.out.println("Second hover: " +secondHoverText);
    Assertions.assertEquals("Link 1", secondHoverText);


        WebElement hoverThird = driver.findElement(By.xpath("//button[text()='Hover Over Me Third!']"));
        action.moveToElement(hoverThird).perform();

        WebElement hoverThirdLink1 = driver.findElement(By.xpath("//div[3]//a[1]"));
        action.moveToElement(hoverThirdLink1).perform();
        String hoverThirdLink1Text =hoverThirdLink1.getText();
    Assertions.assertEquals("Link 1", hoverThirdLink1Text);
            System.out.println("Third hover1: " +hoverThirdLink1Text);

        WebElement hoverThirdLink2 = driver.findElement(By.xpath("//div[3]//a[2]"));
        action.moveToElement(hoverThirdLink2).perform();
        String hoverThirdLink2Text =hoverThirdLink2.getText();
    Assertions.assertEquals("Link 1", hoverThirdLink2Text);
            System.out.println("Third hover2: " +hoverThirdLink2Text);
    }

// 4.   Click and Hold
    @Test
    void clickAndHold() {
        Actions action = new Actions(driver);
        driver.findElement(By.id("click-box")).click();
        WebElement notActiveBackground = driver.findElement(By.xpath("//div[@id='click-box']"));
        String notActiveColor = notActiveBackground.getCssValue("background-color");
        String notActiveText = notActiveBackground.getText();
        System.out.println("First background is red? " + notActiveColor);
        System.out.println("Dont release me!!! ? " + notActiveText);

    Assertions.assertEquals("rgba(255, 99, 71, 1)", notActiveColor);
    Assertions.assertEquals("Dont release me!!!", notActiveText);
            action.clickAndHold(notActiveBackground).perform();

        WebElement activeBackground = driver.findElement(By.xpath("//div[@id='click-box']"));
        String styleAttribute = activeBackground.getAttribute("style");
        String activeColor = styleAttribute.contains("background: rgb(0, 255, 0)")?"green" : "not green";
        String activeText = activeBackground.getText();
        System.out.println("First background is green? " + activeColor);
        System.out.println("Well done! keep holding that click now..... ? " + activeText);

    Assertions.assertTrue(styleAttribute.contains("background: rgb(0, 255, 0)"), "Expected background color is not green");
    Assertions.assertEquals("Well done! keep holding that click now.....", activeText);

    }


}
