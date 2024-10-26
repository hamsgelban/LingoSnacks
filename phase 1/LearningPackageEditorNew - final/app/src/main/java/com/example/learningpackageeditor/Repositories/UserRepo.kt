package com.example.learningpackageeditor.Repositories

import android.content.Context
import com.example.learningpackageeditor.model.User
import com.example.learningpackageeditor.model.AppViewModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.regex.Pattern

@Serializable
object UserRepo {

    var userList = mutableListOf<User>()


    fun getUsers(context: Context): List<User> {
        if (userList.isEmpty()) {
            // read the stadium.json data from the assets folder
            val jsonTextOfTheFile = context
                .assets
                .open("user.json")
                .bufferedReader()
                .use { it.readText() }

            // decode them to Stadium objects
            userList = Json { ignoreUnknownKeys = true }
                .decodeFromString(jsonTextOfTheFile)
        }
        return userList
    }

    //var userList = mutableListOf<User>()
    fun addUser(appViewModel: AppViewModel, user: User): String {
        if (!isEmailValid(user.email)) {
            return "Invalid email format"
        }

        if (!isPasswordValid(user.password)) {
            return "Invalid password format"
        }

        if(UserExistsByEmail(user.email, appViewModel)){
            return "User already exists"
        }

        appViewModel.userList.value.add(user)
        return "User added"
    }

    fun isEmailValid(email: String): Boolean {
        val pattern = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
        )
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }


    fun isPasswordValid(password: String): Boolean {
        val minLength = 8
        val containsUppercase = password.any { it.isUpperCase() }
        val containsLowercase = password.any { it.isLowerCase() }
        val containsDigit = password.any { it.isDigit() }
        val containsSpecialChar = password.any { it.isLetterOrDigit().not() }

        return (password.length >= minLength) &&
                containsUppercase &&
                containsLowercase &&
                containsDigit &&
                containsSpecialChar
    }


    fun UserExistsByEmail(email: String, appViewModel: AppViewModel): Boolean{
        return  appViewModel.userList.value.any { it.email == email }
    }

    fun getUser(email:String, password: String, appViewModel: AppViewModel) :Boolean{
        return appViewModel.userList.value.any { it.email == email && it.password==password }
    }

    fun generateUserId(): String {
        val id = java.util.UUID.randomUUID().toString()
        // Remove hyphens from the ID and all non-numeric characters
        val numericCharacters = id.replace("-", "")
            .filter { it.isDigit() }

        // Take the first 4 numeric characters
        return numericCharacters.take(8)
    }

    init {

        val user = User(
            fname = "Ahmed",
            lname = "Doe",
            email = "ahmed@example.com",
            password = "Secure@9",
        )

        val user2 = User(
            fname = "John",
            lname = "Dora",
            email = "johndoe@example.com",
            password = "Secure@2",
        )

        val user3 = User(
            fname = "hams",
            lname = "mohamed",
            email = "hams@xyz.com",
            password = "yagdt67",
        )
        val user4 = User(
            fname = "roaa",
            lname = "naim",
            email = "roaa@xyz.com",
            password = "yagdt87",
        )

        val user5 = User(
            fname = "shatha",
            lname = "saleh",
            email = "shatha@xyz.com",
            password = "yagdu67",
        )



        val users = listOf(user,user2,user3,user4,user5)

    }



}

fun main() {
    val user = User(
        fname = "Ahmed",
        lname = "Doe",
        email = "ahmed@example.com",
        password = "Secure@9",
    )

    val user2 = User(
        fname = "John",
        lname = "Dora",
        email = "johndoe@example.com",
        password = "Secure@2",
    )

    val users = listOf(user,user2)


// Define the file path where you want to save the JSON data
    val filePath = "data/user.json"

// Convert the User object to a JSON string
    val jsonString = Json.encodeToString(users)


// Write the JSON string to a file
    File(filePath).writeText(jsonString)

// The User object is now saved as a JSON file at the specified file path


//    println(userRepo.addUser(user))
//    println(userRepo.addUser(user2))
//    println(userRepo.addUser(user2))
//
//    userRepo.userList.forEach {
//        println(it)
//    }


}