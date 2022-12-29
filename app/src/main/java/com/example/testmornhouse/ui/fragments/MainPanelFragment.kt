package com.example.testmornhouse.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testmornhouse.ui.IMainActivity
import com.example.testmornhouse.databinding.FragmentMainPanelBinding
import kotlin.random.Random

class MainPanelFragment : Fragment(), View.OnClickListener{

    companion object {
        fun newInstance() = MainPanelFragment()
    }

    private lateinit var mIMainActivity: IMainActivity

    private lateinit var binding: FragmentMainPanelBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mIMainActivity = activity as IMainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainPanelBinding.inflate(inflater)

        with(binding){
            btnGetNumFact.setOnClickListener(this@MainPanelFragment)
            btnGetRandomNumFact.setOnClickListener(this@MainPanelFragment)
        }

        return binding.root
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnGetNumFact.id -> {
                val givenNumber = binding.editTxtNumber.text.toString().toInt()
                mIMainActivity.getFactAboutGivenNumber(givenNumber)
            }
            binding.btnGetRandomNumFact.id -> {
                val randomNumber = Random.nextInt(0, 5000)
                mIMainActivity.getFactAboutGivenNumber(randomNumber)
            }
        }
    }

}