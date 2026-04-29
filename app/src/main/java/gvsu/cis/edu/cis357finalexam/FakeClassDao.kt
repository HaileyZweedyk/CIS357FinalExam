// This is troubleshooting for room issues
package gvsu.cis.edu.cis357finalexam

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeStarDao : StarDao {

    override fun getAll():
            Flow<List<StarredNetwork>> =
        flowOf(emptyList())

    override suspend fun insert(
        item: StarredNetwork
    ) {
    }

    override suspend fun delete(
        item: StarredNetwork
    ) {
    }

    override suspend fun exists(
        id: String
    ): Boolean {
        return false
    }
}