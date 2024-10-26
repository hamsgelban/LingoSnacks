package com.example.learningpackageeditor.ui.screens.authentication

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.learningpackageeditor.Repositories.UserRepo
import com.example.learningpackageeditor.model.User
import com.example.learningpackageeditor.model.AppViewModel
import com.example.learningpackageeditor.ui.theme.LearningPackageEditorTheme
import com.example.learningpackageeditor.ui.theme.backgroundColor
import com.example.learningpackageeditor.ui.theme.buttonsColor
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.lightBlue
import com.example.learningpackageeditor.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(
    appViewModel: AppViewModel,
    onNavigateToLoginScreen:  () -> Unit){

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    val customTextFieldDefaults = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = darkBlue,
        unfocusedBorderColor = darkBlue,
    )

    var showSnackBar by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    var msg by remember {
        mutableStateOf("")
    }

    Surface(color = backgroundColor, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(45.dp)
                .verticalScroll(state = scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {
            Text(
                text = "Sign-up",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(20.dp)
            )
            Text(
                text = "First Name",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 15.dp, start = 15.dp)
            )
            TextField(
                value = firstName,
                onValueChange = {firstName = it},
                modifier = Modifier,
                colors = customTextFieldDefaults
            )
            Text(
                text = "Last Name",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 15.dp, start = 15.dp)
            )
            TextField(
                value = lastName,
                onValueChange = {lastName = it},
                modifier = Modifier,
                colors = customTextFieldDefaults
            )
            Text(
                text = "Email",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 15.dp, start = 15.dp)
            )
            TextField(
                value = email,
                onValueChange = {email = it},
                modifier = Modifier,
                colors = customTextFieldDefaults
            )
            Text(
                text = "Password",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 15.dp, start = 15.dp)
            )
            TextField(
                value = password,
                onValueChange = {password = it},
                modifier = Modifier,
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
                onClick = {
                    val user = User(
                        firstName,
                        lastName,
                        email,
                        password
                    )
                    msg = UserRepo.addUser(user = user, appViewModel = appViewModel)
                    if (msg == "User added") {
                        appViewModel.currentUser.value = user
                        onNavigateToLoginScreen()
                    }
                    showSnackBar = true
                }
            ) {
                Text(
                    text = "Sign up",
                    color = darkBlue
                )
            }

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
                    Text(text = msg, color = white)
                }
            }
        }
    }
}


@Composable
@Preview
fun SignUpPreview(){
    LearningPackageEditorTheme {
        //SignUp(){}
    }
}