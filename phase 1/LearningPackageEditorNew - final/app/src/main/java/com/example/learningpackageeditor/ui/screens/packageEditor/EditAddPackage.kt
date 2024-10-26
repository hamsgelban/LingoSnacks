package com.example.learningpackageeditor.ui.screens.packageEditor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.learningpackageeditor.Repositories.PackagesRepo.addPackage
import com.example.learningpackageeditor.Repositories.PackagesRepo.newWord
import com.example.learningpackageeditor.Repositories.PackagesRepo.updatePackage
import com.example.learningpackageeditor.Repositories.PackagesRepo.wordsList
import com.example.learningpackageeditor.model.LearningPackage
import com.example.learningpackageeditor.model.AppViewModel
import com.example.learningpackageeditor.model.Word
import com.example.learningpackageeditor.ui.UIcomponents.BackwardButton
import com.example.learningpackageeditor.ui.UIcomponents.RadioButtonGroup
import com.example.learningpackageeditor.ui.UIcomponents.SubmitButton
import com.example.learningpackageeditor.ui.theme.backgroundColor
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.white
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAddPackage(
    addMode: Boolean,
    navController: NavHostController,
    lPackage: LearningPackage,
    appViewModel: AppViewModel,
    onNavigateToAddWordScreen: (LearningPackage) -> Unit,
    onNavigateToEditWordScreen: (Word) -> Unit,
    onNavigateToCompletePackageScreens:  () -> Unit
) {
    val radioOptions = listOf("beginner", "intermediate", "advanced")
    var isErrorAlertVisible by remember { mutableStateOf(false) }

    var packageName by rememberSaveable { mutableStateOf(lPackage.title) }
    var level by rememberSaveable { mutableStateOf(lPackage.level) }
    var selectedLevelIndex by rememberSaveable { mutableStateOf(radioOptions.indexOf(lPackage.level)) }
    var category by rememberSaveable { mutableStateOf(lPackage.category) }
    var description by rememberSaveable { mutableStateOf(lPackage.description) }
    var language by rememberSaveable { mutableStateOf(lPackage.language) }
    var words by rememberSaveable { mutableStateOf(lPackage.words) }
    words = words.toMutableList()

    var keywords by rememberSaveable { mutableStateOf(lPackage.keywords) }
    keywords = keywords.toMutableList()

    val currentDate = Date()
    var screenTitle = ""

    if (addMode) screenTitle= "Add Package" else screenTitle = "Edit Package"

    val scrollState = rememberScrollState()

    val customTextFieldDefaults2 = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = darkBlue,
        unfocusedBorderColor = darkBlue,
        containerColor = white
    )

    val basicTextFieldModifier = Modifier
        .padding(end = 5.dp, top = 5.dp)
        .width(300.dp)
        .height(35.dp)
        .background(backgroundColor)

    Surface(color = backgroundColor){
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier){
            TopAppBar(
                title = { Text("Learning Package Editor") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = darkBlue, titleContentColor = white, actionIconContentColor = white),
                navigationIcon = {BackwardButton(navController, currentScreen = "EditAddPackage")}
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .verticalScroll(state = scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = screenTitle,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(25.dp)
            )
            //Row {
            Text(
                text = "Package Name:",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .align(Alignment.Start)
            )
            Column {
                BasicTextField(
                    value = packageName,
                    onValueChange = {
                        packageName = it
                        appViewModel.currentPackage.value.title = it
                        },
                    modifier = basicTextFieldModifier,
                    textStyle = MaterialTheme.typography.headlineSmall
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(darkBlue)
                        .border(1.dp, darkBlue)
                )
            }
            Text(
                text = "Level: ",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .padding(top = 25.dp, bottom = 15.dp)
                    .align(Alignment.Start)
            )
            Column {
                Row{
                    RadioButtonGroup(
                        options = radioOptions,
                        selectedOptionIndex = selectedLevelIndex,
                        onOptionSelected = { index, text ->
                            run {
                                selectedLevelIndex = index
                                level = text
                                appViewModel.currentPackage.value.level=text
                            }
                        }
                    )
                }
            }
            Text(
                text = "Language: ",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .padding(top = 25.dp)
                    .align(Alignment.Start)
            )
            Column {
                BasicTextField(
                    value = language,
                    onValueChange = {
                        language = it
                        appViewModel.currentPackage.value.language = it
                                    },
                    modifier = basicTextFieldModifier,
                    textStyle = MaterialTheme.typography.headlineSmall
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(darkBlue)
                        .border(1.dp, darkBlue)
                        .padding(bottom = 25.dp)
                )
            }

            CategoryDropDown(selectedCategory = category) { selectedCategory ->
                category = selectedCategory
                appViewModel.currentPackage.value.category = selectedCategory
            }

            Text(
                text = "Description",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .padding(top = 25.dp, bottom = 10.dp)
                    .align(Alignment.Start)
            )
            OutlinedTextField(
                value = description,
                onValueChange = {description = it
                    //newPackage?.description=it
                    appViewModel.currentPackage.value.description = it
                                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth(),
                colors = customTextFieldDefaults2,
                textStyle = MaterialTheme.typography.headlineSmall,
                singleLine = false
            )

            EditAddWordList(
                onAdd = {
                        onNavigateToAddWordScreen(appViewModel.currentPackage.value)
                        words = words.toMutableList().also { it.add(newWord) }
                },
                edit = {
                    onNavigateToEditWordScreen(it)
                },
                words = words
            ) { word ->
                    words = words.toMutableList().also { it.remove(word) }
                    appViewModel.currentPackage.value.words = words
            }

            Text(
                text = "Keywords",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier
                    .padding(top = 25.dp, bottom = 10.dp)
                    .align(Alignment.Start)
            )

            EditAddKeywordList(
                keywords = keywords,
                onAdd = { keyword ->
                    keywords = keywords.toMutableList().also { it.add(keyword) }
                    appViewModel.currentPackage.value.keywords = keywords
                }
            ) { keyword ->
                keywords = keywords.toMutableList().also { it.remove(keyword) }
                appViewModel.currentPackage.value.keywords = keywords
            }
            SubmitButton {
                if (packageName.isBlank() || level.isBlank() || language.isBlank() || category.isBlank() || description.isBlank()) {
                    isErrorAlertVisible = true
                } else {
                    if (addMode) {
                        addPackage(appViewModel.currentPackage.value)
                    } else {
                        updatePackage(lPackage, appViewModel.currentPackage.value)
                    }

                    onNavigateToCompletePackageScreens()
                    wordsList.clear()
                }
            }
        }
    }
    if (isErrorAlertVisible) {
        AlertDialog(
            onDismissRequest = { isErrorAlertVisible = false },
            title = { Text("Unable to Add Package") },
            text = { Text("Please fill in all the required fields") },
            confirmButton = {
                TextButton(onClick = { isErrorAlertVisible = false }) {
                    Text("OK")
                }
            }
        )
    }
}
