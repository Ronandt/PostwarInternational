package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.test.ui.theme.TestTheme
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var flow = RealmDb.getDb().query<Forest>().asFlow().map {
            println(it.list.toList())
            it.list.toList()

        }


        setContent {
            var state = flow.collectAsState(initial = listOf())
            LaunchedEffect(key1 = Unit ) {
                RealmDb.getDb().write {
               var forest =  Forest().apply {
                var frog = Frog().apply { name = "Hello" }
                    var frog1 = Frog().apply { name = "Hello" }
                    frogs = realmListOf(frog, frog1)
                }
                    copyToRealm(forest)
                }
            }



            TestTheme {
                LazyColumn() {
                    items(state.value.size) {
                        println(state.value[it]._id)
                        LaunchedEffect(key1 = Unit ) {
                            RealmDb.getDb().query<Frog>("_id == $0", state.value[it].frogs.first()._id).asFlow().map {
                                println(it.list.toList())
                                it.list.toList()

                            }.collect()
                        }
                        Text(text = state.value[it].frogs.toList().map {it._id}.toString())
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestTheme {
        Greeting("Android")
    }
}