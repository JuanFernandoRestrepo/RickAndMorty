
data class Characters(
    val info: Info?,
    val results: List<RmCharacter>
)
data class RmCharacter(
    val id: Int?,
    val name: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val image: String?
)
data class Info(
    val count: Int?,
    val pages: Int?,
    val next: String?,
    val prev: String?
)


