package com.example.learningpackageeditor.ui.navHost

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.learningpackageeditor.Repositories.PackagesRepo.newWord
import com.example.learningpackageeditor.Repositories.PackagesRepo.wordsList
import com.example.learningpackageeditor.Repositories.UserRepo
import com.example.learningpackageeditor.model.LearningPackage
import com.example.learningpackageeditor.model.User
import com.example.learningpackageeditor.model.AppViewModel
import com.example.learningpackageeditor.model.Word
import com.example.learningpackageeditor.ui.screens.packagesScreen.CompletePackagesScreen
import com.example.learningpackageeditor.ui.screens.authentication.LoginScreen
import com.example.learningpackageeditor.ui.screens.authentication.SignUp
import com.example.learningpackageeditor.ui.screens.packageEditor.EditAddPackage
import com.example.learningpackageeditor.ui.screens.sentenceEditor.ResourcesScreen
import com.example.learningpackageeditor.ui.screens.wordEditor.EditAddWord

@Composable
fun MyNavHost(navHostController: NavHostController){

    val appViewModel = viewModel<AppViewModel>()

    var addMode by remember { mutableStateOf(false) }
    var wordAddMode by remember { mutableStateOf(false) }

    val user1 = User(
        fname = "John",
        lname = "Doe",
        email = "johndoe@example.com",
        password = "Secure@9",
        id = "246"
    )
    UserRepo.addUser(user = user1, appViewModel=appViewModel)

    NavHost(
        navController = navHostController,
        startDestination = Screens.LoginScreen.route
    ) {

        composable(route = Screens.LoginScreen.route) {
           LoginScreen(
               appViewModel = appViewModel,
               onNavigateToSignUp = {
                   navHostController.navigate(Screens.signUpScreen.route)
               },
               onNavigateToCompletePackagesScreen = {
                   navHostController.navigate(Screens.PackageScreen.route)
               } )
           }

        composable(route = Screens.signUpScreen.route) {
            SignUp(appViewModel= appViewModel){
                navHostController.navigate(Screens.LoginScreen.route)
            }
        }

        composable(route = Screens.PackageScreen.route) {
            CompletePackagesScreen(modifier = Modifier, appViewModel= appViewModel,
                onAddPackage = {
                    addMode = true
                    appViewModel.currentPackage.value = LearningPackage(
                        author = appViewModel.currentUser.value, category ="", description = "", keywords = mutableListOf(),
                        language = "", lastUpdatedDate = "", level = "", title = "", words = mutableListOf())

                    navHostController.navigate(Screens.EditAddPackageScreen.route)
                               },
                onEditPackage = {
                    addMode = false
                    appViewModel.currentPackage.value = it
                    //userViewModel.currentPackage.value.version = userViewModel.currentPackage.value.version!! + 1
                    navHostController.navigate(Screens.EditAddPackageScreen.route)
                }
            )
        }

        composable(route = Screens.EditAddPackageScreen.route){
            EditAddPackage(
                lPackage = appViewModel.currentPackage.value,
                appViewModel = appViewModel,
                onNavigateToAddWordScreen = {
                    wordAddMode = true
                    appViewModel.currentWord = Word("", mutableListOf(), mutableListOf())
                    appViewModel.currentPackage.value = it
                    navHostController.navigate(Screens.AddWordScreen.route)
                },
                onNavigateToEditWordScreen = {
                    wordAddMode = false
                    appViewModel.currentWord = it
                    navHostController.navigate(Screens.AddWordScreen.route)
                },
                onNavigateToCompletePackageScreens = {
                    navHostController.navigate(Screens.PackageScreen.route) },
                addMode = addMode,
                navController = navHostController
            )
        }

        composable(route = Screens.AddWordScreen.route){
            EditAddWord(word = appViewModel.currentWord, navController =  navHostController, addMode = wordAddMode, appViewModel = appViewModel,
                onAddResources = {
                appViewModel.currentSentence.value = it
                navHostController.navigate(Screens.ResourceEditor.route)
            }
            ){
                if(wordAddMode) {
                    newWord = appViewModel.currentWord
                    wordsList.add(newWord)
                    appViewModel.currentPackage.value.words.add(appViewModel.currentWord)
                    appViewModel.currentWord = newWord
                }
                else{

                }
                navHostController.navigate(Screens.EditAddPackageScreen.route)
             }

        }

        composable(route = Screens.ResourceEditor.route){
            ResourcesScreen(navController = navHostController, appViewModel= appViewModel,
                sentence = appViewModel.currentSentence.value, onConfirm = { s->
                    navHostController.navigate(Screens.AddWordScreen.route)
                }
            )
        }
    }
}