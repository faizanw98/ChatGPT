package com.devfaiz.chatgpt.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.devfaiz.chatgpt.Chat
import com.devfaiz.chatgpt.ChatAdapter
import com.devfaiz.chatgpt.R
import com.devfaiz.chatgpt.databinding.FragmentSearchBinding
import java.lang.NullPointerException

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
        binding.lifecycleOwner = this
        val text = binding.textInput.text
        val chats = viewModel.chats
        val adapter = ChatAdapter(chats)
        binding.recycleView.adapter = adapter
        binding.recycleView.layoutManager = LinearLayoutManager(context)
        binding.textInputLayout.setEndIconOnClickListener {
                    viewModel.setChat(text.toString())
                    adapter.notifyItemInserted(chats.size-1)
                }

        return binding.root
    }
}


