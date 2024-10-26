package com.example.learningpackageeditor.ui.screens.packageEditor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningpackageeditor.Repositories.CategoryRepo
import com.example.learningpackageeditor.Repositories.PackagesRepo
import com.example.learningpackageeditor.ui.theme.LearningPackageEditorTheme
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.white

@Composable
fun CategoryDropDown(selectedCategory: String, onFilterAndCategorySelected: (String) -> Unit) {
    var categoryOptions = CategoryRepo.getCategory(LocalContext.current)
    var showMenu by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, bottom = 15.dp)
    ) {
        Text(
            text = "Category",
            style = MaterialTheme.typography.displaySmall,
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 55.dp)
                .background(white)
                .border(
                    width = 1.dp,
                    color = darkBlue,
                    shape = RectangleShape
                )
        ) {
            Row {
                Text(text = selectedCategory, style = MaterialTheme.typography.displaySmall) // Display the selected category
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "drop down",
                    modifier = Modifier.clickable { showMenu = !showMenu }.size(30.dp),
                    tint = darkBlue
                )
            }

            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                categoryOptions.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it, style = MaterialTheme.typography.displaySmall) },
                        onClick = {
                            showMenu = false
                            onFilterAndCategorySelected(it)
                        }
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun CategoryDropDownPreview() {
    LearningPackageEditorTheme {
//        CategoryDropDown(){

    }
}