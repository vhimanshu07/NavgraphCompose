package com.example.navgraphcompose

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


/**
 * Created by Himanshu Verma on 02/08/24.
 **/
@SuppressLint("MutableCollectionMutableState")
@Composable
fun ScreenA(navController: NavHostController) {
    println("Screen A")
    val inputText = rememberSaveable {
        mutableStateOf("")
    }
    val newAge = rememberSaveable { mutableStateOf<String>("") }
    val suggestionsList =
        rememberSaveable { mutableStateOf<List<SuggestedChipDetails>>(mutableListOf()) }
    val chipList by rememberSaveable {
        mutableStateOf<MutableList<SuggestedChip>>(mutableListOf())
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Screen A", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(65.dp))
        Button(onClick = {

            // Here we have to pass for navigating without argument
            //   navController.navigate(ScreenName.SCREEN_B.value)

            // with argument and mandatory to pass
            // navController.navigate("ScreenB/${inputText.value}")

            // with argument and optional to pass
            //  navController.navigate("ScreenB?name=${inputText.value}&age=${ageInput.value}")
            navController.navigate("ScreenB?name=${inputText.value}")


        }) {
            Text(text = "Go to Screen B", fontSize = 40.sp)
        }
        OutlinedTextField(value = inputText.value, onValueChange = {
            inputText.value = it
        }, label = {
            Text(text = "Please enter some value here ")
        }
        )
        Spacer(modifier = Modifier.height(20.dp))


        CustomTextInputLayout2(
            hintText = "Age",
            hintColor1 = N500,
            hintTextStyle = TextStyle.Default,
            inputTextState = newAge,
            gridView = true,
            suggestedItemList = suggestionsList,
            inputTextClicked = {
                newAge.value = "18yrs-20yrs"
                suggestionsList.value = fetchSuggestions()

            },
            suggestedChipClicked = { data ->
                newAge.value = if (data.isSelected) {
                    chipList.add(data)
                    var ans = ""
                    var count = 0
                    for (item in chipList) {
                        if (count > 2) {
                            ans += "+" + (chipList.size - count) + "more"
                            break
                        }
                        ans += item.clickedChipDetails.value

                        if (count != chipList.size - 1)
                            ans += " , "
                        count++

                    }
                    ans
                } else {
                    var ans = ""
                    var count = 0
                    var index = 0
                    for (item in chipList) {
                        if (item.clickedChipDetails.label == data.clickedChipDetails.label) {
                            index = count
                        }
                        count++
                    }
                    count = 0
                    chipList.removeAt(index)
                    for (item in chipList) {
                        if (count > 2) {
                            ans += "+" + (chipList.size - count) + "more"
                            break
                        }
                        if (item.isSelected) {
                            ans += item.clickedChipDetails.value
                            if (count != chipList.size - 1)
                                ans += " , "
                        }
                        count++
                    }
                    if (chipList.isEmpty()) {
                        ""
                    } else
                        ans
                }
                fetchMoreList(data, suggestionsList)
            },
            chipsTextModifier = Modifier.padding(
                start = 12.dp,
                top = 8.dp,
                bottom = 8.dp,
                end = 8.dp
            ),
            drawableLeftModifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp,
                top = 8.dp,
                bottom = 8.dp
            ), multiChipSelection = true
        )


    }
}

fun fetchSuggestions(): List<SuggestedChipDetails> {
    return listOf(
        SuggestedChipDetails(
            label = "1",
            value = "Ghaziabad",
        ),
        SuggestedChipDetails(
            label = "2",
            value = "Delhi"
        ),
        SuggestedChipDetails(
            label = "3",
            value = "Noida"
        ),
        SuggestedChipDetails(
            label = "4",
            value = "Greater Noida"
        ),
        SuggestedChipDetails(
            label = "5",
            value = "Gurugram"
        ),
        SuggestedChipDetails(
            label = "6",
            value = "Faridabad"
        ),
        SuggestedChipDetails(
            label = "7",
            value = "Harayana"
        )
    )

}

fun fetchMoreList(data: SuggestedChip, suggestionsList: MutableState<List<SuggestedChipDetails>>) {
    val inputText = data.inputText
    val clickedChip = data.clickedChipDetails

}