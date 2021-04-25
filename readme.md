# Football standing service

Develop, Test and Deploy a microservice to find standings of a team playing league football match using country name, league name and team name. The service should be accessible via web browser on internet and end user should be able to view results by changing previously listed parameters. Output of this service should be presented in web browser using any one of Javascript framework, HTML or JSON. And the service should be ready to be released to production or live environment. In output, display following: 

{
countryId: 41,
country_name: "England",
league_id: 149,
league_name: "Championship",
team_id: 2641,
team_name: "Norwich",
overall_league_position: 1
}

# Input
countryName,leagueName and teamName

# url 
http://localhost:8889/api/v1/football/standing?countryName=England&leagueName=Championship&teamName=Leeds

