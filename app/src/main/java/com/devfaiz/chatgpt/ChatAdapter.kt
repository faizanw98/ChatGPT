package com.devfaiz.chatgpt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private val VIEW_TYPE_MESSAGE_SENT:Int = 1
private val VIEW_TYPE_MESSAGE_RECEIVED:Int = 2
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
        val view:View = if (viewType == VIEW_TYPE_MESSAGE_SENT){
            LayoutInflater.from(parent.context).inflate(R.layout.sent_messages,parent,false)
        }else if(viewType == VIEW_TYPE_MESSAGE_RECEIVED){
            LayoutInflater.from(parent.context).inflate(R.layout.recieved_messages,parent,false)
        } else {
            throw IllegalArgumentException("Invalid View Type")
        }
        return ChatViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        val chat = chats[position]
        if(chat.type == 0){
            return VIEW_TYPE_MESSAGE_SENT
        }else if(chat.type == 1){
            return VIEW_TYPE_MESSAGE_RECEIVED
        }
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return chats.size
    }
    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.chatView.text = chats[position].text

    }
}
