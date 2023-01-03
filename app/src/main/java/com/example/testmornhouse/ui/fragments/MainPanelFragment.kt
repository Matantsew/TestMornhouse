package com.example.testmornhouse.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.testmornhouse.R
import com.example.testmornhouse.databinding.FragmentMainPanelBinding
import com.example.testmornhouse.model.NumberFact
import com.example.testmornhouse.ui.IMainActivity
import com.example.testmornhouse.ui.MainViewModel
import com.example.testmornhouse.ui.adapters.FactsHistoryAdapter
import kotlin.random.Random

class MainPanelFragment :
    Fragment(),
    View.OnClickListener,
    FactsHistoryAdapter.OnFactItemClickListener {

    companion object {

        const val TAG = "MainPanelFragment"

        fun newInstance() = MainPanelFragment()
    }

    private lateinit var mIMainActivity: IMainActivity

    private lateinit var binding: FragmentMainPanelBinding

    private lateinit var factsHistoryAdapter: FactsHistoryAdapter

    private val mainViewModel: MainViewModel by activityViewModels()

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

        factsHistoryAdapter = FactsHistoryAdapter(this)

        with(binding) {

            btnGetNumFact.setOnClickListener(this@MainPanelFragment)
            btnGetRandomNumFact.setOnClickListener(this@MainPanelFragment)

            binding.rvHistoryOfFacts.adapter = factsHistoryAdapter

            binding.rvHistoryOfFacts.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.factAboutNumberHistoryList.observe(viewLifecycleOwner) {
            factsHistoryAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.updateFactAboutNumberHistoryList()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            binding.btnGetNumFact.id -> {
                with(binding.editTxtNumber.text.toString()){
                    if(this.isEmpty()){
                        Toast.makeText(context, R.string.warning_no_number_entered, Toast.LENGTH_LONG).show()
                        return
                    }

                    val givenNumber = this.toInt()
                    mainViewModel.obtainFactAboutNumber(givenNumber)
                }
            }
            binding.btnGetRandomNumFact.id -> {
                val randomNumber = Random.nextInt(0, 5000)
                mainViewModel.obtainFactAboutNumber(randomNumber)
            }
        }
    }

    override fun onFactItemClick(selectedNumberFact: NumberFact) {
        mIMainActivity.onFactAboutGivenNumberSelected(selectedNumberFact)
    }
}