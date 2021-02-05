package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Web {
    public static final String USERNAME = "USERNAME"; //substituir pelos dados do browserstack
    public static final String AUTOMATE_KEY = "AUTOMATE_KEY"; //substituir pelos dados do browserstack
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static WebDriver createChrome(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }
    public static WebDriver createBrowserStack(){
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "88.0");
        caps.setCapability("name", "users's First Test"); //substituir pelos dados do browserstack

        WebDriver navegador = null;
        try{
            navegador = new RemoteWebDriver(new URL(URL), caps);
            navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            navegador.manage().window().maximize();
            navegador.get("http://www.juliodelima.com.br/taskit");
        }catch (MalformedURLException e) {
            System.out.println("Houveram problemas com a URL" + e.getMessage());
        }
        return navegador;
    }
}
