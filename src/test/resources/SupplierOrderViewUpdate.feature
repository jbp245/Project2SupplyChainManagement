Feature: The SupplierOrderViewUpdate Page loads Supplier Orders

	Scenario: On page load supplier orders are displayed
		Given The user starts on the SupplierOrderViewUpdate page
		When The user allows the page to fully load
		Then The user should see a list of supplier orders
		
	Scenario: The user checks update on a supply order that is not received yet and submits
		Given The user starts on the SupplierOrderViewUpdate page
		When The user clicks the received checkbox on the first supplier PO
		Then The user clicks the submit button
		When The page is reloaded to view updates
		Then The user sees that the PO is no longer loaded
		