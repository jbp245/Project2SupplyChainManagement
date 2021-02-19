Feature: The distributor page shows all orders and allows user to update order status

	Scenario: Distributor logs in and can view all orders made and their status
		Given The user logs in as a distributor
		When The user clicks the distributor information button
		Then The user will be able to see a table with orders in it
		
	Scenario: The user can select checkbox and comfirm as received
		Given The user is logged in as target
		When The user cliks the first available checkbox and clicks button
		Then The user will see that field updated to Order Completed