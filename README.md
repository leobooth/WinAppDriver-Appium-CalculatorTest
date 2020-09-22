# WinAppDriver-Appium-CalculatorTest

This test demonstrates how to automate a Windows desktop application.

Examples show: 
* how to use WinAppDriver locators 
* how to use Appium image locators (findElementByImage).

**Setup:**
1. install node.js & npm
1. install opencv4nodejs
    1. navigate to your local node_modules folder  
        1. in git bash, run "which appium" to find out where it is (probably c:/Users/[your user]/AppData/Roaming/npm/npm_modules)
    1. run npm install -g opencv4nodejs  
        1. if you encounter an error because the installer cannot run cmake, install cmake
        1. visit cmake.org/download/ and download the latest installer. 
        1. close and reopen a command line window
        1. navigate again to node_modules folder and rerun command to install opencv4nodejs with cmake installed
    1. run npm link opencv4nodejs
1. add support for the latest version of appium-java-client
    1. navigate to github.com/appium/java-client
    1. add jitpack and java-client dependencies using Maven or Gradle (depending on your project)
    1. change the "version" number to the most recent release
1. run npm install -g appium-windows-driver  
1. activate Developer Mode on Windows 10
    1. (Settings / Updates & Security / For Developers / Developer Mode)
1. download and install the most recent version of WinAppDriver (1.2 Release Candidate)
1. download and install Appium Desktop

**To run demo:**
1. start Appium Server from Appium Desktop
1. run AppiumCalculatorTest.java from your IDE (such as IntelliJ)
