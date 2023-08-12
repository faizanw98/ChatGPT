package com.devfaiz.chatgpt.api

import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GptApi {
    @Headers(
        "Content-Type:application/json",
        "Authorization:Bearer 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")
    @POST("v1/completions")
   suspend fun sendMessage(@Body json: JsonObject): GptResponse

}
class Network{
private val chatGPTClient = RetrofitInstance.getInstance().create(GptApi::class.java)

suspend fun postResponse(jsonData : JsonObject) = chatGPTClient.sendMessage(jsonData)}