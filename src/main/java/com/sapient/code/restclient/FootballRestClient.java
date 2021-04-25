package com.sapient.code.restclient;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sapient.code.model.Country;
import com.sapient.code.model.FootballTeamStanding;
import com.sapient.code.model.Leagues;

@Service
public class FootballRestClient {

	private static final String APIKEY = "APIkey";

	@Autowired
	private RestTemplate restTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(FootballRestClient.class);
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

	@Value("${football.league.base.url}")
	private String baseUrl;

	@Value("${football.league.action.standings}")
	private String standingsAction;

	@Value("${football.league.action.countries}")
	private String countriesAction;

	@Value("${football.league.action.leagues}")
	private String leaguesAction;

	@Value("${football.league.token}")
	private String token;
	
	public Country[] getCountries() {
		Map<String, String> queryParams = new HashMap<>();
		queryParams.put("action", countriesAction);
		UriComponentsBuilder builder = createUriComponentsBuilder(baseUrl, queryParams);
		return this.restTemplate.exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()),Country[].class).getBody();
	}
	
	public Leagues[] getLeagues(int countryId) {
	    Map<String, String> queryParams = new HashMap<>();
	    queryParams.put("action", leaguesAction);
	    queryParams.put("country_id", String.valueOf(countryId));
	    UriComponentsBuilder builder = createUriComponentsBuilder(baseUrl, queryParams);
	    return this.restTemplate
	        .exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()),
	            Leagues[].class).getBody();
	  }
	
	public FootballTeamStanding[] getTeamStanding(int leagueId) {
	    Map<String, String> queryParams = new HashMap<>();
	    queryParams.put("action", standingsAction);
	    queryParams.put("league_id", String.valueOf(leagueId));
	    UriComponentsBuilder builder = createUriComponentsBuilder(baseUrl, queryParams);
	    return this.restTemplate
	        .exchange(builder.toUriString(), HttpMethod.GET, new HttpEntity<>(getHeaders()),
	        		FootballTeamStanding[].class).getBody();
	  }
	
	private UriComponentsBuilder createUriComponentsBuilder(String url, Map<String, String> queryParams) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam(APIKEY, token);
		for(String key : queryParams.keySet()) {
			builder.queryParam(key, queryParams.get(key));
		}
		logger.info("Created url using uriComponenetBuilder::"+builder.toUriString());
		return builder;
	}
	
	private HttpHeaders getHeaders() {
	    HttpHeaders headers = new HttpHeaders();
	    headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
	    return headers;
	  }

}
