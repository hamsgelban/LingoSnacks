package com.example.learningpackageeditor.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.Date
import kotlin.random.Random

@Serializable
data class LearningPackage(
    var author: User,
    var category: String,
    var description: String,
    var iconUrl: String? = null,
    var keywords: MutableList<String>,
    var language: String,
    @Contextual
    var lastUpdatedDate: String,
    var level: String,
    var title: String,
    var version: Int? = null,
    var words: MutableList<Word>
) {
    val packageId: String = generatePackageId()

    companion object {
        // Function to generate a package ID
        private fun generatePackageId(): String {
            val numericCharacters = List(4) { Random.nextInt(0, 10) }.joinToString("")
            return numericCharacters
        }
    }

    fun getPackageKeywords(): List<String> {
        return keywords
    }
}
