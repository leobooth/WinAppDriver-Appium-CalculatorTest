package test.java.qa.testing;

import static main.java.qa.testing.ProjectSettings.IMAGE_LOCATOR_HOME_DIRECTORY;
import static main.java.qa.testing.AppiumSetup.WINDOWS_DRIVER_SESSION;

import main.java.qa.testing.AppiumSetup;
import main.java.qa.testing.ImageLocator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class CalculatorImageLocatorTest {

  private static WebElement CalculatorResult = null;

  @BeforeClass
  public static void setup() {
    AppiumSetup.startWindowsDriverSession();
    CalculatorResult = WINDOWS_DRIVER_SESSION.findElementByAccessibilityId("CalculatorResults");
    Assert.assertNotNull(CalculatorResult);
  }

  @AfterClass
  public static void TearDown() {
    AppiumSetup.stopWindowsDriverSession();
  }

  @Test
  public void Addition() {
    WINDOWS_DRIVER_SESSION.findElementByName("One").click();
    WINDOWS_DRIVER_SESSION.findElementByName("Plus").click();
    WINDOWS_DRIVER_SESSION.findElementByName("Seven").click();
    WINDOWS_DRIVER_SESSION.findElementByName("Equals").click();
    Assert.assertEquals("8", FormatCalculatorResultsText());
  }

  @Test
  public void AdditionByImage() {
    ImageLocator imageButton1 = new ImageLocator(IMAGE_LOCATOR_HOME_DIRECTORY, "button_1.png");
    ImageLocator imageButtonPlus = new ImageLocator(IMAGE_LOCATOR_HOME_DIRECTORY, "button_plus.png");
    ImageLocator imageButtonEquals = new ImageLocator(IMAGE_LOCATOR_HOME_DIRECTORY, "button_equals.png");
    ImageLocator imageDisplayResult = new ImageLocator(IMAGE_LOCATOR_HOME_DIRECTORY, "results_display_1_plus_1_equals_2.png");
    ImageLocator imageChessPawn = new ImageLocator(IMAGE_LOCATOR_HOME_DIRECTORY, "chess_pawn.png");

    // TODO: find a way to gracefully handle finding elements by image
    //  if the image matching threshold is not met
    WebElement button1 = WINDOWS_DRIVER_SESSION.findElementByImage(imageButton1.getLocator());
    WebElement buttonPlus = WINDOWS_DRIVER_SESSION.findElementByImage(imageButtonPlus.getLocator());
    WebElement buttonEquals = WINDOWS_DRIVER_SESSION.findElementByImage(imageButtonEquals.getLocator());
    WebElement displayResult = WINDOWS_DRIVER_SESSION.findElementByImage(imageDisplayResult.getLocator());
//    WebElement chessPawn = WINDOWS_DRIVER_SESSION.findElementByImage(imageChessPawn.getLocator());

    button1.click();
    buttonPlus.click();
    button1.click();
    buttonEquals.click();
    boolean isDisplayResultVisible = displayResult.isDisplayed();
//    boolean isChessPawnVisible = chessPawn.isDisplayed();

    // use image locator scores to calibrate the image matching threshold
    System.out.println("button 1 score:" + button1.getAttribute("score"));
    System.out.println("button plus score:" + buttonPlus.getAttribute("score"));
    System.out.println("button equals score:" + buttonEquals.getAttribute("score"));
    System.out.println("display result score:" + displayResult.getAttribute("score"));
//    System.out.println("chess pawn score:" + chessPawn.getAttribute("score"));

    Assert.assertEquals("2", FormatCalculatorResultsText());

    // TODO: create custom true/false assertions based on image threshold
    Assert.assertTrue("1+1 = 2 display result not found.", isDisplayResultVisible);
//    Assert.assertFalse("Chess pawn should not be visible.", isChessPawnVisible);
  }

  private String FormatCalculatorResultsText() {
    // trim extra text and whitespace off of the display value
    return CalculatorResult.getText().replace("Display is", "").trim();
  }
}
