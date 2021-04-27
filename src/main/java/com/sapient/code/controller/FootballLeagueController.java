package com.sapient.code.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sapient.code.exception.RecordNotFoundException;
import com.sapient.code.model.FootballTeamStanding;
import com.sapient.code.service.FootballLeagueService;

@RestController
@RequestMapping("/api/v1/football/")
public class FootballLeagueController {
	
	@Autowired
	FootballLeagueService footballLeagueService;
	
	private static final Logger logger = LoggerFactory.getLogger(FootballLeagueController.class);
	
	@RequestMapping("standing")
	public FootballTeamStanding getStanding(@RequestParam("countryName") String countryName, 
			@RequestParam("leagueName") String leagueName, @RequestParam("teamName") String teamName) {
		logger.info("getting standing for input countryName:"+countryName+",leagueName:"+leagueName+" and teamName:"+teamName);
		validateInputParams(countryName, leagueName, teamName);
		FootballTeamStanding footballTeamStanding = null;
		try {
			footballTeamStanding = footballLeagueService.getFootballLeagueStanding(countryName, leagueName, teamName);
			return footballTeamStanding;
		} catch(Exception e) {
			throw new RecordNotFoundException(e.getLocalizedMessage());
		}
	}
	
	public static void validateInputParams(String countryName, String leagueName, String teamName) {
		if(countryName == null || countryName.isEmpty()) {
			logger.error("Input parameter 'countryName' was not correctly provided in the input. Either paramter is null or empty");
			throw new RecordNotFoundException("Input parameter 'countryName' was not correctly provided in the input. Either paramter is null or empty");
		}
		if(leagueName == null || leagueName.isEmpty()) {
			logger.error("Input parameter 'leagueName' was not correctly provided in the input. Either paramter is null or empty");
			throw new RecordNotFoundException("Input parameter 'leagueName' was not correctly provided in the input. Either paramter is null or empty");
		}
		if(teamName == null || teamName.isEmpty()) {
			logger.error("Input parameter 'teamName' was not correctly provided in the input. Either paramter is null or empty");
			throw new RecordNotFoundException("Input parameter 'teamName' was not correctly provided in the input. Either paramter is null or empty");
		}
	}

}
