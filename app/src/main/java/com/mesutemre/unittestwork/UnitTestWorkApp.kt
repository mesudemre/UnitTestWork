package com.mesutemre.unittestwork

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mesutemre.namazvakitleri.ui.components.UnitTestWorkScaffold
import com.mesutemre.unittestwork.navigation.UnitTestWorkNavGraph
import com.mesutemre.unittestwork.navigation.UnitTestWorkNavigation
import com.mesutemre.unittestwork.ui.theme.UnitTestWorkTheme

@Composable
fun UnitTestWorkApp(
    startDestination: String
) {
    UnitTestWorkTheme {
        val appState = rememberUnitTestWorkAppState()

        UnitTestWorkScaffold(
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.primary
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Rounded.ArrowBackIosNew,
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 12.dp)
                            .then(
                                if (appState.hasBackButton) {
                                    Modifier.clickable {
                                        appState.popBack()
                                    }
                                } else Modifier
                            ),
                        tint = Color.White,
                        contentDescription = null)
                    Text(
                        text = appState.screenTitle, style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 12.dp),
                        color = Color.White
                    )
                }

            }
        ) {
            UnitTestWorkNavGraph(
                startDestination = startDestination,
                modifier = Modifier.padding(it),
                navController = appState.navController
            )
        }
    }
}
