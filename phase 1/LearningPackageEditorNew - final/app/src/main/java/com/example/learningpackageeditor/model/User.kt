package com.example.learningpackageeditor.model

import com.example.learningpackageeditor.Repositories.UserRepo
import kotlinx.serialization.Serializable

@Serializable
data class User(
    var fname: String,
    var lname: String,
    var email: String,
    var password: String,
    var id: String? = UserRepo.generateUserId()
) {
    constructor(email: String,
                password: String,
                id: String?) : this("", "", email, password, id)
}
