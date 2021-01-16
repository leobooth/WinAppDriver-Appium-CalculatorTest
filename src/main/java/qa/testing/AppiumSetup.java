package qa.testing;

import java.util.concurrent.TimeUnit;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumSetup {

  public static AppiumDriverLocalService APPIUM_DRIVER_LOCAL_SERVICE;
  public static WindowsDriver<WebElement> WINDOWS_DRIVER_SESSION;

  public static void startWindowsDriverSession() {
    APPIUM_DRIVER_LOCAL_SERVICE = AppiumDriverLocalService.buildDefaultService();
    APPIUM_DRIVER_LOCAL_SERVICE.start();

    try {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
      capabilities.setCapability("platformName", "Windows");
      capabilities.setCapability("deviceName", "WindowsPC");
      capabilities.setCapability("ms:experimental-webdriver", true);
      WINDOWS_DRIVER_SESSION = new WindowsDriver<>(APPIUM_DRIVER_LOCAL_SERVICE.getUrl(), capabilities);
      WINDOWS_DRIVER_SESSION.manage().timeouts().implicitlyWait(ProjectSettings.IMPLICIT_WAIT_TIME_IN_SECONDS, TimeUnit.SECONDS);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void stopWindowsDriverSession() {
    if (WINDOWS_DRIVER_SESSION != null) {
      WINDOWS_DRIVER_SESSION.quit();
    }
    WINDOWS_DRIVER_SESSION = null;
    APPIUM_DRIVER_LOCAL_SERVICE.stop();
  }
}
