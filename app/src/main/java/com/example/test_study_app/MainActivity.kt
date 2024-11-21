package com.example.test_study_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.core.lifecycle.ScreenLifecycleStore
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.core.base.CoreBaseActivity
import com.example.core.navigation.ScreenLifecycleOwner
import com.example.registration.RegistrationScreen
import com.example.theme.TestStudyAppTheme
import kz.secret_santa_jusan.presentation.Auth.AuthScreen
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : CoreBaseActivity() {
    override fun hideBottomBar() {
    }

    override fun showBottomBar() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinAndroidContext {
                TestStudyAppTheme{
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {

                        Navigator(
                            screen = RegistrationScreen(),
                            content = { navigator ->
                                remember(navigator.lastItem) {
                                    ScreenLifecycleStore.get(navigator.lastItem) {
                                        ScreenLifecycleOwner()
                                    }
                                }
                                    CurrentScreen()
                            },
                        )
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
    TestStudyAppTheme{
        Greeting("Android")
    }
}