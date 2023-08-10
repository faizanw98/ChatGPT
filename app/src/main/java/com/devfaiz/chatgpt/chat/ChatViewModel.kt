package com.devfaiz.chatgpt.chat

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devfaiz.chatgpt.Chat

class ChatViewModel: ViewModel() {
    private var _chats: MutableList<Chat>
    val chats: List<Chat>
        get() = _chats
    init {
        _chats= mutableListOf()
    }

    fun setChat(input: String){
        val text = Chat(input)
        _chats.add(text)
    }
}
