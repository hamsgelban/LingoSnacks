package com.example.learningpackageeditor.model

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.learningpackageeditor.Repositories.PackagesRepo

class AppViewModel(application: Application) : AndroidViewModel(application) {

    var userList = mutableStateOf(mutableListOf<User>())

    var currentUser= mutableStateOf( User("", "", ""))

    var currentPackage = mutableStateOf(PackagesRepo.getPackages(application)[0])

    var currentSentence = mutableStateOf(Sentence("", mutableListOf()))

    var currentResourcesList = mutableStateOf(mutableListOf<Resource>())

    var currentWord: Word = Word("", mutableListOf(), mutableListOf())

    var currentWordList = mutableStateListOf<Word>()

    fun getWord(text: String): Word? {
        return currentWordList.find { it.text == text }
    }

}


