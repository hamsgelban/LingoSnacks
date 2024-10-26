package com.example.learningpackageeditor.ui.screens.authentication

import com.example.learningpackageeditor.model.AppViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.learningpackageeditor.Repositories.UserRepo
import com.example.learningpackageeditor.ui.theme.LearningPackageEditorTheme
import com.example.learningpackageeditor.ui.theme.backgroundColor
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.lightBlue
import com.example.learningpackageeditor.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    appViewModel: AppViewModel,
    onNavigateToSignUp: () -> Unit,
    onNavigateToCompletePackagesScreen: () -> Unit
){

    var emailQuery by remember {
        mutableStateOf("")
    }
    var passQuery by remember {
        mutableStateOf("")
    }

    var islogin: Boolean

    val scrollState = rememberScrollState()

    var showSnackBar by remember { mutableStateOf(false) }

    var msg by remember {
        mutableStateOf("")
    }

    val customTextFieldDefaults = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = darkBlue,
        unfocusedBorderColor = darkBlue,
    )

    Surface(color = backgroundColor) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(45.dp)
                .verticalScroll(state = scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Welcome !",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(20.dp)
            )
            Text(
                text = "Email",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top=30.dp, start = 15.dp)

            )
            TextField(
                value = emailQuery,
                onValueChange = {emailQuery = it},
                modifier = Modifier,
                colors = customTextFieldDefaults
            )
            Text(
                text = "Password",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top=30.dp, start = 15.dp)
            )
            TextField(
                value = passQuery,
                onValueChange = { passQuery = it},
                modifier = Modifier
                    .padding(bottom = 30.dp),
                colors = customTextFieldDefaults,
                visualTransformation = PasswordVisualTransformation(),
            )
            ElevatedButton(
                modifier = Modifier
                    .padding(vertical = 30.dp)
                    .width(300.dp)
                    .height(60.dp),
                colors = ButtonDefaults.elevatedButtonColors(containerColor = lightBlue),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 10.dp),
                onClick =
                {
                   islogin = UserRepo.getUser(emailQuery,passQuery, appViewModel)
                    if (islogin) {
                        appViewModel.currentUser.value = appViewModel.userList.value.find { it.email == emailQuery}!!
                        onNavigateToCompletePackagesScreen()
                        msg = "Login Successful"
                    }
                    else{
                        msg = "Incorrect email or password"
                    }
                    showSnackBar = true
                }
            ) {
                Text(
                    text = "Log in",
                    color = darkBlue
                )
            }
            Text(
                text = "Don't Have an Account? Sign-up",
                textDecoration = TextDecoration.Underline,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        onNavigateToSignUp()
                    }
            )
            if (showSnackBar) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    action = {
                        IconButton(onClick = { showSnackBar = false })
                        {
                            Icon(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    },
                ) {
                    Text(text = msg.toString(), color = white)
                }
            }
        }
    }
}


@Composable
@Preview
fun LoginScreenPreview(){
    val navController = rememberNavController()
    LearningPackageEditorTheme {
//        LoginScreen(onNavigateToSignUp = {
//            navController.navigate(Screens.signUpScreen.route)
//        },
//            onNavigateToCompletePackagesScreen = {
//                navController.navigate(Screens.PackageScreen.route)
//            })
    }
}
