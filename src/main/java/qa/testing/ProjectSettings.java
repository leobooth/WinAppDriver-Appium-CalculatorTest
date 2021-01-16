package qa.testing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class ProjectSettings {

  public static final String PROJECT_HOME_DIRECTORY = "D:\\SideProjects\\AppiumImageLocators";
  public static final String CALCULATOR_APP_ID = "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App";
  public static final long IMPLICIT_WAIT_TIME_IN_SECONDS = 2;

  public static String getBase64ReferenceImage(String imagePath) throws IOException {
    File refImgFile = new File(imagePath);
    return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
  }

}
