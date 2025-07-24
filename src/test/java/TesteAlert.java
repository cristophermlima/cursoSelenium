import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
	 @Test
	    public void deveInteragirComAlertConfirm() {
	    	WebDriver driver = new FirefoxDriver();
	    	driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	    	
	    	driver.findElement(By.id("confirm")).click();
	    	Alert alerta = driver.switchTo().alert();
	    	Assert.assertEquals("Confirm Simples", alerta.getText());
	    	alerta.accept();
	    	Assert.assertEquals("Confirmado", alerta.getText());
	    	alerta.accept();
	    	
	    	driver.quit();
	    	
	    }
	 
	 @Test
	 public void deveInteragirComAlertPrompt() {
		 WebDriver driver = new FirefoxDriver();
		 driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		 
		 driver.findElement(By.id("prompt")).click();
		 Alert alerta =driver.switchTo().alert();
		 Assert.assertEquals("Digite um numero", alerta.getText());
		 alerta.sendKeys("12");
		 alerta.accept();
		 Assert.assertEquals("Era 12", alerta.getText());
		 alerta.accept();
		 Assert.assertEquals(":D", alerta.getText());
		 alerta.accept();
	
		 driver.quit();
		 
	 }
	    
}
