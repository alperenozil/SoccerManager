package miniclip.alperenozil.soccermanager.usecase

import miniclip.alperenozil.soccermanager.model.Team
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class GetScoreUseCaseTest {
    private lateinit var getScoreUseCase: GetScoreUseCase

    @Before
    fun setUp() {
        getScoreUseCase = GetScoreUseCase()
    }

    @Test
    fun `Should return higher score for the stronger team`() {
        val score = getScoreUseCase.execute(
            Team(
                name = "strongTeam",
                points = 0,
                goalDifference = 0,
                goalsFor = 0,
                goalsAgainst = 0,
                mutualResult = 0,
                power = 5
            ), Team(
                name = "weakTeam",
                points = 0,
                goalDifference = 0,
                goalsFor = 0,
                goalsAgainst = 0,
                mutualResult = 0,
                power = 1
            )
        )
        assert(score["homeTeamScore"]!! > score["awayTeamScore"]!!)
    }
}