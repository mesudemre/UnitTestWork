package com.mesutemre.unittestwork

import android.content.res.Resources
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mesutemre.unittestwork.navigation.UnitTestWorkNavigation
import java.io.InputStream

@Composable
fun rememberUnitTestWorkAppState(
    navController: NavHostController = rememberNavController(),
    resources: Resources = resources()
) =
    remember(navController) {
        UnitTestWorkAppState(navController, resources)
    }

@Stable
class UnitTestWorkAppState(
    val navController: NavHostController,
    private val resources: Resources
) {

    fun getStringResource(@StringRes id: Int): String = resources.getString(id)

    private val screenList = UnitTestWorkNavigation::class.nestedClasses.map {
        it.objectInstance as UnitTestWorkNavigation
    }

    private val currentRoute: String?
        get() = navController.currentDestination?.route


    val hasBackButton: Boolean
        @Composable get() =
            screenList.filter {
                it.screenRoute == navController.currentBackStackEntryAsState().value?.destination?.route
                    &&
                    it.hasBack
            }.isNullOrEmpty().not()

    fun popBack(
        route: String? = null,
        inclusive: Boolean = false
    ) {
        route?.let {
            navController.popBackStack(
                route = route,
                inclusive = inclusive
            )
        } ?: run {
            navController.popBackStack()
        }
    }

    val screenTitle: String
        get() = "mesut emre"
}

/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}