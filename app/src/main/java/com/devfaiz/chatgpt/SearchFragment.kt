package com.devfaiz.chatgpt

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import com.devfaiz.chatgpt.databinding.FragmentSearchBinding
private lateinit var binding: FragmentSearchBinding
class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
         binding = inflate(
            inflater, R.layout.fragment_search, container, false
        )


        binding.sendButton.setOnClickListener {

            hideKeyboard(requireContext(),binding.textBox)
        }
        return binding.root
    }
private fun hideKeyboard(context: Context, view: View){
val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}}


