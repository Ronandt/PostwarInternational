package com.example.worldskills

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.util.UUID

object RealmDb {
    fun getDb(): Realm {
      return  Realm.open(configuration = RealmConfiguration.create(
            schema = setOf(EventData::class, TicketData::class)
        ))
    }
}

class EventData:RealmObject {
    @PrimaryKey
    var _id: String? = null
    var viewCount: Int = 0
    var read: Boolean = false

}

class TicketData: RealmObject {
    @PrimaryKey
    var _id: String= UUID.randomUUID().toString()

    var name: String = ""
    var row: String = ""
    var image: String = ""
    var type: String = ""
    var time: String = ""
}