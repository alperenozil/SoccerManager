package miniclip.alperenozil.soccermanager.model

data class Team(
    val name: String = "",
    var points: Int = 0,
    var goalDifference: Int = 0,
    var goalsFor: Int = 0,
    var goalsAgainst: Int = 0,
    var mutualResult: Int = 0,
    val power: Int = 0
)