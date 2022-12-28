package com.example.testmornhouse.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testmornhouse.databinding.FragmentFactAboutNumberBinding

class FactAboutNumberFragment : Fragment() {

    companion object {
        fun newInstance() = FactAboutNumberFragment()
    }


    private lateinit var binding: FragmentFactAboutNumberBinding

    private var givenNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments

        givenNumber = bundle?.getString("NUMBER").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFactAboutNumberBinding.inflate(inflater)
        binding.tvGivenNumber.text = givenNumber

        return binding.root
    }
}