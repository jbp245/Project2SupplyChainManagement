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
	String orderId;
	
	@Given("^The user starts on the SupplierOrderViewUpdate page$")
	public void the_user_starts_on_the_SupplierOrderViewUpdate_page() throws Throwable {

		driver.get("C:\\Users\\gamer\\Revature Code\\Project2FE\\supplierOrderViewUpdate.html");
	}

	@When("^The user allows the page to fully load$")
	public void the_user_allows_the_page_to_fully_load() throws Throwable {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Then("^The user should see a list of supplier orders$")
	public void the_user_should_see_a_list_of_supplier_orders() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String rowNumber = driver.findElement(By.className("container2")).getAttribute("innerHTML");
		
		Assert.assertFalse(rowNumber.isEmpty());
		
	}
	
	@When("^The user clicks the received checkbox on the first supplier PO$")
	public void the_user_clicks_the_received_checkbox_on_the_first_supplier_PO() throws Throwable {
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		WebElement checkbox = driver.findElement(By.id("checkboxNoLabel"));		
		String checkboxValue = checkbox.getAttribute("value");
		orderId = checkboxValue.split(",")[0];
		checkbox.click();
		
		WebElement firstTableRow = driver.findElement(By.id("tbl_rows"));		
	}
	
	@Then("^The user clicks the submit button$")
	public void the_user_clicks_the_submit_button() throws Throwable {

		WebElement submtButton = driver.findElement(By.id("confirmation"));
		submtButton.click();
		Thread.sleep(10000);
	}
	
	@When("^The page is reloaded to view updates$")
	public void the_page_is_reloaded_to_view_updates() throws Throwable {
		Thread.sleep(5000);
	}
	
	@Then("^The user sees that the PO is no longer loaded$")
	public void the_user_sees_that_the_PO_is_no_longer_loaded() throws Throwable {
		
		WebElement orderStatusElement = driver.findElement(By.id(orderId + ",Order Completed"));
		String orderStatusElementId = orderStatusElement.getAttribute("id").split(",")[1];

		Assert.assertEquals("Order Completed", orderStatusElementId); 
	}
}
