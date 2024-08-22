package edu.ws2022.aXX.am

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

@Entity
data class Event(
    val image: String,
    val text: String,
    val title: String,
    var viewCount: Int,
    val image1: String,
    val image2: String,
    val image3: String,
    var status: String = "Unread",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

@Entity
data class Ticket(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val ticketType: String,
    val name: String,
    val seat: String,
    val image: String,
    val time: Long
)


@Dao
interface EventDao {
    @Query("SELECT * FROM Event")
    suspend fun selectAll(): List<Event>

    @Insert
    suspend fun insertAll(events: List<Event>)

    @Query("SELECT * FROM EVENT WHERE id = :id")
    suspend fun selectEvent(id: Int): Event

    @Update
    suspend fun updateEvent(event: Event)

}


@Dao
interface TicketDao {
@Query("SELECT * FROM ticket")
suspend fun selectAll(): List<Ticket>

@Delete
suspend fun deleteTicket(ticket: Ticket)

@Insert
suspend fun insertAll(tickets: List<Ticket> )

@Query("SELECT * FROM Ticket WHERE id = :id")
suspend fun selectTicket(id: Int): Ticket
}



@Database(entities = [Event::class, Ticket::class], version = 1)
abstract class CoreDatabase() : RoomDatabase() {
    abstract fun eventDao(): EventDao
    abstract fun ticketDao() : TicketDao
}

object AppDatabase {
    private lateinit var db: CoreDatabase
    fun getDb(context:Context): CoreDatabase {
        if(!::db.isInitialized) {
            db = Room.databaseBuilder(context, CoreDatabase::class.java, "database").build()
        }
        return db
    }
}

