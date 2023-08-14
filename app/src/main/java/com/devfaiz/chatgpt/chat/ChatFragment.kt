package com.devfaiz.chatgpt.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devfaiz.chatgpt.Chat
import com.devfaiz.chatgpt.ChatAdapter
import com.devfaiz.chatgpt.R
import com.devfaiz.chatgpt.databinding.FragmentSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class SearchFragment : Fragment() {
    private lateinit var viewModel: ChatViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSearchBinding = inflate(
            inflater, R.layout.fragment_search, container, false)
        viewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val text = binding.textInput.text
        val chats = viewModel.chats
        val adapter = ChatAdapter(chats.value!!)
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = LinearLayoutManager(context)
        viewModel.state.observe(viewLifecycleOwner){it->
            dataUpdated(chats, adapter)
            binding.recycleView.smoothScrollToPosition(adapter.itemCount)
        }
        binding.textInputLayout.setEndIconOnClickListener {
            viewModel.setChat(text.toString(),0)
            viewModel.response(text.toString())
            dataUpdated(chats,adapter)
            binding.recycleView.scheduleLayoutAnimation()
            text?.clear()
        }

        return binding.root
    }
    private fun dataUpdated(chats:LiveData<MutableList<Chat>>, adapter: ChatAdapter){
        adapter.notifyItemInserted(chats.value!!.size-1)
        CoroutineScope(Dispatchers.IO).run {
            adapter.notifyDataSetChanged()
        }
    }
}


