package com.hubspot.qa.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hubspot.qa.pages.BasePage;
import com.hubspot.qa.pages.ContactsPage;
import com.hubspot.qa.pages.HomePage;
import com.hubspot.qa.pages.LoginPage;
import com.hubspot.qa.pages.SalesPage;
import com.hubspot.qa.util.TestUtil;

public class SalesPageTest {
	

	public WebDriver driver;
	public BasePage basePage;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public SalesPage salesPage;
	
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init();
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		salesPage = homePage.goToSalesPage();
	
	}
	@DataProvider
	public Object[][] getSalesTestData(){
		return TestUtil.getTestData("Sheet2");
	}
		
		
		
	@Test(dataProvider= "getSalesTestData")
		public void createNewDeal(String Name, String Amount ) {
		salesPage.createNewDeal(Name, Amount);
	

	
	}
		
	
		
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	

}
