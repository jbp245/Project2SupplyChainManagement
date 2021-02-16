package com.revature.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.runners.Project2Runner;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class SupplierOrderViewUpdate {
	
	public static WebDriver driver = Project2Runner.driver;
	
	@Given("^The user starts on the SupplierOrderViewUpdate page$")
	public void the_user_starts_on_the_SupplierOrderViewUpdate_page() throws Throwable {

		driver.get("C:\\Users\\gamer\\Revature Code\\Project2FE\\supplierOrderViewUpdate.html");
		
//	    throw new PendingException();
	}

	@When("^The user allows the page to fully load$")
	public void the_user_allows_the_page_to_fully_load() throws Throwable {

		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//	    throw new PendingException();
	}

	@Then("^The user should see a list of supplier orders$")
	public void the_user_should_see_a_list_of_supplier_orders() {

		String rowNumber = driver.findElement(By.className("container2")).getCssValue("value");
		
		Assert.assertFalse(rowNumber.isEmpty());
		
		
		//	    throw new PendingException();
	}

}
