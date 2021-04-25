package com.sapient.code.unittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sapient.code.model.Country;
import com.sapient.code.model.FootballTeamStanding;
import com.sapient.code.model.Leagues;
import com.sapient.code.service.FootballLeagueService;

public class FootballLeagueServiceUnitTest {
	
	private FootballLeagueService footballLeagueService = new FootballLeagueService();
	
	@Test
	public void testCountryFilter() {
		List<Country> countries = new ArrayList<>();
		Country c1 = new Country();
		c1.setId(1);
		c1.setName("India");
		countries.add(c1);
		
		Country c2 = new Country();
		c2.setId(2);
		c2.setName("England");
		countries.add(c2);
		
		Country filterCountry = footballLeagueService.filterCountryByName("India", countries);
		assertEquals("India",filterCountry.getName());
		assertEquals(1,filterCountry.getId());
		
		filterCountry = footballLeagueService.filterCountryByName("Australia", countries);
		assertNull(filterCountry);
	}
	
	@Test
	public void testLeagueFilter() {
		List<Leagues> leagues = new ArrayList<>();
		Leagues l1 = new Leagues();
		l1.setLeagueId(1);
		l1.setLeagueName("Championship");
		
		leagues.add(l1);
		
		Leagues l2 = new Leagues();
		l2.setLeagueId(2);
		l2.setLeagueName("champ");
		
		leagues.add(l2);
		
		Leagues league = footballLeagueService.filterLeaguesByName("champ", leagues);
		assertEquals("champ",league.getLeagueName());
		assertEquals(2,league.getLeagueId());
		
		league = footballLeagueService.filterLeaguesByName("some", leagues);
		assertNull(league);
	}
	
	@Test
	public void testTeamNameFilter() {
		
		List<FootballTeamStanding> footballTeamStanding = new ArrayList<>();
		FootballTeamStanding fts1 = new FootballTeamStanding();
		fts1.setTeamId(1);
		fts1.setTeamName("India");
		
		footballTeamStanding.add(fts1);
		
		FootballTeamStanding fts2 = new FootballTeamStanding();
		fts2.setTeamId(2);
		fts2.setTeamName("Japan");
		
		footballTeamStanding.add(fts2);
		FootballTeamStanding fts = footballLeagueService.filteredTeamStanding("India", footballTeamStanding);
		assertEquals("India",fts.getTeamName());
		assertEquals(1,fts.getTeamId());
		
		fts = footballLeagueService.filteredTeamStanding("China", footballTeamStanding);
		assertNull(fts);
	}

}
