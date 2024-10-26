package com.example.learningpackageeditor.ui.UIcomponents

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learningpackageeditor.ui.theme.buttonsColor
import com.example.learningpackageeditor.ui.theme.darkBlue

@Composable
fun SubmitButton(onClick: () -> Unit){
    ElevatedButton(
        modifier = Modifier
            .padding(vertical = 30.dp)
            .width(300.dp)
            .height(60.dp),
        colors = ButtonDefaults.elevatedButtonColors(containerColor = buttonsColor),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 10.dp),
        onClick = { onClick() }
    ) {
        Text(
            text = "Submit",
            color = darkBlue
        )
    }
}