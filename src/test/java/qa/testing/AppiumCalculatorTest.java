package qa.testing;

import static qa.testing.ProjectUtilities.PROJECT_HOME_DIRECTORY;
import static qa.testing.ProjectUtilities.getBase64ReferenceImage;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.windows.WindowsDriver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumCalculatorTest {

  private static AppiumDriverLocalService service;

  private static WindowsDriver CalculatorSession = null;
  private static WebElement CalculatorResult = null;

  @BeforeClass
  public static void setup() {
    service = AppiumDriverLocalService.buildDefaultService();
    service.start();

    try {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
      capabilities.setCapability("platformName", "Windows");
      capabilities.setCapability("deviceName", "WindowsPC");
      capabilities.setCapability("ms:experimental-webdriver",true);
      CalculatorSession = new WindowsDriver<>(service.getUrl(), capabilities);
      CalculatorSession.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

      CalculatorResult = CalculatorSession.findElementByAccessibilityId("CalculatorResults");
      Assert.assertNotNull(CalculatorResult);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @AfterClass
  public static void TearDown() {
    if (CalculatorSession != null) {
      CalculatorSession.quit();
    }
    CalculatorSession = null;
    service.stop();
  }

  @Test
  public void Addition() {
    CalculatorSession.findElementByName("One").click();
    CalculatorSession.findElementByName("Plus").click();
    CalculatorSession.findElementByName("Seven").click();
    CalculatorSession.findElementByName("Equals").click();
    Assert.assertEquals("8", FormatCalculatorResultsText());
  }

  @Test
  public void AdditionByImage() {
    String base64Button1 = "";
    String base64ButtonPlus = "";
    String base64ButtonEquals = "";

    try {
      base64Button1 = getBase64ReferenceImage(PROJECT_HOME_DIRECTORY + "\\src\\main\\java\\qa\\testing\\button_1.png");
      base64ButtonPlus = getBase64ReferenceImage(PROJECT_HOME_DIRECTORY + "\\src\\main\\java\\qa\\testing\\button_plus.png");
      base64ButtonEquals = getBase64ReferenceImage(PROJECT_HOME_DIRECTORY + "\\src\\main\\java\\qa\\testing\\button_equals.png");
    } catch (IOException e) {
      throw new NotFoundException("could not create base64 reference images");
    }

    WebElement button1 = CalculatorSession.findElementByImage(base64Button1);
    WebElement buttonPlus = CalculatorSession.findElementByImage(base64ButtonPlus);
    WebElement buttonEquals = CalculatorSession.findElementByImage(base64ButtonEquals);

    button1.click();
    buttonPlus.click();
    button1.click();
    buttonEquals.click();
    Assert.assertEquals("2", FormatCalculatorResultsText());
  }

  protected String FormatCalculatorResultsText() {
    // trim extra text and whitespace off of the display value
    return CalculatorResult.getText().replace("Display is", "").trim();
  }
}
