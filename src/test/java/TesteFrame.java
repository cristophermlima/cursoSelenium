import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFrame {
	 @Test
	    public void deveInteragirComAlertConfirm() {
	    	WebDriver driver = new FirefoxDriver();
	    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	    	
	    	driver.switchTo().frame("frame1");
	    	driver.findElement(By.id("frameButton")).click();
	    	Alert alert = driver.switchTo().alert();
	    	String msg = alert.getText();
	    	Assert.assertEquals("Frame OK!", msg);
	    	alert.accept();
	    	
	    	driver.switchTo().defaultContent();
	    	driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
}
	 @Test
	 public void deveInteragirComJanelas() {
		 WebDriver driver = new FirefoxDriver();
	    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	    	
	    	driver.findElement(By.id("buttonPopUpEasy")).click();
	    	driver.switchTo().window("Popup");
	    	driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
	    	driver.close();
	    	driver.switchTo().window("");
	    	driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
		 
	 }

}
