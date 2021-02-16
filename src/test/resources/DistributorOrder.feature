Feature: The Distributor order page allows you to make orders for different distributors

	Scenario: The user inputs a number less than 0 to order
		Given The user is on the product order form page
		When The user enters a number less than 0 to order
		Then The form will tell the user to input a number greater than 0
		
	Scenario: The user inpus a number greater than 0 to order
		Given The user is on the product order form page
		When the user enter a number greater than 0 to order
		Then the form will generate an allert saying the order was placed