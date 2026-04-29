package gvsu.cis.edu.cis357finalexam

import kotlinx.coroutines.flow.Flow

class CityBikeRepository(
    private val api: ApiService,
    private val dao: StarDao
) {

    suspend fun getNetworks(): List<Network> =
        api.getNetworks()

    fun starredItems(): Flow<List<StarredNetwork>> =
        dao.getAll()

    suspend fun toggleStar(network: Network) {
        val networkId = network.id ?: return
        
        if (dao.exists(networkId)) {
            dao.delete(
                StarredNetwork(
                    id = networkId,
                    name = network.name ?: "",
                    city = network.location?.city ?: "",
                    country = network.location?.country ?: ""
                )
            )
        } else {
            dao.insert(
                StarredNetwork(
                    id = networkId,
                    name = network.name ?: "",
                    city = network.location?.city ?: "",
                    country = network.location?.country ?: ""
                )
            )
        }
    }
}