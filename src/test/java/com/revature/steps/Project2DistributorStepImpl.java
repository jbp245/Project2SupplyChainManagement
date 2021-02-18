package com.revature.steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.revature.runners.Project2Runner;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Project2DistributorStepImpl {

	public static WebDriver driver = Project2Runner.driver;
	
	public String element_status_id = "";
	
	@Given("^The user logs in as a distributor$")
	public void the_user_logs_in_as_a_distributor() {
	    driver.get("C:\\Users\\Nick.000\\Documents\\projects\\Revature Training\\Project2_FrontEnd\\index.html");
	    
	    WebElement username = driver.findElement(By.id("username"));
	    WebElement password = driver.findElement(By.id("password"));
	    WebElement loginButton = driver.findElement(By.id("login_button"));
	    
	    username.sendKeys("disuser");
	    password.sendKeys("dispass");
	    loginButton.click();
	}

	@When("^The user clicks the distributor information button$")
	public void the_user_clicks_the_distributor_information_button() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    WebElement details = driver.findElement(By.id("orderdetails"));
	    
	    details.click();
	}

	@Then("^The user will be able to see a table with orders in it$")
	public void the_user_will_be_able_to_see_a_table_with_orders_in_it() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    WebElement welcome = driver.findElement(By.xpath("/html/body/div[2]/iframe"));
	    
	    
	    Assert.assertEquals("file:///c:/Users/Nick.000/Documents/projects/Revature%20Training/Project2_FrontEnd/distributor.html?id=1", welcome.getAttribute("src"));
	}
	
	@Given("^The user is logged in as target$")
	public void the_user_is_logged_in_as_target() {
	    driver.get("file:///c:/Users/Nick.000/Documents/projects/Revature%20Training/Project2_FrontEnd/distributor.html?id=2");
	}

	@When("^The user cliks the first available checkbox and clicks button$")
	public void the_user_cliks_the_first_available_checkbox() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    WebElement checkbox = driver.findElement(By.cssSelector("html body div.container table#tbl_body.table.table-bordered tbody tr td div.container2 input#checkboxNoLabel.form-check-input"));
	    WebElement button = driver.findElement(By.id("confirmation"));
	    
	    String value = checkbox.getAttribute("value");
	    
	    String[] split = value.split(",");
	    element_status_id = split[1];
	    
	    checkbox.click();
	    button.click();
	}

	@Then("^The user will see that field updated to Order Completed$")
	public void the_user_will_see_that_field_updated_to_Order_Completed() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement status = driver.findElement(By.id(element_status_id));
		
		Assert.assertEquals("Order Completed", status.getText());
	}

}
