package miniproject;

import java.io.IOException;
import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup 
{
	static WebDriver driver; 	
	
	public static void main(String[] args) throws IOException 
	{   //main method
		System.out.println("Enter the browser(chrome or edge or firefox) to automate the miniproject:");
		Scanner scan=new Scanner(System.in);                  
		String input=scan.next();              //taking browser as inputs              
		String i=input.toLowerCase();                            
		if(i.contains("chrome")) 
		{
			//ChromeDriver works if input given as "chrome/Chrome/CHROME" 
			System.setProperty("webdriver.chrome.driver",
				".\\drivers\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(i.contains("edge"))
		{
			//MicrosoftEdge Driver if input is "EDGE/edge"
			System.setProperty("webdriver.edge.driver", 
					".\\drivers\\msedgedriver.exe");
			driver= new EdgeDriver();
		}
		else if(i.contains("firefox"))
		{
			//FireFox driver if input is "firefox/FireFox/FIREFOX"
			System.setProperty("webdriver.gecko.driver", 
					".\\drivers\\geckodriver.exe");
			driver= new FirefoxDriver();
		}
		else
		{
			System.out.println("Enter a valid browser");
		}
		scan.close();
		
		OutdoorToysSearch out=new OutdoorToysSearch(driver);    //Object Creation for OutdoorToysSearch class
		out.openBrowser();                                      //Calling openBrowser function                 
		out.searchElement();                                    //calling searchElement function
		out.verifyResultsPage();                                //calling verifyResultsPage function
		out.getLinks();                                         //calling getLinks function
		out.closeBrowser();                                     //calling closeBrowser function
	}



}
