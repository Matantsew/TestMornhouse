package com.example.testmornhouse.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.testmornhouse.databinding.FragmentFactAboutNumberBinding

class FactAboutNumberFragment : Fragment() {

    companion object {

        const val TAG = "FactAboutNumberFragment"

        const val NUMBER_ARG = "NUMBER"
        const val DESCRIPTION_ARG = "DESCRIPTION"

        fun newInstance() = FactAboutNumberFragment()
    }


    private lateinit var binding: FragmentFactAboutNumberBinding

    private var givenNumber = ""
    private var description = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments

        givenNumber = bundle?.getString(NUMBER_ARG).toString()
        description = bundle?.getString(DESCRIPTION_ARG).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFactAboutNumberBinding.inflate(inflater)
        binding.tvGivenNumber.text = givenNumber
        binding.tvDescription.text = description

        return binding.root
    }
}