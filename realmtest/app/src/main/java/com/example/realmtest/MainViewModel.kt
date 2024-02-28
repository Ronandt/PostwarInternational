package com.example.realmtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val realm = MyApp.realm

    val courses = realm.query<Course>().asFlow().map {
        results ->
        results.list.toList()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )
    init {
        createEntries()
    }
    private fun createEntries() {
        viewModelScope.launch {
            realm.write {
                var address1 = Address().apply {
                    fullName = "john done"
                    street = "Bruh st ret"
                    houseNumber = 25
                    zip =12355
                    city = "John lim"
                }
                var address2 = Address().apply {
                    fullName = "john done"
                    street = "Bruh st ret"
                    houseNumber = 25
                    zip =12355
                    city = "John lim"
                }
                val course1 = Course().apply {
                    name = "Kotlin made 0 "
                }
                val course2 = Course().apply {
                    name = "Kotlin made 1"
                }
                val course3 = Course().apply {
                    name = "Kotlin made 2"
                }
                val teacher1 = Teacher().apply {
                    address = address1
                    courses = realmListOf(course1, course2)
                }
                val teacher2 = Teacher().apply {
                    address = address1
                    courses = realmListOf(course3)
                }
                course1.teacher = teacher1
                course2.teacher =teacher1 //need to take care of bidrectional linkage
                course3.teacher = teacher2
                address1.teacher = teacher1
                address2.teacher = teacher2

                val student1 = Student().apply {
                    name = "John junior"
                }
                val student2 = Student().apply {
                    name = "john lim"
                }

                //this is atuomatic though no bidirrectionality
                course1.enrolledStudents.add(student1)
                course2.enrolledStudents.add(student2)
                course3.enrolledStudents.addAll(listOf(student1, student2))
                copyToRealm(teacher1, updatePolicy = UpdatePolicy.ALL)
                //updatepolicy.all can be used as update
                copyToRealm(teacher2, updatePolicy = UpdatePolicy.ALL)

                copyToRealm(course1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(course2, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(course3, updatePolicy = UpdatePolicy.ALL)

                copyToRealm(student1, updatePolicy = UpdatePolicy.ALL)
                copyToRealm(student2, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }
}