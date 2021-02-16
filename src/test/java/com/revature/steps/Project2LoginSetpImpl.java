package com.revature.steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.runners.Project2Runner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Project2LoginSetpImpl {

	public static WebDriver driver = Project2Runner.driver;
	
	@Given("^The user starts on the login page$")
	public void the_user_starts_on_the_login_page() {
	    driver.get("file:///C:/Users/Nick.000/Documents/projects/Revature%20Training/Project2_FrontEnd/index.html");//will have to change for each person running the tests
	}

	@When("^The user types in the incorrect username and password and clicks login$")
	public void the_user_types_in_the_incorrect_username_and_password_and_clicks_login() {
	    WebElement username = driver.findElement(By.id("username"));
	    WebElement password = driver.findElement(By.id("password"));
	    WebElement loginButton = driver.findElement(By.id("login_button"));
	    
	    username.sendKeys("asdfasdf");
	    password.sendKeys("0000000000000");
	    loginButton.click();
	}

	@Then("^The user should be on the same page and see error message$")
	public void the_user_should_be_on_the_same_page_and_see_error_message() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals("Post Warehouse Management Login", driver.getTitle());
		WebElement alert = driver.findElement(By.id("alert"));
		
		Assert.assertEquals("UserName or Password Not Exists", alert.getText());
	}
	
	@When("^The user types in the correct username and password and clicks login$")
	public void the_user_types_in_the_correct_username_and_password_and_clicks_login() {
		WebElement username = driver.findElement(By.id("username"));
	    WebElement password = driver.findElement(By.id("password"));
	    WebElement loginButton = driver.findElement(By.id("login_button"));
	    
	    username.sendKeys("test");
	    password.sendKeys("pass");
	    loginButton.click();
	}

	@Then("^The user should be on the next page$")
	public void the_user_should_be_on_the_next_page() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals("Post Warehouse Management", driver.getTitle());
	}
}
