package com.example.worldskills_am_b

import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.RealmObject
import java.util.UUID

class Database {
    fun getDb(): Realm {
       return Realm.open(configuration = RealmConfiguration.create(
            schema = setOf(CommentData::class)
        ))

    }
}


class CommentData(): RealmObject {
    var id: String = UUID.randomUUID().toString()
    var videoId: String = "0"
    var ipAddress: String = ""
    var commentText: String = ""
}