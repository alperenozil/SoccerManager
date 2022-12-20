package miniclip.alperenozil.soccermanager.usecase

import miniclip.alperenozil.soccermanager.model.Team

class GetScoreUseCase {
    fun execute(homeTeam: Team, awayTeam: Team): HashMap<String, Int> {

        var homeTeamScore = 0
        var awayTeamScore = 0

        if (homeTeam.power > awayTeam.power) {
            homeTeamScore = (3..6).random()
            awayTeamScore = (0..4).random()
        } else if (homeTeam.power < awayTeam.power) {
            awayTeamScore = (3..6).random()
            homeTeamScore = (0..4).random()
        }

        val result = hashMapOf(
            "homeTeamScore" to homeTeamScore,
            "awayTeamScore" to awayTeamScore
        )

        return result
    }
}
