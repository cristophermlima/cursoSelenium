import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.*;
public class TesteGoogle {

    @Test
    public void teste() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");
        System.out.println(driver.getTitle());
        Assert.assertEquals("Google", driver.getTitle());
        driver.quit();
    }
}
