package com.sapient.code.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sapient.code.exception.RecordNotFoundException;
import com.sapient.code.model.Country;
import com.sapient.code.model.FootballTeamStanding;
import com.sapient.code.model.Leagues;
import com.sapient.code.restclient.FootballRestClient;


@Service
public class FootballLeagueService {

	@Autowired
	private FootballRestClient footballRestClient;

	private static final Logger logger = LoggerFactory.getLogger(FootballLeagueService.class);

	public FootballTeamStanding getFootballLeagueStanding(String countryName, String leagueName, String teamName) {
		List<Country> countries = getAllCountries();
		Country country = filterCountryByName(countryName, countries);
		if (country == null) {
			logger.error("Couldn't found country by name::" + countryName);
			throw new RecordNotFoundException("Couldn't found country by name::" + countryName);
		}
		logger.info("countryId and countryName are::"+country.getId()+","+countryName);
		List<Leagues> leaguesList = getLeagues(country.getId());
		Leagues leagues = filterLeaguesByName(leagueName, leaguesList);
		if (leagues == null) {
			logger.error("Couldn't found league by name::" + leagueName);
			throw new RecordNotFoundException(
					"Couldn't found league by name::" + leagueName);
		}
		logger.info("leagueId and leagueName are::"+leagues.getLeagueId()+","+leagues.getLeagueName());		 
		List<FootballTeamStanding> footballTeamStanding = getTeamStanding(leagues.getLeagueId());
		FootballTeamStanding fts = filteredTeamStanding(teamName,footballTeamStanding);
		if (fts == null) {
			logger.error("Couldn't found team standing by teamName::" + teamName);
			throw new RecordNotFoundException("Couldn't found team standing by teamName::" + teamName);
		}
		logger.info("overall team standing::"+fts.getPosition());
		fts.setCountryId(country.getId());
		return fts;

	}

	public Country filterCountryByName(String countryName, List<Country> countries) {
		return countries.stream().filter(c -> countryName.equalsIgnoreCase(c.getName()))
				.findFirst().orElse(null);
	}

	public Leagues filterLeaguesByName(String leagueName, List<Leagues> leaguesList) {
		return leaguesList.stream().filter(l -> leagueName.equalsIgnoreCase(l.getLeagueName()))
				.findFirst().orElse(null);
	}

	public FootballTeamStanding filteredTeamStanding(String teamName, List<FootballTeamStanding> footballTeamStanding) {
		return footballTeamStanding.stream().filter(t -> teamName.equalsIgnoreCase(t.getTeamName()))
				.findFirst().orElse(null);
	}


	private List<Country> getAllCountries() {
		return new ArrayList<>(Arrays.asList(footballRestClient.getCountries()));
	}


	private List<Leagues> getLeagues(int countryId) {
		return new ArrayList<>(Arrays.asList(footballRestClient.getLeagues(countryId)));
	}


	private List<FootballTeamStanding> getTeamStanding(int leagueId) {
		return new ArrayList<>(Arrays.asList(footballRestClient.getTeamStanding(leagueId)));
	}

}
