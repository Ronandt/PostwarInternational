package com.example.international5

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(version = 1, entities = [Comment::class])
abstract class Databases(): RoomDatabase() {
    abstract fun commentDao(): CommentDao

}

@Dao
interface CommentDao {
    @Insert
    suspend fun insert(comment: com.example.international5.Comment)

    @Query("SELECT * FROM comment")
    suspend fun selectAll(): List<Comment>

}


@Entity
data class Comment(
    val user: String,
    val description: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)


object db {
    var db: Databases? = null
    fun getDb(context: Context): Databases{
        db = db ?:  Room.databaseBuilder(context, Databases::class.java, name = "f").build()
        return db as Databases
    }
}