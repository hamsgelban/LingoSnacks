package com.example.learningpackageeditor.model

import kotlinx.serialization.Serializable

@Serializable
class Sentence(
    var text: String,
    var resources: List<Resource>
) {
}