package com.example.international_a_am.data

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
    var ticketType: String,
    var audienceName: String,
    var time: Long,
    var seat: String,
    var image: String,
    @PrimaryKey(autoGenerate = true) var id:Long = 0
)


@Database(version = 1, entities = [Event::class, Ticket::class])
abstract class Db: RoomDatabase() {
   abstract fun eventDao(): EventDao
   abstract fun ticketDao(): TicketDao
}


@Dao
interface TicketDao {
    @Insert
    suspend fun insertAll(tickets: List<Ticket>)

    @Query("SELECT * FROM ticket WHERE ticketType = :ticketType")
    suspend fun selectAll(ticketType: String): List<Ticket>

    @Update
    suspend fun update(ticket: Ticket)

    @Query("SELECT * FROM ticket WHERE id = :id")
    suspend fun select(id: Int): Ticket

}
@Dao
interface EventDao {
    @Insert
    suspend fun insertAll(events: List<Event>)

    @Query("SELECT * FROM event")
    suspend fun selectAll(): List<Event>

    @Update
    suspend fun update(event: Event)

    @Query("SELECT EXISTS (SELECT * FROM event WHERE id = :eventId)")
    suspend fun select(eventId: Int): Boolean
}

object Database {
    var db: Db? = null
    fun getDb(context: Context): Db? {

        db = db ?: Room.databaseBuilder(context, Db::class.java, name = "db" ).build()
        return db
    }
}