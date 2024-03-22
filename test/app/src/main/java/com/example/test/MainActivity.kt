package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.theme.TestTheme
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.flow.map

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
                val navController = rememberNavController()
              NavHost(navController = navController, startDestination = "home") {
                  composable("home") {
                      HomeScreen(navController)
                  }
                  composable("chat/{prompt}") {
                      ChatScreen(navController, it.arguments?.getString("prompt"))

                  }

              }
                /*
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
                }*/
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