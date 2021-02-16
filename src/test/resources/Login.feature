Feature: The Login Page works as desired

	Scenario: Login attempt with incorrect username and password
		Given The user starts on the login page
		When The user types in the incorrect username and password and clicks login
		Then The user should be on the same page and see error message
		
	Scenario: Login Attempt with correct username and password
		Given The user starts on the login page
		When The user types in the correct username and password and clicks login
		Then The user should be on the next page
		