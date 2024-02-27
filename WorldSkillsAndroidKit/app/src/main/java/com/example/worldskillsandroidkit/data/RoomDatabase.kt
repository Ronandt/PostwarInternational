package com.example.worldskillsandroidkit.data


import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update


@Entity
data class SampleDataDb(
    var ticketType: String,
    var audienceName: String,
    var time: Long,
    var seat: String,
    var image: String,
    @PrimaryKey(autoGenerate = true) var id:Long = 0
)


@Database(version = 1, entities = [SampleDataDb::class])
abstract class Db: RoomDatabase() {

    abstract fun sampleDataDao():SampleDataDao
}


@Dao
interface SampleDataDao {
    @Insert
    suspend fun insertAll(tickets: List<SampleDataDb>)

    @Query("SELECT * FROM sampledatadb WHERE ticketType = :ticketType")
    suspend fun selectAll(ticketType: String): List<SampleDataDb>

    @Update
    suspend fun update(ticket: SampleDataDb)

    @Query("SELECT * FROM sampledatadb WHERE id = :id")
    suspend fun select(id: Int): SampleDataDb

}

object Database {
    var db: Db? = null
    fun getDb(context: Context): Db? {

        db = db ?: Room.databaseBuilder(context, Db::class.java, name = "db" ).build()
        return db
    }
}