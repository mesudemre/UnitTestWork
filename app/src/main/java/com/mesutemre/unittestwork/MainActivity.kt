package com.mesutemre.unittestwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mesutemre.unittestwork.navigation.UnitTestWorkNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UnitTestWorkApp(startDestination = UnitTestWorkNavigation.MainScreen.screenRoute)
        }
    }
}
