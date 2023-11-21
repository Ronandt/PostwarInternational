package com.example.myapplication.data

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Identity
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Database(version = 1, entities = [EventRead::class, EventViews::class, Ticket::class], )
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun eventStatusDao(): EventStatusDao
    abstract fun eventViewsDao(): EventViewsDao

    abstract fun ticketDao(): TicketDao
}


@Entity
data class EventRead(
    @PrimaryKey var id: Int
)

@Entity
data class EventViews(@PrimaryKey val id: Int, val viewCount: Int = 0)

@Entity
data class Ticket(val ticketImage: String, val ticketType: String,  val audienceName: String, val time: Long, val seat: String, @PrimaryKey(autoGenerate = true) var id: Int = 0)


@Dao
interface TicketDao {
    @Insert
    suspend fun insert(ticket: Ticket)

    @Query("SELECT * FROM ticket WHERE id = :id")
    suspend fun retrieveTicket(id: Long): Ticket

    @Query("SELECT * FROM ticket WHERE ticketType = :ticketType")
    suspend fun retrieveTicketFromType(ticketType: String): List<Ticket>
}
@Dao
interface EventStatusDao {
    @Insert
    suspend fun insert(eventStatuses: List<EventRead>)

    @Query("SELECT * FROM EventRead")
    fun getAll(): Flow<List<EventRead>>

    @Query("SELECT EXISTS (SELECT * FROM EventRead WHERE id = :id)")
    suspend fun exists(id: Int): Boolean

    @Query("SELECT * FROM EventRead WHERE id = :id")
    suspend fun retrieveEventStatus(id: Int): EventRead




}



@Dao
interface EventViewsDao {
    @Insert
    suspend fun insert(eventViews: List<EventViews>)

    @Update
    suspend fun update(eventView:EventViews)

}

object Db {
    private var db: ApplicationDatabase? = null
    fun initialise(context: Context): ApplicationDatabase {
        db = db ?: Room.databaseBuilder(context, ApplicationDatabase::class.java, "db").build()
        return db as ApplicationDatabase
    }

}

