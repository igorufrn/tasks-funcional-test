package br.com.linnik.test.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TaskTest {
	
	
	public WebDriver acessarAplicacao() {
		System.setProperty("webdriver.chrome.driver","/home/igorlinnik/Downloads/devops/chromedriver");		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");		
        options.addArguments("--headless");
        
		WebDriver driver = new ChromeDriver(options);
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	/*
	@Test
	public void testAmbiente() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://google.com");
	}
	*/
	
	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();
		try {
			//Clicar em ADD Todo.
			driver.findElement(By.id("addTodo")).click();		
			//Escrever a descrição.
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			//Escrever a data.
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			//Clicar em salvar.
			driver.findElement(By.id("saveButton")).click();
			//Validar mensagem de sucesso.
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Sucess!", mensagem);
		} finally {
			//Fechar o browser
			driver.quit();
		}		
	}
	
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();
		try {
			
			//Clicar em ADD Todo.
			driver.findElement(By.id("addTodo")).click();		
			
			
			
			//Escrever a data.
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			//Clicar em salvar.
			driver.findElement(By.id("saveButton")).click();
			//Validar mensagem de sucesso.
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			//Fechar o browser
			driver.quit();
		}		
	}
	
	
	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();
		try {
			//Clicar em ADD Todo.
			driver.findElement(By.id("addTodo")).click();		
			//Escrever a descrição.
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//Clicar em salvar.
			driver.findElement(By.id("saveButton")).click();
			//Validar mensagem de sucesso.
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);
		} finally {
			//Fechar o browser
			driver.quit();
		}		
	}
	
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();
		try {
			//Clicar em ADD Todo.
			driver.findElement(By.id("addTodo")).click();		
			//Escrever a descrição.
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			//Escrever a data.
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2000");
			//Clicar em salvar.
			driver.findElement(By.id("saveButton")).click();
			//Validar mensagem de sucesso.
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			//Fechar o browser
			driver.quit();
		}		
	}
	
	
	

}
