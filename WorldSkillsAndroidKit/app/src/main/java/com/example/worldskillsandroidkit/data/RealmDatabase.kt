package com.example.worldskillsandroidkit.data

import android.location.Address
import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.realmListOf

import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

object RealmDatabase {
  private var realmDb: Realm? = null
  fun getDb(): Realm {
    realmDb = realmDb ?: Realm.open(configuration = RealmConfiguration.create(
      schema = setOf(Teacher::class, Course::class)
    ))
    return realmDb as Realm
  }
}


class Teacher: RealmObject {
  @PrimaryKey
  var _id: ObjectId = ObjectId()

  var courses: RealmList<Course> = realmListOf()


}
class Course: RealmObject {
  @PrimaryKey var id: ObjectId = ObjectId()
  //var name: String = ""
  var teacher: Teacher? = null


}