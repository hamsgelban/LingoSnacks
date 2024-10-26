package com.example.learningpackageeditor.ui.screens.packagesScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.learningpackageeditor.Repositories.PackagesRepo
import com.example.learningpackageeditor.ui.theme.LearningPackageEditorTheme
import com.example.learningpackageeditor.ui.theme.backgroundColor
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.lightBlue
import com.example.learningpackageeditor.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(modifier: Modifier = Modifier,
           filterBySearch: (String) -> Unit,
           filterByMine: () -> Unit,
           getAll: () -> Unit
           )
{

    var query by remember {
        mutableStateOf("")
    }

    var showMenu by remember {
        mutableStateOf(false)
    }

    Surface(
        color = backgroundColor,
    )
    {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .border(
                        width = 1.dp,
                        color = darkBlue, // Replace with your desired border color
                        shape = RoundedCornerShape(5.dp)
                    )
                    .shadow(15.dp),
                value = query,
                shape = RoundedCornerShape(5.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = darkBlue,
                    containerColor = white,
                    cursorColor = darkBlue,
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = darkBlue
                    )
                },
                onValueChange = {
                    query = it
                    filterBySearch(query)
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            modifier = Modifier.clickable {
                                query = ""
                                filterBySearch(query)
                            },
                            contentDescription = "clear",
                            tint = darkBlue
                        )
                    }
                }
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier.align(Alignment.CenterVertically)
            ) {
                if(showMenu) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "drop down",
                        modifier = modifier
                            .padding(2.dp)
                            .clickable { showMenu = false }
                            .size(40.dp),
                        tint = darkBlue
                    )
                }
                else{
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "drop down",
                        modifier = modifier
                            .padding(2.dp)
                            .clickable { showMenu = true }
                            .size(40.dp),
                        tint = darkBlue
                    )
                }

                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                    DropdownMenuItem(
                        text = {Text("My Packages")},
                        onClick = {
                            showMenu = false
                            filterByMine()
                        }
                    )
                    DropdownMenuItem(
                        text = {Text("All Packages")},
                        onClick = {
                            showMenu = false
                            getAll()
                        }
                    )
                }
            }
        }
    }
}
