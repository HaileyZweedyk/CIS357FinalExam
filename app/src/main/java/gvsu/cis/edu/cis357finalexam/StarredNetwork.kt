package gvsu.cis.edu.cis357finalexam

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StarredNetwork(
    @PrimaryKey
    val id:String,
    val name:String,
    val city:String,
    val country:String
)