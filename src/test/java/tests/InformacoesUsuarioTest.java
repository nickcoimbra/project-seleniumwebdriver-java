package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class InformacoesUsuarioTest {
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){
        System.setProperty("webdriver.chrome.driver","D:\\projetos-software\\drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();
        navegador.get("http://www.juliodelima.com.br/taskit");
        navegador.findElement(By.linkText("Sign in")).click();
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox")); //Falicita em projetos grandes
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");
        navegador.findElement(By.linkText("SIGN IN")).click();
        WebElement me = navegador.findElement(By.className("me"));
        String textoNoElementoMe = me.getText();
        assertEquals("Hi, Julio", textoNoElementoMe);
        navegador.quit(); //fecha o navegador
        //navegador.close(); // fecha apenas uma aba do navegador
    }
}
