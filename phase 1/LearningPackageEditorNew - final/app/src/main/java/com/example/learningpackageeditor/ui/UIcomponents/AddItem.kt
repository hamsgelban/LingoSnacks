package com.example.learningpackageeditor.ui.UIcomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithAddButton(modifier: Modifier = Modifier, value: String, onClick: (String) -> Unit){
    var input by remember {
        mutableStateOf(value)
    }
    val customTextFieldDefaults2 = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = darkBlue,
        unfocusedBorderColor = darkBlue,
        containerColor = white
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            modifier = modifier
                .height(50.dp),
            colors = customTextFieldDefaults2,
            textStyle = MaterialTheme.typography.headlineSmall
        )
        ElevatedButton(
            colors = ButtonDefaults.buttonColors(darkBlue),
            border = BorderStroke(1.dp, darkBlue),
            shape = RoundedCornerShape(5.dp),
            contentPadding = PaddingValues(2.dp),
            modifier = Modifier
                .size(35.dp)
                .padding(5.dp),
            onClick = {
                onClick(input)
                input = ""
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add",
                tint = white,
                modifier = Modifier
                    .size(40.dp)
            )
        }
    }
}