package com.example.worldskillsandroidkit.Practice

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.RealmObject
import java.util.UUID

class Db() {
    fun getDb(): Realm {
        val realm = Realm.open(RealmConfiguration.create(
            schema = setOf(Test::class)
        ))
        return realm
    }
}


class Test: RealmObject {
    var id: String = UUID.randomUUID().toString()
    var data: String = UUID.randomUUID().toString()
}
