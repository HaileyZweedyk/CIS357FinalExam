package gvsu.cis.edu.cis357finalexam

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StarDao {

    @Query(
        "SELECT * FROM StarredNetwork"
    )
    fun getAll():Flow<List<StarredNetwork>>

    @Insert(
        onConflict=
            OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        item:StarredNetwork
    )

    @Delete
    suspend fun delete(
        item:StarredNetwork
    )

    @Query(
        "SELECT EXISTS(SELECT 1 FROM StarredNetwork WHERE id=:id)"
    )
    suspend fun exists(
        id:String
    ):Boolean

}