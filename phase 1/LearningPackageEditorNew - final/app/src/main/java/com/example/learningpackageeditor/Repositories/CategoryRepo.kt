package com.example.learningpackageeditor.Repositories

import android.content.Context
import com.example.learningpackageeditor.model.LearningPackage
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


@Serializable

object CategoryRepo {

    var categoryList = mutableListOf<String>()


    fun getCategory(context: Context): List<String> {
        if (categoryList.isEmpty()) {
            // read the stadium.json data from the assets folder
            val jsonTextOfTheFile = context
                .assets
                .open("category.json")
                .bufferedReader()
                .use { it.readText() }

            // decode them to Stadium objects
            categoryList = Json { ignoreUnknownKeys = true }
                .decodeFromString(jsonTextOfTheFile)
        }
        return categoryList
    }


}


fun main() {
    val categoryList = mutableListOf<String>(
        "Food ", "Places", "Travel",
        "Sports", "Business", "Health and Medical", "Nature and Environment", "Art and Culture"
    )

        val filePath = "data/category.json"

// Convert the User object to a JSON string
    val jsonString = Json.encodeToString(categoryList)

// Write the JSON string to a file
    File(filePath).writeText(jsonString)
}