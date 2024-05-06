package diyLambda;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginLT extends Elements {
    private WebDriver driver;

    @BeforeClass
    @Parameters({"browserName", "version", "platform"})
    public void setUp(String browserName, String version, String platform) throws MalformedURLException {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName(platform);
        browserOptions.setBrowserVersion(version);

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", "shailendradreamcast");
        ltOptions.put("accessKey", "u2Teyd7AerfIxFWmxcTPpL56WFL0UhmZIzWYoWKcnT6x41gzi3");
        ltOptions.put("browserName", browserName);
        ltOptions.put("version", version);
        ltOptions.put("platform", platform);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("network", true);
        ltOptions.put("resolution", "2560x1440");
        ltOptions.put("build", "SeleniumBuild");
        ltOptions.put("project", "SeleniumBuild");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL("https://hub.lambdatest.com/wd/hub"), browserOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
    }
    
    public void text(String locators, String keys) {
        driver.findElement(By.xpath(locators)).sendKeys(keys);
    }

    public void linkbtn(String locators) {
        driver.findElement(By.xpath(locators)).click();
    }

    @Test
    public void loginTest() throws InterruptedException {
        driver.get("https://event.godreamcast.com/staging-e/admin");

        text(email, "shailendra+1@dreamcast.co");
        linkbtn(contBtn);
        Thread.sleep(3000);
        text(pwd, "Testing1@3");
        linkbtn(contBtnfrompwd);

        linkbtn(goToEvent);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Switch to the new tab
        driver.switchTo().window(tabs.get(1)); // Assuming the new tab is the second one

        // Now you can perform actions in the new tab

        // For example, you can get the URL of the new tab
        String newTabUrl = driver.getCurrentUrl();
        System.out.println("URL of the new tab: " + newTabUrl);

        System.out.println(driver.getTitle());
        linkbtn(registerBtn);
        linkbtn(contibueBtnfrmQty);
        text(firstName, "Shailendra");
        text(lastName, "Singh");
        text(emailFront, "shailendra@dreamcast.co");
        linkbtn(saveForm);
        Thread.sleep(3000);
       // linkbtn(checkout);

        Thread.sleep(5000);
        driver.quit();
    }
}
