package com.example.navgraphcompose

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

/**
 * Created by Himanshu Verma on 02/08/24.
 **/

enum class ScreenName(val value: String) {
    SCREEN_A("ScreenA"),
    SCREEN_B("ScreenB"),
    SCREEN_C("ScreenC")
}

// ANIMATION in Navigation Graph
val enterTransition: EnterTransition =
    slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(500)) +
            fadeIn(animationSpec = tween(500))

val exitTransition: ExitTransition =
    slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(500)) +
            fadeOut(animationSpec = tween(500))

val popEnterTransition: EnterTransition =
    slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(500)) +
            fadeIn(animationSpec = tween(500))

val popExitTransition: ExitTransition =
    slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(500)) +
            fadeOut(animationSpec = tween(500))

@Composable
fun Nav(navController: NavHostController) {


    // Setting Nav Host

    NavHost(
        navController = navController,
        startDestination = ScreenName.SCREEN_A.value
    ) {

        composable(route = ScreenName.SCREEN_A.value,
            enterTransition = {
                enterTransition
            },
            exitTransition = {
                exitTransition
            },
            popEnterTransition = {
                popEnterTransition
            },
            popExitTransition = {
                popExitTransition
            }
        ) {
            ScreenA(navController)
        }


        // Without argument
//        composable(route = ScreenName.SCREEN_B.value) {
//            ScreenB(navController )
//        }


        // With argument
        // This way it is required argument if we don't provide it the app will crash
//        composable(
//            route = "ScreenB/{name}",
//            arguments = listOf(
//                navArgument("name") {
//                    type = NavType.StringType
//                }
//            )
//        ) { backStackEntry ->
//            ScreenB(navController, myName = backStackEntry.arguments?.getString("name"))
//        }

        // With argument
        // This way it is optional argument need to change from where it is called too.
        composable(
            route = "ScreenB?name={name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "USER"
                    //  defaultValue = ""
                    //  nullable = true
                }
            ),
            enterTransition = {
                enterTransition
            },
            exitTransition = {
                exitTransition
            },
            popEnterTransition = {
                popEnterTransition
            },
            popExitTransition = {
                popExitTransition
            }
        ) { backStackEntry ->
            ScreenB(navController, myName = backStackEntry.arguments?.getString("name"))
        }


        composable(route = ScreenName.SCREEN_C.value,
            enterTransition = {
                enterTransition
            },
            exitTransition = {
                exitTransition
            },
            popEnterTransition = {
                popEnterTransition
            },
            popExitTransition = {
                popExitTransition
            }
        ) {
            ScreenC(navController)
        }


    }


}