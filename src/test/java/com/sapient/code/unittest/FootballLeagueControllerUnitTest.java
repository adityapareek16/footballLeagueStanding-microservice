package com.sapient.code.unittest;

import org.junit.Assert;
import org.junit.Test;

import com.sapient.code.controller.FootballLeagueController;
import com.sapient.code.exception.RecordNotFoundException;

public class FootballLeagueControllerUnitTest {

	@Test
	public void testAllEmptyInputsStanding() {
		
		try {
			FootballLeagueController.validateInputParams("", "", "");
			Assert.fail("Fail! Method was expected to throw an exception because empty/null values are not supported.");
		} catch(RecordNotFoundException e) {
			
		}
		
	}
	@Test
	public void testEmptyCountryInputsStanding() {
		
		try {
			FootballLeagueController.validateInputParams("", "league", "team");
			Assert.fail("Fail! Method was expected to throw an exception because empty/null values are not supported.");
		} catch(RecordNotFoundException e) {
			
		}
		
	}
	@Test
	public void testEmptyLeagueInputsStanding() {
		
		try {
			FootballLeagueController.validateInputParams("India", "", "team");
			Assert.fail("Fail! Method was expected to throw an exception because empty/null values are not supported.");
		} catch(RecordNotFoundException e) {
			
		}
		
	}
	@Test
	public void testEmptyTeamInputsStanding() {
		
		try {
			FootballLeagueController.validateInputParams("India", "league", "");
			Assert.fail("Fail! Method was expected to throw an exception because empty/null values are not supported.");
		} catch(RecordNotFoundException e) {
			
		}
		
	}
	
	@Test
	public void testAllValidInputsStanding() {
		
		try {
			FootballLeagueController.validateInputParams("India", "league", "team");
		} catch(RecordNotFoundException e) {
			
		}
		
	}
}
