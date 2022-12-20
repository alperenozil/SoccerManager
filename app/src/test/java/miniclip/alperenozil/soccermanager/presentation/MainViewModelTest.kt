package miniclip.alperenozil.soccermanager.presentation

import miniclip.alperenozil.soccermanager.model.Team
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel
    val firstTeam = Team(
        name = "firstTeam",
        points = 0,
        goalDifference = 0,
        goalsFor = 0,
        goalsAgainst = 0,
        mutualResult = 0,
        power = 5
    )
    val secondTeam = Team(
        name = "secondTeam",
        points = 0,
        goalDifference = 0,
        goalsFor = 0,
        goalsAgainst = 0,
        mutualResult = 0,
        power = 4
    )
    val thirdTeam = Team(
        name = "thirdTeam",
        points = 0,
        goalDifference = 0,
        goalsFor = 0,
        goalsAgainst = 0,
        mutualResult = 0,
        power = 3
    )
    val fourthTeam = Team(
        name = "fourthTeam",
        points = 0,
        goalDifference = 0,
        goalsFor = 0,
        goalsAgainst = 0,
        mutualResult = 0,
        power = 2
    )

    @Before
    fun setup() {
        mainViewModel = MainViewModel()

    }

    @Test
    fun `Should map team names to teams in fixture`() {
        val fixture = hashMapOf(
            "firstTeam" to firstTeam,
            "secondTeam" to secondTeam,
            "thirdTeam" to thirdTeam,
            "fourthTeam" to fourthTeam
        )
        assertEquals(fixture["firstTeam"], firstTeam)
        assertEquals(fixture["secondTeam"], secondTeam)
        assertEquals(fixture["thirdTeam"], thirdTeam)
        assertEquals(fixture["fourthTeam"], fourthTeam)
    }

}