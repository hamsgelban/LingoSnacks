package com.example.learningpackageeditor.ui.screens.packageEditor

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learningpackageeditor.model.Word
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.white

@Composable
fun EditAddWordButton(word: Word, onDelete: (Word) -> Unit, onEdit: (Word) -> Unit){
    Row(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
    )
    {
        Surface(
            color = darkBlue,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(3.dp).fillMaxSize()
            ) {
                Text(
                    text = word.text,
                    color = white,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "edit",
                    tint = white,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {onEdit(word)} //GO TO EDIT/ ADD WORD SCREEN AND PASS "EDIT"
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "delete",
                    tint = white,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {onDelete(word)} //REMOVE THE WORD
                )
            }
        }
    }
}

@Composable
fun EditAddWordList(words: List<Word>, onAdd: () -> Unit, edit: (Word) -> Unit, delete: (Word) -> Unit){
    Column(horizontalAlignment = Alignment.Start){
        Row(
            modifier = Modifier
                .padding(top = 25.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ){
            Text(
                text = "Words",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(end = 20.dp)
            )
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(white),
                border = BorderStroke(1.dp, darkBlue),
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(2.dp),
                modifier =  Modifier.size(25.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 10.dp),
                onClick = {
                    onAdd()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = darkBlue,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
        Row(
            modifier = Modifier.height(70.dp)
        ) {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(2),
            ) {
                items(words) {
                    EditAddWordButton(word = it, delete, edit)
                }
            }
        }
    }
}

//@Composable
//@Preview
//fun EditAddWordButtonPreview(){
//    var s = Sentence("I am a sentence.")
//    var s1 = Sentence("I am a sentence.")
//    val word1 = Word(
//        text = "ExampleWord",
//        definitions = mutableListOf("Definition 1", "Definition 2"),
//        sentences = mutableListOf(s1, s),
//        //resources = mutableListOf("Resource 1", "Resource 2")
//    )
//    LearningPackageEditorTheme {
//        //EditAddWordButton(word1)
//    }
//}
//
//@Composable
//@Preview
//fun EditAddWordListPreview(){
//    var s = Sentence("I am a sentence.")
//    var s1 = Sentence("I am a sentence.")
//    val word1 = Word(
//        text = "ExampleWord",
//        definitions = mutableListOf("Definition 1", "Definition 2"),
//        sentences = mutableListOf(s1, s),
//        //resources = mutableListOf("Resource 1", "Resource 2")
//    )
//    val word2 = Word(
//        text = "ExampleWord",
//        definitions = mutableListOf("Definition 1", "Definition 2"),
//        sentences = mutableListOf(s, s1),
//        //resources = mutableListOf("Resource 1", "Resource 2")
//    )
//
//    val word3 = Word(
//        text = "ExampleWord",
//        definitions = mutableListOf("Definition 1", "Definition 2"),
//        sentences = mutableListOf(s, s1),
//        //resources = mutableListOf("Resource 1", "Resource 2")
//    )
//    val word4 = Word(
//        text = "ExampleWord",
//        definitions = mutableListOf("Definition 1", "Definition 2"),
//        sentences = mutableListOf(s, s1),
//        //resources = mutableListOf("Resource 1", "Resource 2")
//    )
//    LearningPackageEditorTheme {
//        //EditAddWordList(mutableListOf(word1, word2, word3, word4))
//    }
//}