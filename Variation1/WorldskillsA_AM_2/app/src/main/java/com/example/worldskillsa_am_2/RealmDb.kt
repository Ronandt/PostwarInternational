package com.example.worldskillsa_am_2

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

object RealmDb {

    fun getDb(): Realm {
       return Realm.open(configuration = RealmConfiguration.create(
            schema = setOf(EventData::class, TicketData::class)
        ))

    }
}

class EventData : RealmObject {
    @PrimaryKey
    var id: String = "0"

    var image: String = "0"

    var title: String = ""

    var text: String = ""

    var viewCount: Int = 0

    var otherImages: RealmList<String> = realmListOf()



    var read: Boolean = false


}

class TicketData: RealmObject {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()

    var ticketImage: String = ""
    var ticketType: String = ""
    var audienceName: String = ""
    var seat: String = ""
    var timestamp: String = ""
}