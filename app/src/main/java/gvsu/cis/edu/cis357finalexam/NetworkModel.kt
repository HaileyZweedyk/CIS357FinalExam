package gvsu.cis.edu.cis357finalexam

data class Network(
    val id: String? = null,
    val name: String? = null,
    val location: Location? = null
)

data class Location(
    val city: String? = null,
    val country: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)
