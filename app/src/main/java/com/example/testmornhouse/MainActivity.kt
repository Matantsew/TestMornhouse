package com.example.testmornhouse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testmornhouse.databinding.ActivityMainBinding
import com.example.testmornhouse.ui.fragments.FactAboutNumberFragment
import com.example.testmornhouse.ui.fragments.MainPanelFragment

class MainActivity : AppCompatActivity(), IMainActivity {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (savedInstanceState == null) {
            init()
        }
    }

    private fun init() {
        val mainPanelFragment = MainPanelFragment.newInstance()
        commitFragment(mainPanelFragment, null, "null", false)
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
        val factAboutNumberFragment = FactAboutNumberFragment.newInstance()

        val bundle = Bundle()
        bundle.putString("NUMBER", givenNumber.toString())

        commitFragment(factAboutNumberFragment, bundle, "Fact", true)
    }
}