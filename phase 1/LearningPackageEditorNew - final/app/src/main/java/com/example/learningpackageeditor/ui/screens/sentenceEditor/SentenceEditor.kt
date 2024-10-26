package com.example.learningpackageeditor.ui.screens.sentenceEditor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.learningpackageeditor.model.Sentence
import com.example.learningpackageeditor.model.AppViewModel
import com.example.learningpackageeditor.ui.UIcomponents.BackwardButton
import com.example.learningpackageeditor.ui.UIcomponents.SubmitButton
import com.example.learningpackageeditor.ui.theme.backgroundColor
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourcesScreen(
    navController: NavHostController,
    sentence: Sentence,
    appViewModel: AppViewModel,
    onConfirm: (Sentence) -> Unit
) {

    var sentenceText by rememberSaveable {
        mutableStateOf(sentence.text)
    }

    var resources by rememberSaveable {
        mutableStateOf(sentence.resources)
    }
    resources = resources.toMutableList()

    val customTextFieldDefaults = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = darkBlue,
        unfocusedBorderColor = darkBlue
    )

    val scrollState = rememberScrollState()

    Surface(color = backgroundColor) {
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier){
            TopAppBar(
                title = { Text("Learning Package Editor") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = darkBlue, titleContentColor = white, actionIconContentColor = white
                ),
                navigationIcon ={ BackwardButton(navController, currentScreen = "ResourcesScreen")}
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(35.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Sentence Editor",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(15.dp)
            )
            Text(
                text = "Sentence",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 10.dp)
            )
            TextField(
                value = sentenceText,
                onValueChange = {
                    sentenceText = it
                    appViewModel.currentSentence.value.text = sentenceText
                                },
                modifier = Modifier
                    .height(50.dp),
                colors = customTextFieldDefaults,
                textStyle = TextStyle(fontSize = 15.sp)
            )
            Row(modifier = Modifier
                .padding(top = 25.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start) {
                Text(
                    text = "Resources",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .padding(end = 20.dp)
                )
            }
            Row(modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .height(300.dp)) {
                ResourceItemList(
                    resources
                ){r ->
                    resources= resources.toMutableList().also { it.add(r) }
                    appViewModel.currentSentence.value.resources = resources
                }
            }
            SubmitButton {onConfirm(sentence)}
        }
    }
}