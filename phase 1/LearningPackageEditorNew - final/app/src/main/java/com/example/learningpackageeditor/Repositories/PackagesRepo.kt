package com.example.learningpackageeditor.Repositories

import android.content.Context
import com.example.learningpackageeditor.model.LearningPackage
import com.example.learningpackageeditor.model.Sentence
import com.example.learningpackageeditor.model.User
import com.example.learningpackageeditor.model.AppViewModel
import com.example.learningpackageeditor.model.Word
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
object PackagesRepo {

    var packagesList = mutableListOf<LearningPackage>()

    fun getPackages(context: Context): List<LearningPackage> {
        if (packagesList.isEmpty()) {
            // read the stadium.json data from the assets folder
            val jsonTextOfTheFile = context
                .assets
                .open("package.json")
                .bufferedReader()
                .use { it.readText() }


            packagesList = Json { ignoreUnknownKeys = true }
                .decodeFromString(jsonTextOfTheFile)
        }
        return packagesList
    }

    var wordsList = mutableListOf<Word>()

    var newWord = Word("", mutableListOf<String>(), mutableListOf<Sentence>())

    fun addPackage(
        lPackage: LearningPackage, //userViewModel: UserViewModel
    ) {
        packagesList.add(lPackage)
    }

    fun getPackage(lPackage: LearningPackage): LearningPackage? {
        return packagesList.find { it.packageId == lPackage.packageId }
    }

    fun filteredPackages(query: String) =
        if (query.isNotEmpty()) {

            packagesList.filter {

                it.keywords.any { keyword -> keyword.contains(query, ignoreCase = true) } ||
                        it.level.contains(query, ignoreCase = true) ||
                        it.category.contains(query, ignoreCase = true)

            }
        } else
            packagesList

    fun getMyPackages(appViewModel: AppViewModel) =
        packagesList.filter {
            it.author.id == appViewModel.currentUser.value.id
        }

    fun removePacakge(lPackage: LearningPackage){

        packagesList.remove(lPackage)

    }

    fun getAllPackages() = packagesList

    fun updatePackage(tobeUpdated: LearningPackage, updatedVersion: LearningPackage) {
        var p = packagesList.find { it.packageId == tobeUpdated.packageId }
        p = updatedVersion
    }


    fun getWordPackage(word: Word): List<Word>? {
        var foundWords: List<Word>? = null

        packagesList.forEach { learningPackage ->
            if (learningPackage.words.contains(word)) {
                foundWords = learningPackage.words
                return@forEach // Break out of the forEach loop when the word is found
            }
        }
        return foundWords
    }

    fun updateWord(updatedWord: Word, words: List<Word>): List<Word> {
        return words.map { if (it.text == updatedWord.text) updatedWord else it }
    }

    fun generatePackageId(): String {
        val id = java.util.UUID.randomUUID().toString()
        // Remove hyphens from the ID and all non-numeric characters
        val numericCharacters = id.replace("-", "")
            .filter { it.isDigit() }

        // Take the first 4 numeric characters
        return numericCharacters.take(4)
    }

