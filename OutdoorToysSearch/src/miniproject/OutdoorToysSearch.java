package miniproject;

import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OutdoorToysSearch extends DriverSetup
{
	WebDriver driver;
	  
	public OutdoorToysSearch(WebDriver driver) 
	{   
		//Constructor method
		this.driver=driver;
	}

	//To open the ebay.com
	
	public void openBrowser() 
	{
		String url="https://www.ebay.com";
		driver.manage().window().maximize();               //Maximizing the window
		driver.get(url);                                   //Opening the url
		
	}
	
	//To search for the "search box" in ebay.com and giving input through data driven(Excel Sheet) concept.
	
	public void searchElement() throws IOException 
	{
		String input=".\\Datafiles\\input.xlsx";
		FileInputStream inputstream=new FileInputStream(input);
		
		XSSFWorkbook workbook=new XSSFWorkbook(inputstream);
		XSSFSheet sheet=workbook.getSheet("sheet1");

		Row row=sheet.getRow(0);
		Cell cell=row.getCell(0);
		
		String n=cell.getStringCellValue();
		
		 
		WebElement searchTxtBox=driver.findElement(By.id("gh-ac"));
		searchTxtBox.sendKeys(n);
		driver.findElement(By.id("gh-btn")).click();
		workbook.close();
	}
	
	
	//To verify results page is as same title as original title
	
	public String verifyResultsPage() 
	{
		String title=driver.getTitle();                     //Get the title of the results page
		System.out.println("Title is: "+title);
		String expected="outdoor toys | eBay";
		if(title.equals(expected)) 
		{
			System.out.println("Yes,The page title starts with outdoor toys");
		}
		else 
		{
			System.out.println("No,The page title doesn't start with outdoor toys");
		}
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		return null;
	}
	
	
	//To get all the "href" values and clicking the first "Portable" text link from it 
	public void getLinks() 
	{
		List<WebElement> anchors = driver.findElements(By.tagName("a"));
		  int totallinks= anchors.size();
		  System.out.println("Total Outdoor toys links:" +totallinks);
		  
		  WebElement port = driver.findElement(By.partialLinkText("Portable"));
		  String link = port.getAttribute("href");
		  System.out.println("Portable Product links:" +link);
		  
		  driver.get(link);
		  String title1 = driver.findElement(By.id("itemTitle")).getText();
		  
		  System.out.println("Product title :" +title1);
	}
	//To close the browser
	public void closeBrowser() 
	{
		driver.close();
    }


}
