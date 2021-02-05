package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import support.Web;

public class InformacoesUsuarioPageObjectsTest {
    private WebDriver navegador;

    @Before
    public void setUp(){
        navegador = Web.createChrome();
    }
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario(){
        new LoginPage(navegador)
                .clickSignIn()
                .fazerLogin("julio0001", "123456") //abordagem funcional
                .clicarMe()
                .clicarAbaMoreDataAboutYou()
                .clicarBotaoAddMoreDataAboutYou();
                //.typeLogin("julio0001") abordagem estrutural
                //.typePassword("123456")
                //.clickSignIn();
    }

    @After
    public void tearDown(){
        navegador.quit();
    }
}
