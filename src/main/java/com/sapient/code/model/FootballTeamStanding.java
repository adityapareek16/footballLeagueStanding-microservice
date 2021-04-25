package com.sapient.code.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FootballTeamStanding {
	
	private int countryId;
	
	@JsonProperty("country_name")
	private String countryName;
	
	@JsonProperty("league_id")
	private int leagueId;
	
	@JsonProperty("league_name")
	private String leagueName;
	
	@JsonProperty("team_id")
	private int teamId;
	
	 @JsonProperty("team_name")
	private String teamName;
	 
	 @JsonProperty("overall_league_position")
	private int position;
	
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	

}
