package com.devfaiz.chatgpt.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devfaiz.chatgpt.Chat
import com.devfaiz.chatgpt.api.GptApi
import com.devfaiz.chatgpt.api.GptResponse
import com.devfaiz.chatgpt.api.GptText
import com.devfaiz.chatgpt.api.RetrofitInstance
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Response

class ChatViewModel: ViewModel() {
    var state= MutableLiveData<Boolean>()
    private val SENT_MESSAGE_STATE = 0
    private var RECEIVED_MESSAGE_STATE = 1
    private var _chats = MutableLiveData<MutableList<Chat>>()
    val chats: LiveData<MutableList<Chat>>
        get() = _chats
    init {
        _chats.value = mutableListOf()
        state.value = true
    }
    fun response(query: String) {
        val jsonObject: JsonObject = JsonObject().apply {
            addProperty("model", "text-davinci-003")
            addProperty("prompt", query)
            addProperty("temperature", 0)
            addProperty("max_tokens", 500)
            addProperty("top_p", 1)
            addProperty("frequency_penalty", 0.0)
            addProperty("presence_penalty", 0.0)
        }
        callResponse(jsonObject)
    }
    private fun callResponse(jsonObject: JsonObject) {
        val gptApi = RetrofitInstance.client.create(GptApi::class.java)
        val call: retrofit2.Call<GptResponse> = gptApi.sendMessage(jsonObject)

        call.enqueue(object : retrofit2.Callback<GptResponse> {
            override fun onResponse(
                call: retrofit2.Call<GptResponse>,
                response: Response<GptResponse>
            ) {
               val gson = Gson()
                val tempjson = gson.toJson(response.body()!!.choices.get(0))
                val tempgson = gson.fromJson(tempjson, GptText::class.java)
                messageReceived(tempgson.text,RECEIVED_MESSAGE_STATE)
                state.value = false
            }

            override fun onFailure(call: retrofit2.Call<GptResponse>, t: Throwable) {
                println("IOException")
            }
        })
    }
    private fun messageReceived(text: String, num:Int){
        _chats.value!!.add(Chat(text,num))
    }

    fun setChat(input: String, num:Int){
        if (num==0){
        val text = Chat(input,SENT_MESSAGE_STATE)
        _chats.value!!.add(text)
        }
    }

}
