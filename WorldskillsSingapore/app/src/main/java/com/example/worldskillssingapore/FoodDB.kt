package com.example.worldskillssingapore

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.types.RealmObject

object FoodDB {
    fun getDb(): Realm {
        val realm = Realm.open(RealmConfiguration.create(schema = setOf(FoodDbData::class)))
        return realm
    }

}


class FoodDbData(): RealmObject {
    var id: String = "0"
    var hexColor: String = "FFFFFF"
    var foodName:  String = "Name"
    var unixDate: Long = 0
    var calories: String = "0.00"
}