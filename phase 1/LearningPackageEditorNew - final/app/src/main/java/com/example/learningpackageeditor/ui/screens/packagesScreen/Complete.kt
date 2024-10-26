package com.example.learningpackageeditor.ui.screens.packagesScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.learningpackageeditor.Repositories.PackagesRepo
import com.example.learningpackageeditor.Repositories.PackagesRepo.packagesList
import com.example.learningpackageeditor.Repositories.PackagesRepo.removePacakge
import com.example.learningpackageeditor.model.Keywords
import com.example.learningpackageeditor.model.LearningPackage
import com.example.learningpackageeditor.model.Sentence
import com.example.learningpackageeditor.model.User
import com.example.learningpackageeditor.model.AppViewModel
import com.example.learningpackageeditor.model.Word
import com.example.learningpackageeditor.ui.theme.LearningPackageEditorTheme
import com.example.learningpackageeditor.ui.theme.backgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompletePackagesScreen(
    modifier: Modifier = Modifier,
    appViewModel: AppViewModel,
    onEditPackage: (LearningPackage) -> Unit,
    onAddPackage: () -> Unit,
){
    val navController = rememberNavController()

//    var packages by rememberSaveable {
//        mutableStateOf(packagesList)
//    }

    var packages = PackagesRepo.getPackages(LocalContext.current)
    packages.toMutableList()

    var isErrorAlertVisible by remember { mutableStateOf(false) }
    var confirmDelete by remember { mutableStateOf(false) }
    var packageToDelete by remember { mutableStateOf<LearningPackage?>(null) }

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopBar(
                filterBySearch = {query -> packages = PackagesRepo.filteredPackages(query).toMutableList()},
                filterByMine = { packages = PackagesRepo.getMyPackages(appViewModel).toMutableList() },
                getAll = {packages = PackagesRepo.getAllPackages().toMutableList() }
            ) },
        content = { PackageCardList(
            packages = packages,
            modifier = modifier.padding(it),
            delete = { p->
                if(p.author.id==appViewModel.currentUser.value.id) {
                    confirmDelete = true
                    packageToDelete = p
                }
                else{ isErrorAlertVisible = true }
            },
            navigateToEdit = {p->
                if(p.author.id==appViewModel.currentUser.value.id) {
                    appViewModel.currentPackage.value = p
                    onEditPackage(p)
                    packages = packagesList
                }
                else{
                    isErrorAlertVisible = true
                }
            }
        ) },
        floatingActionButton = {
            BottomBar(navController = navController){
                onAddPackage()
            }
        }
    )
    if (isErrorAlertVisible) {
        AlertDialog(
            onDismissRequest = { isErrorAlertVisible = false },
            title = { Text("Unauthorized Action") },
            text = { Text("You are unable to delete or edit a package that wasn't made by you.") },
            confirmButton = {
                TextButton(onClick = { isErrorAlertVisible = false }) {
                    Text("OK")
                }
            }
        )
    }
    if (confirmDelete) {
        AlertDialog(
            onDismissRequest = { confirmDelete = false },
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to delete this package?") },
            confirmButton = {
                TextButton(onClick = {
                    confirmDelete = false
//                    packages.remove(packageToDelete)
                    packageToDelete?.let { removePacakge(it) }
                    packagesList.remove(packageToDelete)
                }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    confirmDelete = false
                    packageToDelete = null
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
@Preview
fun CompletePackagesScreenPreview(){
    val navController = rememberNavController()

    var s1 = Sentence("I am a sentence.", mutableListOf())
    var s2 = Sentence("I am a sentence.", mutableListOf())

    var kword = Keywords("I am a keyword")

    var u = User("roaa@xyz.com", "yagdt67", "246")
    var u2 = User("roaa@xyz.com", "yagdt67", "123")

    val word1 = Word(
        text = "ExampleWord",
        definitions = mutableListOf("Definition 1", "Definition 2"),
        sentences = mutableListOf(s1, s2),
        //resources = mutableListOf("Resource 1", "Resource 2")
    )
    val word2 = Word(
        text = "ExampleWord",
        definitions = mutableListOf("Definition 1", "Definition 2"),
        sentences = mutableListOf(s1, s2),
        //resources = mutableListOf("Resource 1", "Resource 2")
    )

    val lp =  LearningPackage(
//        packageId = "1",
        author = u,
        category = "Education",
        description = "This is a sample learning package.",
        iconUrl = "https://example.com/package-icon.png",
        keywords = mutableListOf("learning", "education", "sample"),
        language = "English",
        lastUpdatedDate = "20-10-2023",
        level = "Intermediate",
        title = "Sample Learning Package",
        version = 1,
        words = mutableListOf(word1, word2),
    )

    val lp2 =  LearningPackage(
//        packageId = "1",
        author = u2,
        category = "Education",
        description = "This is a sample learning package.",
        iconUrl = "https://example.com/package-icon.png",
        keywords = mutableListOf("English", "grammar", "sample"),
        language = "English",
        lastUpdatedDate = "",
        level = "Beginner",
        title = "Sample Learning Package",
        version = 1,
        words = mutableListOf(word1, word2),
    )

    val lp3 =  LearningPackage(
//        packageId = "1",
        author = u2,
        category = "Education",
        description = "This is a sample learning package.",
        iconUrl = "https://example.com/package-icon.png",
        keywords = mutableListOf("English", "grammar", "sample"),
        language = "English",
        lastUpdatedDate = "",
        level = "Beginner",
        title = "Sample Learning Package",
        version = 1,
        words = mutableListOf(word1, word2)
    )

    val lp4 =  LearningPackage(
//        packageId = "1",
        author = u2,
        category = "Education",
        description = "This is a sample learning package.",
        iconUrl = "https://example.com/package-icon.png",
        keywords = mutableListOf("English", "grammar", "sample"),
        language = "English",
        lastUpdatedDate = "",
        level = "Beginner",
        title = "Sample Learning Package",
        version = 1,
        words = mutableListOf(word1, word2),
    )
    val lp5 =  LearningPackage(
//        packageId = "1",
        author = u2,
        category = "Education",
        description = "This is a sample learning package.",
        iconUrl = "https://example.com/package-icon.png",
        keywords = mutableListOf("English", "grammar", "sample"),
        language = "English",
        lastUpdatedDate = "",
        level = "Beginner",
        title = "Sample Learning Package",
        version = 1,
        words = mutableListOf(word1, word2),
    )

    PackagesRepo.packagesList.add(lp)
    PackagesRepo.packagesList.add(lp2)
    PackagesRepo.packagesList.add(lp3)
    PackagesRepo.packagesList.add(lp4)
    PackagesRepo.packagesList.add(lp5)

    LearningPackageEditorTheme {
        //CompletePackagesScreen(user = u)
    }
}

//@Composable
//@Preview
//fun CompletePackagesScreenPreview(){
//    val navController = rememberNavController()
//
//    var s1 = Sentence("I am a sentence.")
//    var s2 = Sentence("I am a sentence.")
//
//    var kword = Keywords("I am a keyword")
//
//    var u = User("roaa@xyz.com", "yagdt67", "246")
//    var u2 = User("roaa@xyz.com", "yagdt67", "123")
//
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
////        packageId = "1",
//        author = u,
//        category = "Education",
//        description = "This is a sample learning package.",
//        iconUrl = "https://example.com/package-icon.png",
//        keywords = mutableListOf("learning", "education", "sample"),
//        language = "English",
//        lastUpdatedDate = Date(),
//        level = "Intermediate",
//        title = "Sample Learning Package",
//        version = 1,
//        words = mutableListOf(word1, word2),
//    )
//
//    val lp2 =  LearningPackage(
////        packageId = "1",
//        author = u2,
//        category = "Education",
//        description = "This is a sample learning package.",
//        iconUrl = "https://example.com/package-icon.png",
//        keywords = mutableListOf("English", "grammar", "sample"),
//        language = "English",
//        lastUpdatedDate = Date(),
//        level = "Beginner",
//        title = "Sample Learning Package",
//        version = 1,
//        words = mutableListOf(word1, word2),
//    )
//
//    val lp3 =  LearningPackage(
////        packageId = "1",
//        author = u2,
//        category = "Education",
//        description = "This is a sample learning package.",
//        iconUrl = "https://example.com/package-icon.png",
//        keywords = mutableListOf("English", "grammar", "sample"),
//        language = "English",
//        lastUpdatedDate = Date(),
//        level = "Beginner",
//        title = "Sample Learning Package",
//        version = 1,
//        words = mutableListOf(word1, word2)
//    )
//
//    val lp4 =  LearningPackage(
////        packageId = "1",
//        author = u2,
//        category = "Education",
//        description = "This is a sample learning package.",
//        iconUrl = "https://example.com/package-icon.png",
//        keywords = mutableListOf("English", "grammar", "sample"),
//        language = "English",
//        lastUpdatedDate = Date(),
//        level = "Beginner",
//        title = "Sample Learning Package",
//        version = 1,
//        words = mutableListOf(word1, word2),
//    )
//    val lp5 =  LearningPackage(
////        packageId = "1",
//        author = u2,
//        category = "Education",
//        description = "This is a sample learning package.",
//        iconUrl = "https://example.com/package-icon.png",
//        keywords = mutableListOf("English", "grammar", "sample"),
//        language = "English",
//        lastUpdatedDate = Date(),
//        level = "Beginner",
//        title = "Sample Learning Package",
//        version = 1,
//        words = mutableListOf(word1, word2),
//    )
//
//    PackagesRepo.packagesList.add(lp)
//    PackagesRepo.packagesList.add(lp2)
//    PackagesRepo.packagesList.add(lp3)
//    PackagesRepo.packagesList.add(lp4)
//    PackagesRepo.packagesList.add(lp5)
//
//    LearningPackageEditorTheme {
//        //CompletePackagesScreen(user = u)
//    }
//}
