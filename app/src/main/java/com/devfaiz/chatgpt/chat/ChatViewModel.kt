package com.devfaiz.chatgpt.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devfaiz.chatgpt.Chat
import com.devfaiz.chatgpt.api.GptText
import com.devfaiz.chatgpt.api.Network
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {
    private val network = Network()
    private val SENT_MESSAGE_STATE = 0
    private var RECEIVED_MESSAGE_STATE = 1
    private var _chats = MutableLiveData<MutableList<Chat>>()
    val chats: LiveData<MutableList<Chat>>
        get() = _chats
    init {
        _chats.value = mutableListOf()
    }

    fun postResponse(query : String) = viewModelScope.launch {
        val jsonObject: JsonObject = JsonObject().apply{
            addProperty("model", "text-davinci-003")
            addProperty("prompt", query)
            addProperty("temperature", 0)
            addProperty("max_tokens", 500)
            addProperty("top_p", 1)
            addProperty("frequency_penalty", 0.0)
            addProperty("presence_penalty", 0.0)
        }
         val response= network.postResponse(jsonObject)
        val gson = Gson()
        val tempjson = gson.toJson(response.choices.get(0))
        val tempgson = gson.fromJson(tempjson, GptText::class.java)
        println(response.toString())
        messageReceived(tempgson.text,RECEIVED_MESSAGE_STATE)
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
