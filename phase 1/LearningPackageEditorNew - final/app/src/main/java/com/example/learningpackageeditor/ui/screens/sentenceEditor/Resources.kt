package com.example.learningpackageeditor.ui.screens.sentenceEditor

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.learningpackageeditor.model.Resource
import com.example.learningpackageeditor.ui.UIcomponents.RadioButtonGroup
import com.example.learningpackageeditor.ui.theme.darkBlue
import com.example.learningpackageeditor.ui.theme.lightBlue
import com.example.learningpackageeditor.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResourceItem(resource: Resource){

    val radioOptions = listOf("Video", "Photo", "Website")

    var selectedTypeByIndex by rememberSaveable { mutableStateOf(radioOptions.indexOf(resource.type)) }
    var resourceText by rememberSaveable { mutableStateOf(resource.resourceUrl) }

    ElevatedCard(modifier = Modifier
        .padding(3.dp)
        .border(color = darkBlue, width = 1.dp, shape = RoundedCornerShape(5.dp)), colors = CardDefaults.cardColors(containerColor = lightBlue)) {
       Column(modifier = Modifier.padding(3.dp)){
           OutlinedTextField(value = resourceText, onValueChange = {resourceText=it}, modifier = Modifier
               .fillMaxWidth()
               .padding(3.dp))
           Row{
               RadioButtonGroup(
                   options = radioOptions,
                   selectedOptionIndex = selectedTypeByIndex,
                   onOptionSelected = { index, text ->
                       run {
                           selectedTypeByIndex = index
//                           level = text
                           //userViewModel.currentPackage.level=text
                       }
                   }
               )
           }
       }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddResourceItem(resource: Resource, onAddResource: (Resource) -> Unit){

    val radioOptions = listOf("Video", "Photo", "Website")

    var selectedTypeByIndex by rememberSaveable { mutableStateOf(radioOptions.indexOf(resource.type)) }
    var resourceText by rememberSaveable { mutableStateOf(resource.resourceUrl) }
    var resourceType by rememberSaveable { mutableStateOf(resource.type) }

    Row {
        ElevatedCard(modifier = Modifier
            .padding(3.dp),
            //.border(color = darkBlue, width = 1.dp, shape = RoundedCornerShape(5.dp)),
            ) {
            Column(modifier = Modifier.padding(3.dp)) {
                OutlinedTextField(
                    value = resourceText, onValueChange = { resourceText = it }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp)
                )
                Row {
                    RadioButtonGroup(
                        options = radioOptions,
                        selectedOptionIndex = selectedTypeByIndex,
                        onOptionSelected = { index, text ->
                            run {
                                selectedTypeByIndex = index
                                resourceType = text
                            }
                        }
                    )
                }
            }
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(white),
                border = BorderStroke(1.dp, darkBlue),
                shape = RoundedCornerShape(5.dp),
                contentPadding = PaddingValues(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 10.dp),
                onClick = {
                    onAddResource(Resource(resourceText, resourceType))
                    resourceText=""
                }
            ) {
                Row {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = darkBlue,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(text = "Add Resource", color = darkBlue)
                }
            }
        }
    }
}

@Composable
fun ResourceItemList(resources: List<Resource>, onAdd: (Resource) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(5.dp)
            .fillMaxWidth()

    ) {
        AddResourceItem(resource = Resource("", ""), onAddResource = onAdd)
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            contentPadding = PaddingValues(5.dp)
        ) {
            items(resources) { it ->
                ResourceItem(resource = it)
            }
        }
    }
}

//@Composable
//@Preview
//fun ResourceItemPreview(){
//    LearningPackageEditorTheme {
//        ResourceItem(Resource("thisIsAurl", "Type"))
//    }
//}

//@Composable
//@Preview
//fun ResourceItemListPreview(){
//    LearningPackageEditorTheme {
//        ResourceItemList(resources = listOf(Resource("thisIsAurl", "Type"), Resource("thisIsAurl", "Type"), Resource("thisIsAurl", "Type")))
//    }
//}