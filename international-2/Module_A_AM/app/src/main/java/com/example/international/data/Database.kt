package com.example.international.data

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
data class Ticket(
    val ticketType: String,
    val audienceName: String,
    val time: Long,
    val seat: String,
    val imagePath: String,
    @PrimaryKey(autoGenerate = true) var id: Int  = 0
)


@Entity
data class Event(
    var readStatus: String, val title: String, val  text: String, var viewCount: Int,
    val image1: String, val image2: String, val image3: String, @PrimaryKey(autoGenerate = true) var id: Int= 0
)


@Dao
interface TicketDao {
    @Insert
    suspend fun insertAll(tickets: List<Ticket>)

    @Query("SELECT * FROM  Ticket   WHERE ticketType= 'Closing Ceremony'")
    suspend fun findClosingCeremony(): List<Ticket>

    @Query("SELECT * FROM Ticket WHERE ticketType = 'Opening Ceremony'")
    suspend fun findOpeningCeremony(): List<Ticket>

    @Query("SELECT * FROM Ticket WHERE :id = id")
    suspend fun findTicket(id: Int): Ticket

}


@Dao
interface EventDao {
    @Insert
    suspend fun insertALl(events: List<Event>)

    @Query("SELECT * FROM Event")
    suspend fun selectAll(): List<Event>

    @Update
    suspend fun update(event: Event)

    @Query("SELECT EXISTS(SELECT * FROM Event)")
    suspend fun exists(): Boolean

    @Query("SELECT * FROM  Event WHERE :id = id")
    suspend fun findEvent(id: Int): Event

}


@Database(entities = [Event::class, Ticket::class], version = 1)
abstract class Database(): RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun ticketDao(): TicketDao
}


object DB {

    var db: com.example.international.data.Database? = null
    fun getDatabase(context: Context): com.example.international.data.Database? {
        db = (db ?: Room.databaseBuilder(context, com.example.international.data.Database::class.java, name = "db").build())
        return db
    }
}