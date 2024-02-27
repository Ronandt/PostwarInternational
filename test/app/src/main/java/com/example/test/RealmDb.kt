package com.example.test

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

object RealmDb {
    private var realm: Realm? = null
    fun getDb(): Realm {
        realm = realm ?: Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(Frog::class, Forest::class)
            )
        )

        return realm as Realm

    }
}

class Frog : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""

}

class Forest: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var frogs: RealmList<Frog> = realmListOf()

}
