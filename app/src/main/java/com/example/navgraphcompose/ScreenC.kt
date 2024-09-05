package com.example.navgraphcompose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

/**
 * Created by Himanshu Verma on 02/08/24.
 **/
@Composable
fun ScreenC(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen C", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(65.dp))
        Button(onClick = {


//            // Here we have to pass for navigating
//            navController.navigate(ScreenName.SCREEN_A.value)
//

            // Using popupto it removes all other screens in stack

            navController.navigate(ScreenName.SCREEN_A.value) {
                popUpTo(ScreenName.SCREEN_A.value) {
                    inclusive = true
                }
            }


        }) {
            Text(text = "Go to Screen A", fontSize = 40.sp)

        }

    }
    BackHandler {
        navController.popBackStack()
    }
}