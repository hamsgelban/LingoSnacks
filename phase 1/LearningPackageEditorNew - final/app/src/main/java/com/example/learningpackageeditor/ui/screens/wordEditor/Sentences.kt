package com.example.learningpackageeditor.ui.screens.wordEditor

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningpackageeditor.model.Sentence
import com.example.learningpackageeditor.ui.UIcomponents.EditRemoveItem
import com.example.learningpackageeditor.ui.UIcomponents.TextFieldWithAddButton
import com.example.learningpackageeditor.ui.theme.LearningPackageEditorTheme
import com.example.learningpackageeditor.ui.theme.darkBlue

@Composable
fun AddSentence(onAdd: (Sentence) -> Unit){
    TextFieldWithAddButton(value = "", onClick = {onAdd(Sentence(it, mutableListOf()))})
}

@Composable
fun EditRemoveSentence(sentence: Sentence, onAddResources: (Sentence) -> Unit,onDelete: (Sentence) -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically) {
        EditRemoveItem(value = sentence.text, onDelete = { onDelete(sentence) })
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "addResources",
            tint = darkBlue,
            modifier = Modifier
                .size(20.dp)
                .clickable { onAddResources(sentence) },
        )
    }
}

@Composable
fun EditRemoveSentenceList(sentenceList: List<Sentence>, onAddResources: (Sentence)-> Unit, delete: (Sentence) -> Unit){
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
            items(sentenceList) { it ->
                EditRemoveSentence(sentence = it, onAddResources, delete)
            }
        }
    }
}

@Composable
@Preview
fun EditRemoveSentencePreview(){
    LearningPackageEditorTheme {
        EditRemoveSentence(sentence = Sentence("", mutableListOf()), onAddResources = {}, onDelete = {})
    }
}

@Composable
@Preview
fun EditAddSentenceListPreview(){
    LearningPackageEditorTheme {
        EditRemoveSentenceList(
            sentenceList = mutableListOf(Sentence("sentence", mutableListOf()), Sentence("sentence", mutableListOf())),
            onAddResources = {},
            delete = {}
        )
    }
}

@Composable
@Preview
fun AddSentencePreview(){
    LearningPackageEditorTheme {
        AddSentence(){

        }
    }
}
