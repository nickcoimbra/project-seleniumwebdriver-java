package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.Generator;
import support.Screenshot;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")
public class InformacoesUsuarioTest {
    private WebDriver navegador;
    @Rule
    public TestName test = new TestName();
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\projetos-software\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();
        navegador.get("http://www.juliodelima.com.br/taskit");
        navegador.findElement(By.linkText("Sign in")).click();
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox")); //Falicita em projetos grandes
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");
        navegador.findElement(By.linkText("SIGN IN")).click();
        navegador.findElement(By.className("me")).click();
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }


    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="Contato")String contato, @Param(name="mensagem")String mensagemEsperada){


        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);


    }

    @Test
    public void removerUmContatoDeUmUsuario(){
        //Logar-se na aplicação
        navegador.findElement(By.xpath("//span[text()=\"5551991234567\"]/following-sibling::a")).click();

        navegador.switchTo().alert().accept();

        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        String screenshotArquivo = "D:\\projetos-software\\screenshots\\" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshotArquivo);
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
        navegador.findElement(By.linkText("Logout")).click();





    }
    @After
    public void tearDown(){
        navegador.quit(); //fecha o navegador
        //navegador.close(); // fecha apenas uma aba do navegador
    }
}
