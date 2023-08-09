package com.devfaiz.chatgpt.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devfaiz.chatgpt.R
import com.devfaiz.chatgpt.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSearchBinding = inflate(
            inflater, R.layout.fragment_search, container, false)
        val viewModel: ViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        return binding.root
    }
}


