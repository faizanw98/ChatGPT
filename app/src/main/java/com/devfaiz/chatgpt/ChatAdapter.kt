package com.devfaiz.chatgpt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(
    private var chats: List<Chat>
): RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {
    inner class ChatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val chatView: TextView
        init {
            chatView = itemView.findViewById(R.id.text_gchat_message_me)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view:View = if(chats[chats.size-1].type==0){
            LayoutInflater.from(parent.context).inflate(R.layout.sent_messages,parent,false)
        }else{
            LayoutInflater.from(parent.context).inflate(R.layout.recieved_messages,parent,false)
        }
        return ChatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chats.size
    }
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.chatView.text = chats[position].text

    }
}
