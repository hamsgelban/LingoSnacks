package com.example.learningpackageeditor.model

import kotlinx.serialization.Serializable

@Serializable
class Word(
    var text: String,
    var definitions: MutableList<String>,
    var sentences: MutableList<Sentence>,
) {

}