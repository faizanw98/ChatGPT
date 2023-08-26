package com.devfaiz.chatgpt.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GptApi {
    @Headers(
        "Content-Type:application/json",
        "Authorization:Bearer <Your_API_Key>")
    @POST("v1/completions")
    fun sendMessage(@Body json: JsonObject): Call<GptResponse>

}
class Network{
     fun postResponse(jsonData : JsonObject) {
         val gptApi = RetrofitInstance.client.create(GptApi::class.java)
         gptApi.sendMessage(jsonData)
     }}
