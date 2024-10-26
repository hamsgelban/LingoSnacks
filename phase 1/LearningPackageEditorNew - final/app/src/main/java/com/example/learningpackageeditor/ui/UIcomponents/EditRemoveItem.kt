package com.example.learningpackageeditor.ui.UIcomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditRemoveItem(
    value: String,
    onDelete: (String) -> Unit,
) {
    var input by remember {
        mutableStateOf(value)
    }
    val customTextFieldDefaults2 = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = darkBlue,
        containerColor = white
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(bottom = 2.dp)
    ) {
        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            modifier = Modifier
                .height(50.dp),
            colors = customTextFieldDefaults2,
            textStyle = TextStyle(fontSize = 10.sp)
        )
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            tint = darkBlue,
            modifier = Modifier
                .size(20.dp)
                .clickable { onDelete(input) }
        )
    }
}