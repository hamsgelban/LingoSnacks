package com.example.learningpackageeditor.model

import android.net.UrlQuerySanitizer
import kotlinx.serialization.Serializable

@Serializable
class Resource(
    var resourceUrl: String,
    var type: String
) {

}