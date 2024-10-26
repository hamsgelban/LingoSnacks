package com.example.learningpackageeditor.ui.UIcomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.learningpackageeditor.ui.navHost.Screens
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.white

@Composable
fun BackwardButton(navController: NavHostController, currentScreen: String) {
    Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Surface(color = darkBlue, modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(darkBlue),
                    border = BorderStroke(1.dp, darkBlue),
                    shape = RoundedCornerShape(10.dp),
                    contentPadding = PaddingValues(2.dp),
                    modifier = Modifier
                        .size(35.dp)
                        .padding(5.dp),
                    onClick = {when (currentScreen) {
                        "EditAddPackage" -> navController.navigate(Screens.PackageScreen.route)
                        "EditAddWord" -> navController.navigate(Screens.EditAddPackageScreen.route)
                        "ResourcesScreen" -> navController.navigate(Screens.AddWordScreen.route)

                    }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "add",
                        tint = white,
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
                Text(text = "Learning Package Editor App", color = white, fontSize = 8.sp)
            }
        }
    }
}