    init {
        var s1 = Sentence("I am a sentence.", mutableListOf())
        var s2 = Sentence("I am a sentence.", mutableListOf())

        var u = User("roaa@xyz.com", "yagdt67", "27818437")

        val word1 = Word(
            text = "ExampleWord",
            definitions = mutableListOf("Definition 1", "Definition 2"),
            sentences = mutableListOf(s1, s2),
        )
        val word2 = Word(
            text = "ExampleWord",
            definitions = mutableListOf("Definition 1", "Definition 2"),
            sentences = mutableListOf(s1, s2),
        )

        val lp = LearningPackage(
            author = u,
            category = "Education",
            description = "This is a sample learning package.",
            iconUrl = "https://example.com/package-icon.png",
            keywords = mutableListOf("learning", "education", "sample"),
            language = "English",
            lastUpdatedDate = "",
            level = "Advanced",
            title = "Sample Learning Package",
            version = 1,
            words = mutableListOf(word1, word2),
        )

        var s3 = Sentence("I am a sentence.", mutableListOf())
        var s4 = Sentence("I am a sentence.", mutableListOf())

        var u1 = User("hams@xyz.com", "yagdt67", "27818438")

        val word3 = Word(
            text = "ExampleWord",
            definitions = mutableListOf("Definition 1", "Definition 2"),
            sentences = mutableListOf(s3, s4),
        )
        val word4 = Word(
            text = "ExampleWord",
            definitions = mutableListOf("Definition 1", "Definition 2"),
            sentences = mutableListOf(s3, s4),
        )

        val lp2 = LearningPackage(
            author = u,
            category = "Art and Culture",
            description = "This is a sample learning package.",
            iconUrl = "https://example.com/package-icon.png",
            keywords = mutableListOf("art", "education", "sample"),
            language = "English",
            lastUpdatedDate = "",
            level = "Advanced",
            title = "Art and Culture",
            version = 1,
            words = mutableListOf(word3, word4),
        )

        var s5 = Sentence("I am a sentence.", mutableListOf())
        var s6 = Sentence("I am a sentence.", mutableListOf())

        var u2 = User("hams@xyz.com", "yagdt67", "27818438")

        val word5 = Word(
            text = "ExampleWord",
            definitions = mutableListOf("Definition 1", "Definition 2"),
            sentences = mutableListOf(s3, s4),
        )
        val word6 = Word(
            text = "ExampleWord",
            definitions = mutableListOf("Definition 1", "Definition 2"),
            sentences = mutableListOf(s3, s4),
        )

        val lp3 = LearningPackage(
            author = u,
            category = "Travel",
            description = "This is a sample learning package.",
            iconUrl = "https://example.com/package-icon.png",
            keywords = mutableListOf("learning", "travel"),
            language = "English",
            lastUpdatedDate = "",
            level = "Advanced",
            title = "Traveling",
            version = 1,
            words = mutableListOf(word3, word4),
        )
        packagesList.add(lp)
        packagesList.add(lp2)
        packagesList.add(lp3)

    }
}
//    init {
//        var s1 = Sentence("I am a sentence.", mutableListOf( Resource("", "")))
//        //var s2 = Sentence("I am a sentence.")
//        val word1 = Word(
//            text = "ExampleWord",
//            definitions = mutableListOf("Definition 1", "Definition 2"),
//            sentences = mutableListOf(s1),
//        )
//        val word2 = Word(
//            text = "ExampleWord",
//            definitions = mutableListOf("Definition 1", "Definition 2"),
//            sentences = mutableListOf(s1),
//        )
//
//        val lp = LearningPackage(
//            author = u,
//            category = "Education",
//            description = "This is a sample learning package.",
//            iconUrl = "https://example.com/package-icon.png",
//            keywords = mutableListOf("learning", "education", "sample"),
//            language = "English",
//            lastUpdatedDate = Date(),
//            level = "Advanced",
//            title = "Sample Learning Package",
//            version = 1,
//            words = mutableListOf(word1, word2),
//        )
//        packagesList.add(lp)
//    }
//}

//fun main(args: Array<String>) {
//
//    var s1 = Sentence("I am a sentence.")
//    var s2 = Sentence("I am a sentence.")
//
//    var u = User("roaa@xyz.com", "yagdt67", "27818437")
//
//    val word1 = Word(
//        text = "ExampleWord",
//        definitions = mutableListOf("Definition 1", "Definition 2"),
//        sentences = mutableListOf(s1, s2),
//    )
//    val word2 = Word(
//        text = "ExampleWord",
//        definitions = mutableListOf("Definition 1", "Definition 2"),
//        sentences = mutableListOf(s1, s2),
//    )
//
//    val date =Date(2023, 10, 31)
//
//    val lp = LearningPackage(
//        author = u,
//        category = "Education",
//        description = "This is a sample learning package.",
//        iconUrl = "https://example.com/package-icon.png",
//        keywords = mutableListOf("learning", "education", "sample"),
//        language = "English",
//        lastUpdatedDate = "31-10-2023",
//        level = "Advanced",
//        title = "Sample Learning Package",
//        version = 1,
//        words = mutableListOf(word1, word2),
//    )
//
//    val filePath = "data/package.json"
//
//// Convert the User object to a JSON string
//    val jsonString = Json.encodeToString(lp)
//
//// Write the JSON string to a file
//    File(filePath).writeText(jsonString)
//}
