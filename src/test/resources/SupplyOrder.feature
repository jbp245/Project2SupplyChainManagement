Feature: The supply order form can create orders as expected

	Scenario: The user enters a negative number for units to order showing error message
		Given The user is on the supply order page
		When The user enters a negative number of units to order
		Then The user will see an error message
		
	Scenario: The user enter a positive nubmer and the order completes
		Given The user is on the supply order page
		When The user selects product and enters a positve number
		Then The user will see alert that product was ordered