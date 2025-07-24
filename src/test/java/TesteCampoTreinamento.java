import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.*;

public class TesteCampoTreinamento {
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void testeTextField() {
		// Criação do driver do Firefox

		dsl.escreve("elementosForm:nome", "Teste de escrita");

		assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));

	}

	@Test
	public void deveInteragirComTextArea() {
	    dsl.escreve("elementosForm:sugestoes", "teste\nnaasldjdlks\nUltima linha");

	    assertEquals(
	        "teste\nnaasldjdlks\nUltima linha",
	        dsl.obterValorCampo("elementosForm:sugestoes"));
	}


	@Test
	public void deveInteragirComButton() {

		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

		driver.findElement(By.id("elementosForm:sexo:0")).click();
		assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());

	}

	@Test
	public void deveInteragirComCheckbox() {

		dsl.clicarradio("elementsForm:comidaFavorita:2");

		assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

	}

	@Test
	public void deveInteragirComCombo() {
		
		dsl.selecioanrCombo("elementosForm:escolaridade", "2o grau completo");
		
		Assert.assertEquals("2o grau completo", dsl.obterValorCampo("elementosform:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecioanrCombo("elementosForm:esportes", "Natacao");
		dsl.selecioanrCombo("elementosForm:esportes", "Corrida");
		dsl.selecioanrCombo("elementosForm:esportes", "o que eh esporte?");
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3,  allSelectedOptions.size());
		
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions(); 
		Assert.assertEquals(2, allSelectedOptions.size());
	}
	
	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimples");
		WebElement botao = driver.findElement(By.id("value"));
		Assert.assertEquals("Obrigado !", botao.getAttribute("value"));
		
	}
	
	@Test
	public void deveInteragirComLinks() {
		dsl.clicarLink("Voltar");
		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}

	@Test
	public void deveBuscarTextosNaPagina() {
		Assert.assertEquals("Campo de treinamento", dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));


	}
	

}
