package com.example.learningpackageeditor.ui.screens.packagesScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.learningpackageeditor.ui.theme.LearningPackageEditorTheme
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.lightBlue
import com.example.learningpackageeditor.ui.theme.white

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onAddPackage: () -> Unit// Callback to add a new package
) {
    Row(modifier = modifier) {
        ElevatedButton(
            onClick ={
                onAddPackage()
                //navController.navigate("EditPackage")
                     }, // Call the addpackege when the button is clicked
            colors = ButtonDefaults.buttonColors(
                containerColor = white,
                contentColor = darkBlue
            ),
            border = BorderStroke(1.dp, darkBlue),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
        ) {
            Text(text = "Add a Package")
        }
    }
}


//@Composable
//@Preview
//fun BottomBarPreview(){
//    val navController = rememberNavController()
//    LearningPackageEditorTheme {
//        BottomBar(navController=navController)
//    }
//}