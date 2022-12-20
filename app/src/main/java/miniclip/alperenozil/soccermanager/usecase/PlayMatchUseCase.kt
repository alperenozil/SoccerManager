package miniclip.alperenozil.soccermanager.usecase

import miniclip.alperenozil.soccermanager.model.Team

class PlayMatchUseCase(
    private val getScoreUseCase: GetScoreUseCase = GetScoreUseCase()
) {
    fun execute(
        homeTeam: Team,
        awayTeam: Team,
        fixture: HashMap<String, Team>
    ): HashMap<String, Team> {

        val scoreHashMap = getScoreUseCase.execute(homeTeam = homeTeam, awayTeam = awayTeam)
        val homeTeamScore = scoreHashMap["homeTeamScore"]!!
        val awayTeamScore = scoreHashMap["awayTeamScore"]!!

        fixture[homeTeam.name]!!.goalsFor += homeTeamScore
        fixture[homeTeam.name]!!.goalsAgainst += awayTeamScore
        fixture[homeTeam.name]!!.goalDifference += homeTeamScore - awayTeamScore

        fixture[awayTeam.name]!!.goalsFor += awayTeamScore
        fixture[awayTeam.name]!!.goalsAgainst += homeTeamScore
        fixture[awayTeam.name]!!.goalDifference += awayTeamScore - homeTeamScore

        if (homeTeamScore > awayTeamScore) {
            fixture[homeTeam.name]!!.points += 3
            fixture["winner"] = homeTeam
            fixture["loser"] = awayTeam
        } else if (homeTeamScore < awayTeamScore) {
            fixture[awayTeam.name]!!.points += 3
            fixture["loser"] = homeTeam
            fixture["winner"] = awayTeam
        } else {
            fixture["winner"] = homeTeam
            fixture["loser"] = awayTeam
            fixture[homeTeam.name]!!.points += 1
            fixture[awayTeam.name]!!.points += 1
            fixture[awayTeam.name]!!.mutualResult += 1
            fixture[homeTeam.name]!!.mutualResult += 1
        }

        return fixture
    }
}