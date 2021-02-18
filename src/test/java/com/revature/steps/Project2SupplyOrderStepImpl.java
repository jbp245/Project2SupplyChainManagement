package com.revature.steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.runners.Project2Runner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class Project2SupplyOrderStepImpl {

	public static WebDriver driver = Project2Runner.driver;
	
	@Given("^The user is on the supply order page$")
	public void the_user_is_on_the_supply_order_page() {
	    driver.get("file:///C:/Users/Nick.000/Documents/projects/Revature%20Training/Project2_FrontEnd/supplierOrderCreate.html");
	}

	@When("^The user enters a negative number of units to order$")
	public void the_user_enters_a_negative_number_of_units_to_order() {
	    WebElement units = driver.findElement(By.id("quantity"));
	    WebElement submit = driver.findElement(By.id("submitProductOrderButton"));
	    
	    units.clear();
	    units.sendKeys("-10");
	    submit.click();
	}

	@Then("^The user will see an error message$")
	public void the_user_will_see_an_error_message() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement error = driver.findElement(By.id("error"));
		Assert.assertEquals("You must order a quantity of 1 or more", error.getText());
	}

	@When("^The user selects product and enters a positve number$")
	public void the_user_selects_product_and_enters_a_positve_number() {
		WebElement units = driver.findElement(By.id("quantity"));
	    WebElement submit = driver.findElement(By.id("submitProductOrderButton"));
	    
	    units.clear();
	    units.sendKeys("10");
	    
	    try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	    submit.click();
	}

	@Then("^The user will see alert that product was ordered$")
	public void the_user_will_see_alert_that_product_was_ordered() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals("Order Submitted", driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
	}
}
