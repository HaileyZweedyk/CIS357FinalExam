package gvsu.cis.edu.cis357finalexam

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [StarredNetwork::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun starDao(): StarDao
}