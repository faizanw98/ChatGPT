package com.devfaiz.chatgpt.api

import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName

data class GptText(
    @SerializedName("text")
    val text : String
)
data class GptResponse (
    val choices : JsonArray

)