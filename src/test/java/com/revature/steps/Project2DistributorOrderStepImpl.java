package com.revature.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.runners.Project2Runner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Project2DistributorOrderStepImpl {

	public static WebDriver driver = Project2Runner.driver;
	
	@Given("^The user is on the product order form page$")
	public void the_user_is_on_the_product_order_form_page() {
	    driver.get("file:///C:/Users/Nick.000/Documents/projects/Revature%20Training/Project2_FrontEnd/distributor_order.html");
	}

	@When("^The user enters a number less than (\\d+) to order$")
	public void the_user_enters_a_number_less_than_to_order(int arg1) {
	    WebElement quantity = driver.findElement(By.id("quantity"));
	    WebElement button = driver.findElement(By.id("getOneProductButton"));
	    quantity.sendKeys("-1");
	    button.click();
	}

	@Then("^The form will tell the user to input a number greater than (\\d+)$")
	public void the_form_will_tell_the_user_to_input_a_number_greater_than(int arg1) {
	    WebElement error = driver.findElement(By.id("error"));
	    
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	    Assert.assertEquals("You must order a quantity of 1 or more", error.getText());
	}
	
	@When("^the user enter a number greater than (\\d+) to order$")
	public void the_user_enter_a_number_greater_than_to_order(int arg1) {
		Select distributor = new Select(driver.findElement(By.id("distributor")));
		WebElement quantity = driver.findElement(By.id("quantity"));
	    WebElement button = driver.findElement(By.id("getOneProductButton"));
	    
	    distributor.selectByIndex(1);
	    quantity.sendKeys("10");
	    button.click();
	}

	@Then("^the form will generate an allert saying the order was placed$")
	public void the_form_will_generate_an_allert_saying_the_order_was_placed() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals("Order submitted", driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
	}
}
