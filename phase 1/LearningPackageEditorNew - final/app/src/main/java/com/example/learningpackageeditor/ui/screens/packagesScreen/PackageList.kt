package com.example.learningpackageeditor.ui.screens.packagesScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningpackageeditor.model.Keywords
import com.example.learningpackageeditor.model.LearningPackage
import com.example.learningpackageeditor.model.Sentence
import com.example.learningpackageeditor.model.Word
import com.example.learningpackageeditor.ui.theme.LearningPackageEditorTheme
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.lightBlue
import com.example.learningpackageeditor.ui.theme.white
import java.util.Date

@Composable
fun PackageCard(
    learningP : LearningPackage,
    modifier: Modifier = Modifier,
    onDelete: (LearningPackage)-> Unit,
    onNavigateToEdit: (LearningPackage) -> Unit
){
    Surface(color = white) {
        ElevatedCard(
            modifier = Modifier
                .padding(5.dp)
                .shadow(15.dp)
                .clickable { onNavigateToEdit(learningP) },
            colors = CardDefaults.cardColors(
                containerColor = darkBlue,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 15.dp
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(start = 40.dp, end = 15.dp, bottom = 30.dp,top = 15.dp),
                //horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    modifier = modifier
                        .clickable {onDelete(learningP)}
                        .padding(end = 2.dp)
                        .align(Alignment.End),
                    contentDescription = "remove",
                    tint = white
                )
                Text(
                    text = learningP.title,
                    style = MaterialTheme.typography.displayLarge,
                )
                Text(
                    text = "Level: ${learningP.level}",
                    style = MaterialTheme.typography.displayMedium,
                )
                Text(
                    text = "Author Id: ${learningP.author.id.toString()}",
                    modifier = modifier,
                    style = MaterialTheme.typography.displayMedium,
                )
                Row(modifier = modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    horizontalArrangement = Arrangement.End){

                    Icon(
                        imageVector = Icons.Default.Edit,
                        modifier = modifier
                            .clickable{onNavigateToEdit(learningP)}, //EDIT THE PACKAGE HERE
                        contentDescription = "edit",
                        tint = white
                    )
                }
            }
        }
    }
}

@Composable
fun PackageCardList(
    modifier: Modifier = Modifier,
    packages: List<LearningPackage>,
    delete: (LearningPackage) -> Unit,
    navigateToEdit: (LearningPackage) -> Unit,
) {
    LazyColumn(
        modifier = modifier.padding(10.dp),
    ) {
        items(packages) { learningPackage ->
            PackageCard(
                learningP = learningPackage,
                onDelete = delete,
                onNavigateToEdit = navigateToEdit
            )
        }
    }
}

//@Composable
//@Preview
//fun PackageCardListPreview(){
//    var s1 = Sentence("I am a sentence.")
//    var s2 = Sentence("I am a sentence.")
//    var kword = Keywords("I am a keyword")
//    val word1 = Word(
//        text = "ExampleWord",
//        definitions = mutableListOf("Definition 1", "Definition 2"),
//        sentences = mutableListOf(s1, s2),
//        //resources = mutableListOf("Resource 1", "Resource 2")
//    )
//    val word2 = Word(
//        text = "ExampleWord",
//        definitions = mutableListOf("Definition 1", "Definition 2"),
//        sentences = mutableListOf(s1, s2),
//        //resources = mutableListOf("Resource 1", "Resource 2")
//    )
//
//    val lp =  LearningPackage(
//        packageId = "1",
//        author = "John Doe",
//        category = "Education",
//        description = "This is a sample learning package.",
//        iconUrl = "https://example.com/package-icon.png",
//        keywords = mutableListOf("learning", "education", "sample"),
//        language = "English",
//        lastUpdatedDate = Date(),
//        level = "Intermediate",
//        title = "Sample Learning Package",
//        version = 1,
//        words = mutableListOf(word1, word2))
//
//
//    val lp2 =  LearningPackage(
//        packageId = "1",
//        author = "John Doe",
//        category = "Education",
//        description = "This is a sample learning package.",
//        iconUrl = "https://example.com/package-icon.png",
//        keywords = mutableListOf("learning", "education", "sample"),
//        language = "English",
//        lastUpdatedDate = Date(),
//        level = "Intermediate",
//        title = "Sample Learning Package",
//        version = 1,
//        words = mutableListOf(word1, word2)
//    )
//
//    LearningPackageEditorTheme {
//        //PackageCardList(packages = mutableListOf(lp, lp2))
//    }
//}
//
//@Composable
//@Preview
//fun PackageCardPreview(){
//    var s1 = Sentence("I am a sentence.")
//    var s2 = Sentence("I am a sentence.")
//    var kword = Keywords("I am a keyword")
//
//    val word1 = Word(
//        text = "ExampleWord",
//        definitions = mutableListOf("Definition 1", "Definition 2"),
//        sentences = mutableListOf(s1,s2),
//        //resources = mutableListOf("Resource 1", "Resource 2")
//    )
//    val word2 = Word(
//        text = "ExampleWord",
//        definitions = mutableListOf("Definition 1", "Definition 2"),
//        sentences = mutableListOf(s1,s2),
//        //resources = mutableListOf("Resource 1", "Resource 2")
//    )
//
//   val lp =  LearningPackage(
//        packageId = "1",
//        author = "John Doe",
//        category = "Education",
//        description = "This is a sample learning package.",
//        iconUrl = "https://example.com/package-icon.png",
//        keywords = mutableListOf("learning", "education", "sample"),
//        language = "English",
//        lastUpdatedDate = Date(),
//        level = "Intermediate",
//        title = "Sample Learning Package",
//        version = 1,
//        words = mutableListOf(word1, word2)
//    )
//    LearningPackageEditorTheme {
//        //PackageCard(lp)
//    }
//}