package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.CsvUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest  {

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties application =  new Properties();
    public static FileInputStream  fis;
    public static Logger logger;
    public static CsvUtils csvUtils = new CsvUtils();

    public void init() throws IOException {
        PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");

        fis = new FileInputStream("./src/test/resources/properties/config.properties");
        config.load(fis);
        fis = new FileInputStream("./src/test/resources/properties/application.properties");
        application.load(fis);

        if(config.getProperty("browser").equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(config.getProperty("browser").equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.get(config.getProperty("baseurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);


    }
}