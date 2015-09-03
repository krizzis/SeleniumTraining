
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class SwitchWindow {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://www.enterprise.com/";

        // Maximize the browser's window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSwitchWindows() throws Exception {
        driver.get(baseUrl);
        Thread.sleep(2000);

        driver.findElement(By.xpath("//table[@id='standardTab']//a[text()='Airport']")).click();

        // Get the handle
        String parentHandle = driver.getWindowHandle();

        //Get handles
        Set<String> handles = driver.getWindowHandles();

        for (String handle: handles)
        {
            if(!handle.equals(parentHandle))
            {
                //Switch to the new window handle
                driver.switchTo().window(handle);
                Thread.sleep(4000);

                //Click on San Francisco link
                driver.findElement(By.xpath("//div[@id='content']//a[text()='San Francisco, CA']")).click();


                //Switch to parent window
                driver.switchTo().window(parentHandle);

            }
        }
    }

    @After
    public void tearDown() throws Exception
    {

    }



}
