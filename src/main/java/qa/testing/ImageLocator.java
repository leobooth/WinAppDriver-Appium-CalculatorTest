package main.java.qa.testing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import org.openqa.selenium.NotFoundException;

public class ImageLocator {
  private String imagePath;

  public ImageLocator(String imagePath) {
    this.imagePath = imagePath;
  }

  public ImageLocator(String imageDirectory, String imageFileName) {
    this.imagePath = imageDirectory + imageFileName;
  }

  private String getBase64ReferenceImage(String imagePath) throws IOException {
    File refImgFile = new File(imagePath);
    return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
  }

  public String getLocator() throws NotFoundException {
    String imageLocator = null;

    try {
      imageLocator = getBase64ReferenceImage(imagePath);
    } catch (IOException e) {
      throw new NotFoundException("Could not create base64 reference image from image at location: " + imagePath);
    }

    return imageLocator;
  }


}
