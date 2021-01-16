package main.java.qa.testing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class ProjectSettings {

  public static final String PROJECT_HOME_DIRECTORY = "D:\\SideProjects\\AppiumImageLocators";
  public static final String IMAGE_LOCATOR_HOME_DIRECTORY = "D:\\SideProjects\\AppiumImageLocators\\"
      + "src\\main\\java\\qa\\testing\\imageLocators\\";
  public static final long IMPLICIT_WAIT_TIME_IN_SECONDS = 2;
  public static final double IMAGE_MATCHING_THRESHOLD_MINIMUM = 0.6;

  public static String getBase64ReferenceImage(String imagePath) throws IOException {
    File refImgFile = new File(imagePath);
    return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
  }
}
