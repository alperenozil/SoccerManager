package miniclip.alperenozil.soccermanager.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import miniclip.alperenozil.soccermanager.model.Team
import miniclip.alperenozil.soccermanager.usecase.PlayMatchUseCase

class MainViewModel(
    private val playMatchUseCase: PlayMatchUseCase = PlayMatchUseCase(),
) : ViewModel() {

    private lateinit var firstTeam : Team
    private lateinit var secondTeam : Team
    private lateinit var thirdTeam : Team
    private lateinit var fourthTeam : Team

    var fixture = HashMap<String, Team>()
    val fixtureLive = MutableLiveData<String>()

    private fun createFixture() {
        firstTeam=Team(
            name = "firstTeam",
            points = 0,
            goalDifference = 0,
            goalsFor = 0,
            goalsAgainst = 0,
            mutualResult = 0,
            power = 5
        )
        secondTeam =
            Team(
                name = "secondTeam",
                points = 0,
                goalDifference = 0,
                goalsFor = 0,
                goalsAgainst = 0,
                mutualResult = 0,
                power = 4
            )
        thirdTeam =
            Team(
                name = "thirdTeam",
                points = 0,
                goalDifference = 0,
                goalsFor = 0,
                goalsAgainst = 0,
                mutualResult = 0,
                power = 3
            )
        fourthTeam =
            Team(
                name = "fourthTeam",
                points = 0,
                goalDifference = 0,
                goalsFor = 0,
                goalsAgainst = 0,
                mutualResult = 0,
                power = 2
            )

        fixture = (
                hashMapOf(
                    "firstTeam" to firstTeam,
                    "secondTeam" to secondTeam,
                    "thirdTeam" to thirdTeam,
                    "fourthTeam" to fourthTeam,
                )
                )
    }

    lateinit var winnerFromFirstRoundFirstMatch: Team
    lateinit var loserFromFirstRoundFirstMatch: Team
    lateinit var winnerFromFirstRoundSecondMatch: Team
    lateinit var loserFromFirstRoundSecondMatch: Team

    lateinit var winnerFromSecondRoundFirstMatch: Team
    lateinit var loserFromSecondRoundFirstMatch: Team
    lateinit var winnerFromSecondRoundSecondMatch: Team
    lateinit var loserFromSecondRoundSecondMatch: Team

    private fun playFirstRound() {
        fixture = (playMatchUseCase.execute(firstTeam, secondTeam, fixture)!!)
        winnerFromFirstRoundFirstMatch = fixture["winner"]!!
        loserFromFirstRoundFirstMatch = fixture["loser"]!!
        fixture = (playMatchUseCase.execute(thirdTeam, fourthTeam, fixture)!!)
        winnerFromFirstRoundSecondMatch = fixture["winner"]!!
        loserFromFirstRoundSecondMatch = fixture["loser"]!!
    }

    private fun playSecondRound() {
        fixture = (playMatchUseCase.execute(
            winnerFromFirstRoundFirstMatch,
            winnerFromFirstRoundSecondMatch,
            fixture
        )!!)
        winnerFromSecondRoundFirstMatch = fixture["winner"]!!
        loserFromSecondRoundFirstMatch = fixture["loser"]!!
        fixture = (playMatchUseCase.execute(
            loserFromFirstRoundFirstMatch,
            loserFromFirstRoundSecondMatch,
            fixture
        )!!)
        winnerFromSecondRoundSecondMatch = fixture["winner"]!!
        loserFromSecondRoundSecondMatch = fixture["loser"]!!
    }

    private fun playThirdRound() {
        fixture = (playMatchUseCase.execute(
            winnerFromSecondRoundFirstMatch,
            winnerFromSecondRoundSecondMatch,
            fixture
        )!!)
        fixture = (playMatchUseCase.execute(
            loserFromSecondRoundFirstMatch,
            loserFromSecondRoundSecondMatch,
            fixture
        )!!)

    }

    private fun updateFixture() {
        var fixtureString=""
        val list = ArrayList<Team>()
        val hashSet=HashSet<String>()
        fixture.keys.forEach {
            if(!hashSet.contains(fixture[it]!!.name)){
                list.add(fixture[it]!!)
                hashSet.add(fixture[it]!!.name)
            }
        }
        var sortedList = list.sortedWith(compareBy({ it.points }))
        var index=1
        sortedList.reversed().forEach{
            fixtureString+=index.toString()+") "+it.toString()+"\n"
            index++
        }
        fixtureLive.postValue(fixtureString)
    }

    private fun startTournament(){
        createFixture()
        playFirstRound()
        playSecondRound()
        playThirdRound()
        updateFixture()
    }

    fun handleClick(){
        startTournament()
    }

}