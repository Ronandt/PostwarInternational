package com.example.pocketvault

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import com.google.gson.Gson

class LoginManager(private val context: Context) {
    val preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)
    fun createSession(id: Int) {
        with(preferences.edit()) {
            this.putInt("id", id)
            this.apply()
        }
    }

    fun logout() {
        with(preferences.edit()) {
            clear()
            apply()
        }
    }

    fun getSession(): Int {
        return preferences.getInt("id", -1)
    }

}

class JsonExtractor(private val context: Context) {
    val gson = Gson()
    fun extractResource(): List<NoteJson> {

        val rawData = context.resources.openRawResource(R.raw.data).bufferedReader()
        return gson.fromJson(rawData,Array<NoteJson>::class.java).toList()
    }
}

@Entity
data class NoteJson(

    var userKey: Int? = 0,
    var title: String,
    var username: String,
    var password: String,
    var website: String,

)


@Database(version = 1, entities = [User::class, Note::class])
abstract class Database(): RoomDatabase() {
   abstract fun userDao() : UserDao
   abstract fun noteDao() : NoteDao

}

@Entity
data class User(

    var password: String,
    var username: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int =0,
)

@Dao
interface UserDao{
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM User WHERE username = :username")
    suspend fun getUser(username: String): List<User>


}

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Insert
    suspend fun insertAll(notes: List<Note>)

    @Query("SELECT * FROM note")
    suspend fun getAll(): List<Note>


    @Query("SELECT * FROM note WHERE userKey = :id")
    suspend fun getById(id: Int): Note


    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getByRealId(id: Int): Note
    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

}

@Entity
data class Note(

    var userKey: Int,
    var title: String,
    var username: String,
    var password: String,
    var website: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 ,
)

object AppDatabase {
   private lateinit var db: com.example.pocketvault.Database
   fun getDb(context: Context): com.example.pocketvault.Database {
       if(!::db.isInitialized) {
           db = Room.databaseBuilder(context,com.example.pocketvault.Database::class.java, "database_name").build()

       }
       return db
   }
}



