package br.com.linnik.test.producao;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import junit.framework.Assert;

public class HealthCheckIT {
	
	@Test
	public void healthCheck() throws MalformedURLException  {				
		System.setProperty("webdriver.chrome.driver","/home/igorlinnik/Downloads/devops/chromedriver");		
		ChromeOptions options = new ChromeOptions();		
		options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");		
        options.addArguments("--headless");       
        WebDriver driver = new ChromeDriver(options);        
        try {        
	        //IP ONDE A APLICACAO ESTA RODANDO
			driver.navigate().to("http://localhost:9999/tasks");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
		} finally {
			driver.quit();
		}				
	}
}
