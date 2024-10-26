package com.example.learningpackageeditor.ui.screens.wordEditor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.learningpackageeditor.model.Word
import com.example.learningpackageeditor.ui.UIcomponents.BackwardButton
import com.example.learningpackageeditor.ui.UIcomponents.SubmitButton
import com.example.learningpackageeditor.ui.theme.backgroundColor
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAddWord(
    addMode: Boolean,
    word: Word,
    appViewModel: AppViewModel,
    navController: NavHostController,
    onAddResources: (Sentence) -> Unit,
    onConfirm: () -> Unit
) {

    var wordText by rememberSaveable { mutableStateOf(word.text) }
    var definitions by rememberSaveable { mutableStateOf(word.definitions) }
    definitions = definitions.toMutableList()

    var sentences by rememberSaveable { mutableStateOf(word.sentences) }
    sentences = sentences.toMutableList()

    var screenTitle = ""
    if (addMode) screenTitle= "Add Word" else screenTitle = "Edit Word"


    var isErrorAlertVisible by remember { mutableStateOf(false) }

    val customTextFieldDefaults = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = darkBlue,
        unfocusedBorderColor = darkBlue
    )

    Surface(color = backgroundColor) {
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier){
            TopAppBar(
                title = { Text("Learning Package Editor") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = darkBlue, titleContentColor = white, actionIconContentColor = white
                ),
                navigationIcon = {BackwardButton(navController, currentScreen = "EditAddWord")}
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(35.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = screenTitle,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(20.dp)
            )
            Text(
                text = "Word",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 10.dp, start = 15.dp)
            )
            TextField(
                value = wordText,
                onValueChange = {
                    wordText = it
                    appViewModel.currentWord.text=wordText },
                modifier = Modifier
                    .height(50.dp),
                colors = customTextFieldDefaults,
                textStyle = TextStyle(fontSize = 15.sp),
                singleLine = true
            )
            Text(
                text = "Definitions",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 10.dp, start = 15.dp)
            )
            AddDef(){
                    def -> definitions = definitions.toMutableList().also { it.add(def) }
                    appViewModel.currentWord.definitions.add(def)
            }
            EditRemoveDefList(definitions){
                    def -> definitions = definitions.toMutableList().also { it.remove(def) }
                    appViewModel.currentWord.definitions.remove(def)
            }
            Text(
                text = "Sentences",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 10.dp, start = 15.dp)
            )
            AddSentence(){
                    sentence -> sentences = sentences.toMutableList().also { it.add(sentence) }
                    appViewModel.currentWord.sentences.add(sentence)
            }
            EditRemoveSentenceList(sentences, onAddResources = {onAddResources(it)}){
                    sentence -> sentences = sentences.toMutableList().also { it.remove(sentence) }
                    appViewModel.currentWord.sentences.remove(sentence)
            }
            SubmitButton {
                if(appViewModel.currentWord.text.isBlank()) {
                    isErrorAlertVisible = true
                }
                else {
                    if (addMode){
                        appViewModel.currentWordList.add(appViewModel.currentWord)
                    } else if (!addMode){
//                        var w = appViewModel.currentWordList.find { it.text == word.text }
//                        w = appViewModel.currentWord
                        appViewModel.currentWordList.map {
                            if (it.text == wordText) appViewModel.currentWord else it }
                    }
                    onConfirm()
                }
            }
        }
    }
    if (isErrorAlertVisible) {
        AlertDialog(
            onDismissRequest = { isErrorAlertVisible = false },
            title = { Text("Unable to Add Word") },
            text = { Text("Please enter a valid word.") },
            confirmButton = {
                TextButton(onClick = { isErrorAlertVisible = false }) {
                    Text("OK")
                }
            }
        )
    }
}