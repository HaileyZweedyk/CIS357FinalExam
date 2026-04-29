package gvsu.cis.edu.cis357finalexam

import io.ktor.client.call.body
import io.ktor.client.request.get
import android.util.Log

class ApiService {

    private val client = KtorProvider.client

    suspend fun getNetworks(): List<Network> {
        return try {
            val response = client.get("https://api.citybik.es/v2/networks")
            val wrapper: NetworksWrapper? = response.body()
            
            // Filter out any invalid networks immediately
            wrapper?.networks?.filter { it.id != null } ?: emptyList()
        } catch (e: Exception) {
            Log.e("ApiService", "Error fetching networks", e)
            sampleNetworks()
        }
    }

    private fun sampleNetworks() =
        listOf(
            Network(
                "divvy",
                "Divvy",
                Location("Chicago", "US")
            ),
            Network(
                "velib",
                "Velib",
                Location("Paris", "FRA")
            ),
            Network(
                "citi-bike",
                "Citi Bike",
                Location("New York", "US")
            ),
            Network(
                "bicing",
                "Bicing",
                Location("Barcelona", "ESP")
            )
        )
}

data class NetworksWrapper(
    val networks: List<Network>? = null
)