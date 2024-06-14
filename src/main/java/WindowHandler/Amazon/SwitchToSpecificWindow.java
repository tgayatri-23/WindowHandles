package WindowHandler.OrangeHRM;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SwitchToSpecificWindow {

	static WebDriver driver;

    public static void main(String[] args) {
        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        String ParentWindowId = driver.getWindowHandle();
       
        // Wait until the element is present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement linkedinLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href, 'linkedin.com/company/orangehrm')]")));
        linkedinLink.click();

        WebElement facebookLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href, 'www.facebook.com/OrangeHRM')]")));
        facebookLink.click();
        
        
        WebElement twitterLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href, 'https://twitter.com/orangehrm?lang=en')]")));
        twitterLink.click();
        
        
        WebElement youtubeLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href, 'https://www.youtube.com/c/OrangeHRMInc')]")));
        youtubeLink.click();
        
        Set<String> handles = driver.getWindowHandles();
        List<String> hList = new ArrayList<String>(handles);
        if(switchToRightWindow("Facebook", hList)) {
        	System.out.println(driver.getCurrentUrl() + " : " + driver.getTitle());
        }
        
        closeAllWindows(hList, ParentWindowId);
        switchToParentWindow(ParentWindowId);
        System.out.println(driver.getCurrentUrl() + " : " + driver.getTitle());
    }
    public static void closeAllWindows(List<String> hList,String parentWindowId)
    {
    	for(String e: hList )
    	{
    		if(!e.equals(parentWindowId)) {
    			driver.switchTo().window(e).close();
    		}
    	}
    }
    
    public static void switchToParentWindow(String parentWindowId){
    
    	driver.switchTo().window(parentWindowId);
    		
   }
    
    public static boolean switchToRightWindow(String windowTitle, List<String> hList) {
    	
    	for(String e:hList) {
    		String title = driver.switchTo().window(e).getTitle();
    		if(title.contains(windowTitle)) {
    			System.out.println("found the right window...");
    			return true;
    			
    		}
    	}
		return false;
    	
    }
    
 
    
    
    
    
    
}
