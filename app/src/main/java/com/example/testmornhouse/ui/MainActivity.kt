package com.example.testmornhouse.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.testmornhouse.R
import com.example.testmornhouse.databinding.ActivityMainBinding
import com.example.testmornhouse.model.NumberFact
import com.example.testmornhouse.ui.fragments.FactAboutNumberFragment
import com.example.testmornhouse.ui.fragments.FactAboutNumberFragment.Companion.DESCRIPTION_ARG
import com.example.testmornhouse.ui.fragments.FactAboutNumberFragment.Companion.NUMBER_ARG
import com.example.testmornhouse.ui.fragments.MainPanelFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IMainActivity {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        initViewModel()

        setContentView(binding.root)

        if (savedInstanceState == null) {
            val mainPanelFragment = MainPanelFragment.newInstance()
            commitFragment(mainPanelFragment, null, "null", false)
        }
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.obtainedNumberFact.observe(this) {

            CoroutineScope(Dispatchers.Main).launch {
                openNumberFactFragment(it)
            }
        }
    }

    private fun openNumberFactFragment(numberFact: NumberFact) {
        val factAboutNumberFragment = FactAboutNumberFragment.newInstance()

        val givenNumber = numberFact.number
        val response = numberFact.fact

        val bundle = Bundle()
        bundle.putString(NUMBER_ARG, givenNumber.toString())
        bundle.putString(DESCRIPTION_ARG, response)

        commitFragment(factAboutNumberFragment, bundle, "Fact", true)
    }

    private fun commitFragment(fragment: Fragment, bundle: Bundle?, tag: String, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()

        fragment.arguments = bundle

        transaction.replace(R.id.container, fragment, tag)

        if(addToBackStack){
            transaction.addToBackStack(tag)
        }

        transaction.commit()
    }

    override fun getFactAboutGivenNumber(givenNumber: Int) {
        mainViewModel.obtainFactAboutNumber(givenNumber)
    }
}