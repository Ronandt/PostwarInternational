package com.example.realmtest

import io.realm.kotlin.Realm
import io.realm.kotlin.ext.backlinks
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

//Teacher -> Address
//Teacher ->> Course
//Students <<->> Course

//Will always be attached to its teacher so it will be mbeeded, cannot be a seperate entity
class Address: EmbeddedRealmObject {
   // @PrimaryKey
    //var id: ObjectId = ObjectId()
    var fullName: String = ""
            var street: String = ""
    var houseNumber: Int = 0
    var zip: Int = 0
    var city: String = ""
    var teacher: Teacher? = null
}

class Teacher: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var address: Address? = null
    var courses: RealmList<Course> = realmListOf()


}

class Course: RealmObject {
    @PrimaryKey var id: ObjectId = ObjectId()
    var name: String = ""
    var teacher: Teacher? = null
    var enrolledStudents: RealmList<Student> = realmListOf()

}

class Student: RealmObject {
    @PrimaryKey var id: ObjectId = ObjectId()
    var name: String = ""
    val enrolledCourses: RealmResults<Course> by backlinks(Course::enrolledStudents) //backlink and many to many
}