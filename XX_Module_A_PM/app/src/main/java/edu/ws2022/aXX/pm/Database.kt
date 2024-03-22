package edu.ws2022.aXX.pm

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.UUID

class Database() {
    fun getDb(): Realm {

        return Realm.open(configuration = RealmConfiguration.create(
            schema = setOf(Comment::class)
        ))
    }
}

class Comment: RealmObject {
    @PrimaryKey var id: String = UUID.randomUUID().toString()
    var ipAddress: String = ""
    var text: String = ""
    var videoId: String = ""

}