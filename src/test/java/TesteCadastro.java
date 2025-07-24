import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializado() {
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);

	}

	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void deveInteragirComAlertprompt() {
		dsl.escreve("elementosForm:nome", "Wagner");
		dsl.escreve("elementosForm:sobrenome", "Costa");
		dsl.clicarradio("elementosForm:sexo:0");
		dsl.clicarradio("elementosForm:comidaFavorita:2");
		dsl.selecioanrCombo("elementosForm:escolaridade", "Mestrado");
		dsl.selecioanrCombo("elementosForm:esportes", "Natacao");
		dsl.clicarBotao("elementosForm:cadastrar");
		

		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Wagner"));
		Assert.assertEquals("Sobrenome: Costa", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: mestrado", dsl.obterTexto("descComida"));
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));

		driver.quit();
	}

}
