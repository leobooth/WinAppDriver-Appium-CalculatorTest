package qa.testing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class ProjectUtilities {

  public static String PROJECT_HOME_DIRECTORY = "D:\\SideProjects\\AppiumImageLocators";

  public static String getBase64ReferenceImage(String imagePath) throws IOException {
    File refImgFile = new File(imagePath);
    return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
  }

}
