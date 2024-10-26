package com.example.learningpackageeditor.ui.screens.packageEditor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
fun EditRemoveKeyword(
    keyword: String,
    modifier: Modifier= Modifier,
    onDeleteKeyword: (String) -> Unit
){

    var thisKeyword by remember { mutableStateOf(keyword)}

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
            ) {
                BasicTextField(
                    value = thisKeyword,
                    onValueChange = {thisKeyword = it},
                    textStyle = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(start = 25.dp, end = 5.dp)
                        .height(30.dp)
                        .border(1.dp, darkBlue, RoundedCornerShape(4.dp))
                        .background(white),
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete",
                    tint = darkBlue,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { onDeleteKeyword(keyword) }
                )
            }
}


@Composable
fun EditAddKeywordList(
    modifier: Modifier= Modifier,
    keywords: List<String>,
    onAdd: (String) -> Unit,
    deleteKeyword: (String) -> Unit
){

    var newKeyword by remember { mutableStateOf("" )}

    val basicTextFieldModifier = Modifier
        .padding(end = 5.dp)
        .width(150.dp)
        .height(30.dp)
        .border(1.dp, darkBlue, RoundedCornerShape(4.dp))
        .background(white)

    Column(horizontalAlignment = Alignment.Start){
        Row(
            modifier = Modifier
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            BasicTextField(
                value = newKeyword,
                onValueChange = {newKeyword = it},
                textStyle = MaterialTheme.typography.bodySmall,
                modifier = basicTextFieldModifier,
            )
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(white),
                border = BorderStroke(1.dp, darkBlue),
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(2.dp),
                modifier = Modifier
                    .size(35.dp)
                    .padding(5.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 8.dp),
                onClick = {
                    onAdd(newKeyword)
                    newKeyword = ""
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add",
                    tint = darkBlue,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        }
        Row(
            modifier = Modifier.height(70.dp)
        ) {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2)
            ) {
                items(keywords){
                    EditRemoveKeyword(keyword = it, onDeleteKeyword = deleteKeyword, modifier = modifier.fillMaxSize())
                }
            }
        }

    }
}
