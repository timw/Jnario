package org.example

Feature: A Feature

	As a dude
	I want more dudeness
	In order to remain dudeful
	
	Scenario: Increasing dudeness
		int dudes
		
		Given a dude
			dudes = 1	
		When I add a dude
			dudes += 1
		Then there should be 2 dudes
			dudes => 2
			
	Scenario:  WTF dude?