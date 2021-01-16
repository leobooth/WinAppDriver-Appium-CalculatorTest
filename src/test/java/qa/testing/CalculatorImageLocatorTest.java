package qa.testing;

import static qa.testing.ProjectSettings.PROJECT_HOME_DIRECTORY;
import static qa.testing.ProjectSettings.getBase64ReferenceImage;
import static qa.testing.AppiumSetup.WINDOWS_DRIVER_SESSION;

import java.io.IOException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NotFoundException;
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
    String base64Button1 = "";
    String base64ButtonPlus = "";
    String base64ButtonEquals = "";

    try {
      base64Button1 = getBase64ReferenceImage(PROJECT_HOME_DIRECTORY + "\\src\\main\\java\\qa\\testing\\imageLocators\\button_1.png");
      base64ButtonPlus = getBase64ReferenceImage(PROJECT_HOME_DIRECTORY + "\\src\\main\\java\\qa\\testing\\imageLocators\\button_plus.png");
      base64ButtonEquals = getBase64ReferenceImage(PROJECT_HOME_DIRECTORY + "\\src\\main\\java\\qa\\testing\\imageLocators\\button_equals.png");
    } catch (IOException e) {
      throw new NotFoundException("could not create base64 reference images");
    }

    WebElement button1 = WINDOWS_DRIVER_SESSION.findElementByImage(base64Button1);
    WebElement buttonPlus = WINDOWS_DRIVER_SESSION.findElementByImage(base64ButtonPlus);
    WebElement buttonEquals = WINDOWS_DRIVER_SESSION.findElementByImage(base64ButtonEquals);

    button1.click();
    buttonPlus.click();
    button1.click();
    buttonEquals.click();
    Assert.assertEquals("2", FormatCalculatorResultsText());
  }

  private String FormatCalculatorResultsText() {
    // trim extra text and whitespace off of the display value
    return CalculatorResult.getText().replace("Display is", "").trim();
  }
}
