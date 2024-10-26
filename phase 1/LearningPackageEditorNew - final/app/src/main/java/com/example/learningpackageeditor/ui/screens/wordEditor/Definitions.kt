package com.example.learningpackageeditor.ui.screens.wordEditor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningpackageeditor.ui.UIcomponents.EditRemoveItem
import com.example.learningpackageeditor.ui.UIcomponents.TextFieldWithAddButton
import com.example.learningpackageeditor.ui.theme.LearningPackageEditorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDef(onAdd: (String) -> Unit){
    TextFieldWithAddButton(value = "", onClick = onAdd)
}

@Composable
fun EditRemoveDef(definition : String, onDelete: (String) -> Unit){
    EditRemoveItem(value = definition, onDelete = {onDelete(it)})
}

@Composable
fun EditRemoveDefList(defList: List<String>, delete: (String) -> Unit){
    Column(
        modifier = Modifier
            .height(100.dp)
            .padding(5.dp)
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(5.dp)
        ) {
            items(defList) { it ->
                EditRemoveDef(definition = it, delete)
            }
        }
    }
}

@Composable
@Preview
fun EditRemoveDefPreview(){
    LearningPackageEditorTheme {
        EditRemoveDef(definition = "This is a sample definition.", {})
    }
}