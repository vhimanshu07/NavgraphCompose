package com.example.navgraphcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            //   App()

            // Nav Controller
            val navController = rememberNavController()
            Nav(navController)

            /**
             *       // Here we have to pass for navigating
             *             navController.navigate(ScreenName.SCREEN_A.value)
             *             By this way we are adding items in stack items are getting added
             *
             *          // Here we have to pass for navigating
             *          //            navController.navigate(ScreenName.SCREEN_A.value)
             *             But if want it to remove it then->
             *
             *             A-> B->C ,
             *             Now i want to navigate to A
             *
             *             use
             *             // Using popupto it removes all other screens in stack
             *
             *             navController.navigate(ScreenName.SCREEN_A.value) {
             *                 popUpTo(ScreenName.SCREEN_A.value)
             *             }
             *
             *             But it adds two instance of Screen A
             *
             *             for one instance
             *               navController.navigate(ScreenName.SCREEN_A.value) {
             *                 popUpTo(ScreenName.SCREEN_A.value) {
             *                    inclusive = true
             *                 }
             *             }
             *
             *
             *
             */
        }

    }
}

@Composable
fun App() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Registration") {
        /**
         *
         * Step 1
         * Made a new node, with route as Registration screen
         *
         */
//        composable(route = "Registration") {
//            RegistrationScreen {
//                navController.navigate("Login")
//            }
//
//        }
//        composable(route = "Login") {
//            LoginScreen {
//                /**
//                 * Step 2
//                 * Made clickable text in every screen and made use of lamda function
//                 *
//                 */
//                navController.navigate("Main")
//            }
//        }
//
//        composable(route = "Main") {
//            MainScreen()
//        }

        /**
         *
         * We are adding nav arguement which helps in transfering data
         *
         */

        composable(route = "Registration") {
            RegistrationScreen {
                navController.navigate("Main/${it}")
            }

        }

        composable(route = "Main/{email}", arguments = listOf(
            navArgument("email") {
                type = NavType.StringType
            }
        )
        ) {
            val email = it.arguments?.getString("email")
            MainScreen(email ?: "")
        }


    }

    /**
     *
     * Another flow is there for Navigation btw screen A, B and C.
     *
     * https://www.youtube.com/watch?v=U12asuT4l6Q&list=PLEGrY4uRTu5nQFodJ6MCgDtpqsU1tlt-g&index=1
     *
     *
     */


}

@Composable
fun RegistrationScreen(onClick: (email: String) -> Unit) {
    Text(
        text = "Registration",
        style = MaterialTheme.typography.displayLarge,
        modifier = Modifier.clickable {
            onClick("xyx@gmail.com")
        })
}

@Composable
fun LoginScreen(onClick: () -> Unit) {
    Text(
        text = "Login",
        style = MaterialTheme.typography.displayLarge, modifier = Modifier.clickable {
            onClick()
        })
}

@Composable
fun MainScreen(email: String) {
    Text(text = "Main - ${email}", style = MaterialTheme.typography.displayLarge)
}


