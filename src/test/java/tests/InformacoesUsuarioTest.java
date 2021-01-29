package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class InformacoesUsuarioTest {
    private WebDriver navegador;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\projetos-software\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();
        navegador.get("http://www.juliodelima.com.br/taskit");
    }


    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){

        navegador.findElement(By.linkText("Sign in")).click();

        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox")); //Falicita em projetos grandes

        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");

        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        navegador.findElement(By.linkText("SIGN IN")).click();

        navegador.findElement(By.className("me")).click();

        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        popupAddMoreData.findElement(By.name("contact")).sendKeys("+5511999999999");

        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Your contact has been added!", mensagem);


    }

    @After
    public void tearDown(){
        navegador.quit(); //fecha o navegador
        //navegador.close(); // fecha apenas uma aba do navegador
    }
}
