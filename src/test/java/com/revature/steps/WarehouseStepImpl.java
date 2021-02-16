package com.revature.steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.runners.Project2Runner;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WarehouseStepImpl {
	
	public static WebDriver driver = Project2Runner.driver;
	
	@Given("^The user starts on the warehouse page$")
	public void the_user_starts_on_the_warehouse_page() throws Throwable {
		driver.get("C:\\Users\\gamer\\Revature Code\\Project2FE\\warehouse.html");
	}

	@When("^Do not implement when$")
	public void do_not_implement_when() throws Throwable {
	}

	@Then("^The user should be able to see product info$")
	public void the_user_should_be_able_to_see_product_info() throws Throwable {
		
		Thread.sleep(5000);
		WebElement tableRow = driver.findElement(By.id("1"));
		System.out.println(tableRow);
		String tableRowId = tableRow.getAttribute("id");
		
		Assert.assertEquals(tableRowId, "1");
	}

}
